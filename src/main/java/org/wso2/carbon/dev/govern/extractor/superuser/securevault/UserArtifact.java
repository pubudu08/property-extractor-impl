package org.wso2.carbon.dev.govern.extractor.superuser.securevault;


/**
 * Created by Pubudu Dissanayake on 9/11/14.
 * pubudud@wso2.com
 */
public class UserArtifact {
	private String username;
	private String password;
	private String serverURL;
	private String apiName;

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param apiName
	 */
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	/**
	 * @param serverURL
	 */
	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return
	 */
	public String getServerURL() {
		return serverURL;
	}

	/**
	 * @return
	 */
	public String getApiName() {
		return apiName;
	}
}
