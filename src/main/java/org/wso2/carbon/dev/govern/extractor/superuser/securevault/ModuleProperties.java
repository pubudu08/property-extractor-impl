package org.wso2.carbon.dev.govern.extractor.superuser.securevault;

/*
 * Copyright (c) 2006, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.dev.govern.extractor.IPropertyExtractor;
import org.wso2.carbon.utils.CarbonUtils;
import org.wso2.securevault.SecretResolver;
import org.wso2.securevault.SecretResolverFactory;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModuleProperties implements IPropertyExtractor {
    private static final Log log = LogFactory.getLog(ModuleProperties.class);
    private List<APIWrapper> apiList = new ArrayList<APIWrapper>();

    public ModuleProperties() {
        xmlExtractor();
    }

    public List<APIWrapper> getApiList() {
        return apiList;
    }

    @Override
    public void xmlExtractor() {
        FileInputStream fileInputStream = null;
        //Assumed that configuration file is under the <PRODUCT_HOME>/repository/conf, check Resource folder for the file
        /*String configPath = CarbonUtils.getCarbonHome() + File.separator + "repository" + File.separator + "conf" +
                File.separator + "superuser-api-config.xml";*/
        String configPath = File.separator + "home" + File.separator + "pubudu" +
                File.separator + "Desktop" + File.separator + "superuser-api-config.xml";

        File registryXML = new File(configPath);
        if (registryXML.exists()) {
            try {
                fileInputStream = new FileInputStream(registryXML);
                StAXOMBuilder builder = new StAXOMBuilder(fileInputStream);
                OMElement configElement = builder.getDocumentElement();
                //Initialize the SecretResolver providing the configuration element.
                SecretResolver secretResolver = SecretResolverFactory.create(configElement, false);
                QName elementQName = new QName("module");
                Iterator infoIter =
                        configElement.getChildrenWithName(elementQName);

                while (infoIter.hasNext()) {

                    OMElement element = (OMElement) infoIter.next();
                    APIWrapper apiWrapper = new APIWrapper();
                    if (element != null) {
                        apiWrapper.setUsername(element.getFirstChildWithName(new QName("username")).getText());
                        apiWrapper.setServerURL(element.getAttributeValue(new QName("serverURL")));
                        apiWrapper.setRemote(element.getAttributeValue(new QName("remote")));
                        apiWrapper.setApiName(element.getAttributeValue(new QName("apiName")));

                        String secretAlias = "superuser.module.password";
                        if (secretResolver != null && secretResolver.isInitialized()) {
                            if (secretResolver.isTokenProtected(secretAlias)) {
                                apiWrapper.setPassword(secretResolver.resolve(secretAlias));
                            } else {
                                apiWrapper.setPassword(element.getFirstChildWithName(new QName("password")).getText());
                            }
                        }

                    }
                    apiList.add(apiWrapper);
                }


            } catch (XMLStreamException e) {
                log.error("Unable to parse superuser-api-config", e);
            } catch (IOException e) {
                log.error("Unable to read superuser-api-config", e);
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        log.error("Failed to close the FileInputStream, file : " + configPath);
                    }
                }
            }
        }
    }

    @Override
    public void propertyfileExtractor() {

    }
}
