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

/**
 * This class responsible of extracting properties from secured xml file
 * It will uses Secure vault implementation to retrieve password in the runtime environment
 */
public class SuperUserArtifacts {
	private static final Log LOGGER = LogFactory.getLog(SuperUserArtifacts.class);
	private ArrayList<UserArtifact> userArtifactArrayList;

	public static final String API_TYPE = "apiName";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String SERVER_URL = "serverURL";

	public static final String ARTIFACT_SECRET_ALIAS = "superuser.artifact.password";

	/**
	 * SuperUserArtifacts Constructor
	 */
	public SuperUserArtifacts() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("SuperUserArtifacts Class has been initialized");
		}
	}

	public ArrayList<UserArtifact> getUserArtifactArrayList() {
		return userArtifactArrayList;
	}

	public void setUserArtifactArrayList(ArrayList<UserArtifact> userArtifactArrayList) {
		this.userArtifactArrayList = userArtifactArrayList;
	}

	/**
	 * This method will extract all necessary artifact properties, which contained artifact's
	 * username and password ( will be secured using SecureVault ).
	 */
	public void performSuperUserXMLPropertyExtraction() {

		FileInputStream fileInputStream = null;
		//Assumed that configuration file is under the <PRODUCT_HOME>/repository/conf, check Resource folder for the file
		String configPath = CarbonUtils.getCarbonHome() +
		                    File.separator + "repository" + File.separator + "conf" +
		                    File.separator + "superuser-api-config.xml";
		File registryXML = new File(configPath);
		setUserArtifactArrayList(new ArrayList<UserArtifact>());

		if (registryXML.exists()) {
			try {
				fileInputStream = new FileInputStream(registryXML);
				StAXOMBuilder builder = new StAXOMBuilder(fileInputStream);
				OMElement configElement = builder.getDocumentElement();
				//Initialize the SecretResolver providing the configuration element.
				SecretResolver secretResolver = SecretResolverFactory.create(configElement, false);
				Iterator iterator = configElement.getChildElements();

				while (iterator.hasNext()) {
					UserArtifact userArtifact = new UserArtifact();
					OMElement temporaryArtifact = (OMElement) iterator.next();
					if (temporaryArtifact != null) {
						userArtifact.setUsername(temporaryArtifact.getFirstChildWithName(
						  new QName(USERNAME)).getText());
						if (secretResolver != null && secretResolver.isInitialized()) {
							userArtifact.setPassword(secretResolver.resolve(ARTIFACT_SECRET_ALIAS));
						} else {
							userArtifact.setPassword(temporaryArtifact.getFirstChildWithName(
							  new QName(PASSWORD)).getText());
						}
						userArtifact.setServerURL(temporaryArtifact.getFirstChildWithName(
						  new QName(API_TYPE)).getText());
						userArtifact.setApiName(temporaryArtifact.getFirstChildWithName(
						  new QName(SERVER_URL)).getText());
						userArtifactArrayList.add(userArtifact);
					}
				}
			} catch (XMLStreamException e) {
				LOGGER.error("Unable to parse superuser-api-config", e);
			} catch (IOException e) {
				LOGGER.error("Unable to read superuser-api-config", e);
			} finally {
				if (fileInputStream != null) {
					try {
						fileInputStream.close();
					} catch (IOException e) {
						LOGGER.error("Failed to close the FileInputStream, file : " + configPath);
					}
				}
			}
		}
	}

}
