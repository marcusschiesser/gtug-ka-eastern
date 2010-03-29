package org.gtug.karlsruhe.bunnycacher.server;

import org.gtug.karlsruhe.bunnycacher.client.LoginInfo;
import org.gtug.karlsruhe.bunnycacher.common.service.LoginService;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.core.client.GWT;

public class LoginServiceImpl implements LoginService {

    public LoginInfo getLoginInfo(String requestUri) {
        UserService userService = UserServiceFactory.getUserService();

        User user = null;
        LoginInfo loginInfo = new LoginInfo();
        try {
            user = userService.getCurrentUser();
            if (user != null) {
                loginInfo.setLoggedIn(true);
                loginInfo.setEmailAddress(user.getEmail());
                loginInfo.setNickname(user.getNickname());
            } else {
                loginInfo.setLoggedIn(false);
                loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
            }
        }
        catch (NullPointerException e) {
            // Running in the default way via development mode causes a
            // NullPointerException. On Appengine it works. See:
            // http://code.google.com/p/googleappengine/issues/detail?id=2358
            GWT.log("NPE when calling Appengine UserService, seems to be a bug, we use a dummy user instead.", e);
            loginInfo.setLoggedIn(true);
            loginInfo.setEmailAddress("dummy@gmail.com");
            loginInfo.setNickname("Dummy");
        }

        return loginInfo;
    }
}
