
package com.vanduc.tapetee.Model.about;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HDWALLPAPER {

    @SerializedName("app_name")
    @Expose
    private String appName;
    @SerializedName("app_logo")
    @Expose
    private String appLogo;
    @SerializedName("app_version")
    @Expose
    private String appVersion;
    @SerializedName("app_author")
    @Expose
    private String appAuthor;
    @SerializedName("app_contact")
    @Expose
    private String appContact;
    @SerializedName("app_email")
    @Expose
    private String appEmail;
    @SerializedName("app_website")
    @Expose
    private String appWebsite;
    @SerializedName("app_description")
    @Expose
    private String appDescription;
    @SerializedName("app_developed_by")
    @Expose
    private String appDevelopedBy;
    @SerializedName("app_privacy_policy")
    @Expose
    private String appPrivacyPolicy;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppAuthor() {
        return appAuthor;
    }

    public void setAppAuthor(String appAuthor) {
        this.appAuthor = appAuthor;
    }

    public String getAppContact() {
        return appContact;
    }

    public void setAppContact(String appContact) {
        this.appContact = appContact;
    }

    public String getAppEmail() {
        return appEmail;
    }

    public void setAppEmail(String appEmail) {
        this.appEmail = appEmail;
    }

    public String getAppWebsite() {
        return appWebsite;
    }

    public void setAppWebsite(String appWebsite) {
        this.appWebsite = appWebsite;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public String getAppDevelopedBy() {
        return appDevelopedBy;
    }

    public void setAppDevelopedBy(String appDevelopedBy) {
        this.appDevelopedBy = appDevelopedBy;
    }

    public String getAppPrivacyPolicy() {
        return appPrivacyPolicy;
    }

    public void setAppPrivacyPolicy(String appPrivacyPolicy) {
        this.appPrivacyPolicy = appPrivacyPolicy;
    }

}
