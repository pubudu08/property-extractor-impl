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



public class ModuleProperties implements IPropertyExtractor {
    private static final Log log = LogFactory.getLog(ModuleProperties.class);
    private APIWrapper apiWrapper;

    public static final String JENKINS = "jenkins";
    public static final String BAMBOO = "bamboo";
    public static final String JIRA = "jira";
    public static final String REDMINE = "redmine";
    public static final String GITHUB = "github";
    public static final String SVN = "svn";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String JENKINS_SECRET_ALIAS= "superuser.jenkins.password";
    public static final String BAMBOO_SECRET_ALIAS= "superuser.bamboo.password";
    public static final String REDMINE_SECRET_ALIAS= "superuser.redmine.password";
    public static final String JIRA_SECRET_ALIAS= "superuser.jira.password";
    public static final String GITHUB_SECRET_ALIAS= "superuser.github.password";
    public static final String SVN_SECRET_ALIAS= "superuser.svn.password";

    public APIWrapper getApiWrapper() {
        return apiWrapper;
    }

    private void setApiWrapper(APIWrapper apiWrapper) {
        this.apiWrapper = apiWrapper;
    }

    public ModuleProperties() {
        xmlExtractor();
    }


    @Override
    public void xmlExtractor() {
        FileInputStream fileInputStream = null;
        //Assumed that configuration file is under the <PRODUCT_HOME>/repository/conf, check Resource folder for the file
       /* String configPath = CarbonUtils.getCarbonHome() + File.separator + "repository" + File.separator + "conf" +
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
                setApiWrapper(new APIWrapper());

                //extracting jenkins Server data
                OMElement jenkinsModule = configElement.getFirstChildWithName(new QName(JENKINS));
                apiWrapper.setJenkinsUsername(jenkinsModule.getFirstChildWithName(new QName(USERNAME)).getText());
                String jenkinsSecretAlias = JENKINS_SECRET_ALIAS;
                if (secretResolver != null && secretResolver.isInitialized()) {
                    if (secretResolver.isTokenProtected(jenkinsSecretAlias)) {
                        apiWrapper.setJenkinsPassword(secretResolver.resolve(jenkinsSecretAlias));
                    } else {
                        apiWrapper.setJenkinsPassword(jenkinsModule.getFirstChildWithName(new QName(PASSWORD)).getText());
                    }
                }
                //extracting Bamboo Server data
                OMElement bambooModule = configElement.getFirstChildWithName(new QName(BAMBOO));
                apiWrapper.setBambooUsername(bambooModule.getFirstChildWithName(new QName(USERNAME)).getText());
                String bambooSecretAlias = BAMBOO_SECRET_ALIAS;
                if (secretResolver != null && secretResolver.isInitialized()) {
                    if (secretResolver.isTokenProtected(bambooSecretAlias)) {
                        apiWrapper.setBambooPassword(secretResolver.resolve(bambooSecretAlias));
                    } else {
                        apiWrapper.setBambooPassword(bambooModule.getFirstChildWithName(new QName(PASSWORD)).getText());
                    }
                }
                //extracting RedMine Server data
                OMElement redmineModule = configElement.getFirstChildWithName(new QName(REDMINE));
                apiWrapper.setRedmineUsername(redmineModule.getFirstChildWithName(new QName(USERNAME)).getText());
                String redmineSecretAlias = REDMINE_SECRET_ALIAS;
                if (secretResolver != null && secretResolver.isInitialized()) {
                    if (secretResolver.isTokenProtected(redmineSecretAlias)) {
                        apiWrapper.setRedminePassword(secretResolver.resolve(redmineSecretAlias));
                    } else {
                        apiWrapper.setRedminePassword(redmineModule.getFirstChildWithName(new QName(PASSWORD)).getText());
                    }
                }
                //extracting Jira Server data
                OMElement jiraModule = configElement.getFirstChildWithName(new QName(JIRA));
                apiWrapper.setJiraUsername(jiraModule.getFirstChildWithName(new QName(USERNAME)).getText());
                String jiraSecretAlias = JIRA_SECRET_ALIAS;
                if (secretResolver != null && secretResolver.isInitialized()) {
                    if (secretResolver.isTokenProtected(jiraSecretAlias)) {
                        apiWrapper.setJiraPassword(secretResolver.resolve(jiraSecretAlias));
                    } else {
                        apiWrapper.setJiraPassword(jiraModule.getFirstChildWithName(new QName(PASSWORD)).getText());
                    }
                }
                //extracting GitHub Server data
                OMElement gitHubModule = configElement.getFirstChildWithName(new QName(GITHUB));
                apiWrapper.setGitHubUsername(gitHubModule.getFirstChildWithName(new QName(USERNAME)).getText());
                String gitHubSecretAlias =GITHUB_SECRET_ALIAS;
                if (secretResolver != null && secretResolver.isInitialized()) {
                    if (secretResolver.isTokenProtected(gitHubSecretAlias)) {
                        apiWrapper.setGitHubPassword(secretResolver.resolve(gitHubSecretAlias));
                    } else {
                        apiWrapper.setGitHubPassword(gitHubModule.getFirstChildWithName(new QName(PASSWORD)).getText());
                    }
                }
                //extracting SVN Server data
                OMElement svnModule = configElement.getFirstChildWithName(new QName(SVN));
                apiWrapper.setSvnUsername(svnModule.getFirstChildWithName(new QName(USERNAME)).getText());
                String svnSecretAlias =SVN_SECRET_ALIAS;
                if (secretResolver != null && secretResolver.isInitialized()) {
                    if (secretResolver.isTokenProtected(svnSecretAlias)) {
                        apiWrapper.setSvnPassword(secretResolver.resolve(svnSecretAlias));
                    } else {
                        apiWrapper.setSvnPassword(svnModule.getFirstChildWithName(new QName(PASSWORD)).getText());
                    }
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
