package org.wso2.carbon.dev.govern.util.test;

import org.wso2.carbon.dev.govern.util.securevault.ModuleProperties;

/**
 * Created by pubudu on 3/31/14.
 */
public class Main {
    public static void main(String[] args) {
        ModuleProperties module = new ModuleProperties();
        System.out.println(module.getUsername()+"\n"+module.getPassword()+"\n"+module.getServerURL()+"\n"+module.isRemote());
    }
}
