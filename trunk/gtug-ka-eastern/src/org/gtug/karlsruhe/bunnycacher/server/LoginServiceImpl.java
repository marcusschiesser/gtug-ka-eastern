package org.gtug.karlsruhe.bunnycacher.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gtug.karlsruhe.bunnycacher.client.LoginInfo;
import org.gtug.karlsruhe.bunnycacher.common.service.LoginService;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class LoginServiceImpl implements LoginService {

	private Provider<HttpServletRequest> httpServletRequest;

	@Inject
	public LoginServiceImpl(Provider<HttpServletRequest> httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

    public LoginInfo login(String requestUri) {
        UserService userService = UserServiceFactory.getUserService();

        User user = null;
        LoginInfo loginInfo = new LoginInfo();
        try {
            user = userService.getCurrentUser();
            if (user != null) {
                loginInfo.setLoggedIn(true);
                loginInfo.setEmailAddress(user.getEmail());
                loginInfo.setNickname(user.getNickname());
                //loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));

                HttpSession session = httpServletRequest.get().getSession();
                session.setAttribute("currentUser", user);
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
            // loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
        }

        return loginInfo;
    }

    @Override
    public String getCurrentUserId() {

        HttpSession session = httpServletRequest.get().getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        return currentUser.getUserId();
    }

}
