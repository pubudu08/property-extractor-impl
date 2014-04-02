package org.wso2.carbon.dev.govern.extractor.superuser.test;

import org.wso2.carbon.dev.govern.extractor.superuser.securevault.APIWrapper;
import org.wso2.carbon.dev.govern.extractor.superuser.securevault.ModuleProperties;

/**
 * Created by pubudu on 3/31/14.
 */
public class Main {
    public static void main(String[] args) {
        ModuleProperties module = new ModuleProperties();
        APIWrapper apiWrapper = module.getAPIConfigDetails();
        System.out.println(apiWrapper.getJenkinsUsername());
        System.out.println(apiWrapper.getJenkinsPassword());
        System.out.println(apiWrapper.getBambooUsername());
        System.out.println(apiWrapper.getGitHubUsername());
        System.out.println(apiWrapper.getSvnUsername());
        System.out.println(apiWrapper.getRedmineUsername());
        System.out.println(apiWrapper.getJiraUsername());
    }
}
