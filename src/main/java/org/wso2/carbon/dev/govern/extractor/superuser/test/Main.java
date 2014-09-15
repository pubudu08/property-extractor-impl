package org.wso2.carbon.dev.govern.extractor.superuser.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.dev.govern.extractor.exception.GenericArtifactException;
import org.wso2.carbon.dev.govern.extractor.superuser.securevault.SuperUserArtifacts;
import org.wso2.carbon.dev.govern.extractor.superuser.securevault.UserArtifact;

/**
 * Created by pubudu on 3/31/14.
 */
public class Main {
	private static final Log LOGGER = LogFactory.getLog(Main.class);
    public static void main(String[] args) {

	    SuperUserArtifacts superUserArtifacts = new SuperUserArtifacts();
	    try {
		    superUserArtifacts.performSuperUserXMLPropertyExtraction();
	    } catch (GenericArtifactException exception) {
		    LOGGER.error("Unable to parse superuser-api-config.xml", exception);
	    }

	    for (UserArtifact userArtifact:superUserArtifacts.getUserArtifactArrayList()){
		    System.out.println(userArtifact.getUsername());

	    }
	    System.out.println(superUserArtifacts.getUserArtifactArrayList().size());
    }
}
