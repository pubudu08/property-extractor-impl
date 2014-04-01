package org.wso2.carbon.dev.govern.extractor.superuser.test;

import org.wso2.carbon.dev.govern.extractor.superuser.securevault.APIWrapper;
import org.wso2.carbon.dev.govern.extractor.superuser.securevault.ModuleProperties;

import java.util.List;

/**
 * Created by pubudu on 3/31/14.
 */
public class Main {
    public static void main(String[] args) {
        ModuleProperties module = new ModuleProperties();
        for(APIWrapper api :module.getApiList() ){
            System.out.println("Username "+api.getUsername());
            System.out.println("password "+api.getPassword());
            System.out.println("remote "+api.getRemote());
            System.out.println("serverURL "+api.getServerURL());
            System.out.println("apiName "+api.getApiName());
            System.out.println("===========================");

        }
    }
}
