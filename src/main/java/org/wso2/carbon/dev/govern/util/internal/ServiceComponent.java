package org.wso2.carbon.dev.govern.util.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.dev.govern.util.IPropertyFile;
import org.wso2.carbon.dev.govern.util.securevault.ModuleProperties;


/**
 * Created by Pubudu Dissanayake - pubudud@wso2.com on 31/3/14.
 * @scr.component name="org.wso2.carbon.dev.govern.util"
 * immediate="true"
 */
public class ServiceComponent {

    private ServiceRegistration serviceRegistry;
    private static BundleContext  bundleContext;
    private static ModuleProperties moduleProperties;
    private static final Log logger = LogFactory.getLog(ServiceComponent.class);


    public void activate(ComponentContext componentContext){
        logger.info("SuperUser config extractor bundle is activated");
        moduleProperties= new ModuleProperties();
        bundleContext = componentContext.getBundleContext();
        serviceRegistry = bundleContext.registerService(IPropertyFile.class,moduleProperties,null);

    }

    public void deactivate(ComponentContext componentContext){
        logger.info("SuperUser config extractor bundle is deactivated");
        serviceRegistry.unregister();
        bundleContext=null;
        moduleProperties=null;
    }
}
