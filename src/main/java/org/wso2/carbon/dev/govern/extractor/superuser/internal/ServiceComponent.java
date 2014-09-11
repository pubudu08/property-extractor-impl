package org.wso2.carbon.dev.govern.extractor.superuser.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.dev.govern.extractor.superuser.securevault.SuperUserArtifacts;


/**
 * Created by Pubudu Dissanayake - pubudud@wso2.com on 31/3/14.
 *
 * @scr.component name="org.wso2.carbon.dev.govern.extractor.superuser"
 * immediate="true"
 */
public class ServiceComponent {

	private static ServiceRegistration serviceRegistry;
	private static BundleContext bundleContext;
	private static SuperUserArtifacts superUserArtifacts;
	private static final Log LOGGER = LogFactory.getLog(ServiceComponent.class);


	public void activate(ComponentContext componentContext) {
		LOGGER.info("SuperUser config extractor bundle is activated");
		superUserArtifacts = new SuperUserArtifacts();
		superUserArtifacts.performSuperUserXMLPropertyExtraction();
		bundleContext = componentContext.getBundleContext();
		// serviceRegistry = bundleContext.registerService(IPropertyExtractor.class,superUserArtifacts,null);

	}

	public void deactivate(ComponentContext componentContext) {
		LOGGER.info("SuperUser config extractor bundle is deactivated");
		serviceRegistry.unregister();
		bundleContext = null;
		superUserArtifacts = null;
	}
}
