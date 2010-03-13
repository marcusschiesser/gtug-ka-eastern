package org.gtug.karlsruhe.bunnycacher.common.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.gtug.karlsruhe.bunnycacher.client.LoginInfo;

/**
 * LoginService determines the current user's login state via appengine's UserService.
 * <p/>
 * It does _not_ perform an actual login, in case of a missing session the returned LoginInfo object
 * contains a redirect URL that points to the google login form.
 * <p/>
 * The code is inspired from the <a href="http://code.google.com/intl/de-DE/webtoolkit/doc/latest/tutorial/appengine.html#user">
 * GWT appengine tutorial</a>.
 */
@RemoteServiceRelativePath("GWT.rpc")
public interface LoginService extends RemoteService {

    /**
     * Tries to authenticate the user associated with the current Thread via
     * the appengine SDK's <a href="http://code.google.com/intl/de-DE/appengine/docs/python/gettingstarted/usingusers.html">
     * UserService</a>.
     * <p/>
     * Returns a result object that contains the loggedIn state, in addition to the the user data if a
     * session exists, else contains a redirect URL that points to the getLoginInfo form and itself contains
     * the current url as return-to URL.
     *
     * @param requestUri - the current request's URL
     * @return authentication state as LoginInfo object
     */
    public LoginInfo getLoginInfo(String requestUri);

}

