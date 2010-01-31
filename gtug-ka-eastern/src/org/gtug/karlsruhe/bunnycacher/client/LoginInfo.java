package org.gtug.karlsruhe.bunnycacher.client;

import java.io.Serializable;

/**
 * Result object for the {@link org.gtug.karlsruhe.bunnycacher.common.service.LoginService#login(String)} method.
 * <p/>
 * Copied from the <a href="http://code.google.com/intl/de-DE/webtoolkit/doc/latest/tutorial/appengine.html#user">
 * GWT appengine tutorial</a>.
 *
 */
public class LoginInfo implements Serializable {

    private boolean loggedIn = false;
    private String loginUrl;
    private String logoutUrl;
    private String emailAddress;
    private String nickname;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

