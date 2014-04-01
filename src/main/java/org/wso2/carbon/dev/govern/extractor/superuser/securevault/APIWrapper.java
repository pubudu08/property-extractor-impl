package org.wso2.carbon.dev.govern.extractor.superuser.securevault;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by pubudu on 4/1/14.
 */
public class APIWrapper {
    private static final Log log = LogFactory.getLog(APIWrapper.class);

    private String jenkinsUsername;
    private String jenkinsPassword;
    private String bambooUsername;
    private String bambooPassword;
    private String jiraUsername;
    private String jiraPassword;
    private String redmineUsername;
    private String redminePassword;
    private String gitHubUsername;
    private String gitHubPassword;
    private String svnUsername;
    private String svnPassword;

    public String getJenkinsUsername() {
        return jenkinsUsername;
    }

    public void setJenkinsUsername(String jenkinsUsername) {
        this.jenkinsUsername = jenkinsUsername;
    }

    public String getJenkinsPassword() {
        return jenkinsPassword;
    }

    public void setJenkinsPassword(String jenkinsPassword) {
        this.jenkinsPassword = jenkinsPassword;
    }

    public String getBambooUsername() {
        return bambooUsername;
    }

    public void setBambooUsername(String bambooUsername) {
        this.bambooUsername = bambooUsername;
    }

    public String getBambooPassword() {
        return bambooPassword;
    }

    public void setBambooPassword(String bambooPassword) {
        this.bambooPassword = bambooPassword;
    }

    public String getJiraUsername() {
        return jiraUsername;
    }

    public void setJiraUsername(String jiraUsername) {
        this.jiraUsername = jiraUsername;
    }

    public String getJiraPassword() {
        return jiraPassword;
    }

    public void setJiraPassword(String jiraPassword) {
        this.jiraPassword = jiraPassword;
    }

    public String getRedmineUsername() {
        return redmineUsername;
    }

    public void setRedmineUsername(String redmineUsername) {
        this.redmineUsername = redmineUsername;
    }

    public String getRedminePassword() {
        return redminePassword;
    }

    public void setRedminePassword(String redminePassword) {
        this.redminePassword = redminePassword;
    }

    public String getGitHubUsername() {
        return gitHubUsername;
    }

    public void setGitHubUsername(String gitHubUsername) {
        this.gitHubUsername = gitHubUsername;
    }

    public String getGitHubPassword() {
        return gitHubPassword;
    }

    public void setGitHubPassword(String gitHubPassword) {
        this.gitHubPassword = gitHubPassword;
    }

    public String getSvnUsername() {
        return svnUsername;
    }

    public void setSvnUsername(String svnUsername) {
        this.svnUsername = svnUsername;
    }

    public String getSvnPassword() {
        return svnPassword;
    }

    public void setSvnPassword(String svnPassword) {
        this.svnPassword = svnPassword;
    }
}
