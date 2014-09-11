package org.wso2.carbon.dev.govern.extractor.superuser.test;

import org.wso2.carbon.dev.govern.extractor.superuser.securevault.SuperUserArtifacts;
import org.wso2.carbon.dev.govern.extractor.superuser.securevault.UserArtifact;

/**
 * Created by pubudu on 3/31/14.
 */
public class Main {
    public static void main(String[] args) {

	    SuperUserArtifacts superUserArtifacts = new SuperUserArtifacts();
	    superUserArtifacts.performSuperUserXMLPropertyExtraction();

	    for (UserArtifact userArtifact:superUserArtifacts.getUserArtifactArrayList()){
		    System.out.println(userArtifact.getUsername());

	    }
	    System.out.println(superUserArtifacts.getUserArtifactArrayList().size());
    }
}
