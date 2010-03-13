package org.gtug.karlsruhe.bunnycacher.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.gtug.karlsruhe.bunnycacher.common.service.EggService;
import org.gtug.karlsruhe.bunnycacher.common.service.EggServiceAsync;
import org.gtug.karlsruhe.bunnycacher.common.service.LoginService;
import org.gtug.karlsruhe.bunnycacher.common.service.LoginServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {


    /**
     * Create a remote service proxy to talk to the server-side Greeting
     * service.
     */
    public static LoginServiceAsync loginService = (LoginServiceAsync) updateEndpoint(GWT.create(LoginService.class));
    public static final EggServiceAsync eggService = (EggServiceAsync) updateEndpoint(GWT.create(EggService.class));

    private LoginInfo loginInfo = null;
    private VerticalPanel loginPanel = new VerticalPanel();
    private Label loginLabel = new Label("Bitte log Dich mit Deinem Google Account ein.");
    private Anchor signInLink = new Anchor("Login");

    /**
     * Set to false in order to disable login.
     */
    private boolean loginDisabled = false;

    private static String baseUrl = "http://bunnycacher.appspot.com/bunnycacher";

    /**
     * Create a remote service proxy to talk to the server-side Greeting
     * service.
     */
    private static Object updateEndpoint(Object service) {

        // when not running in development mode, i.e. within Phonegap
        // the URL is a file-URL
        // direct RPC calls to http://bunnycacher.appspot.com/bunnycasher/GWT.rpc
        ServiceDefTarget endpoint = (ServiceDefTarget) service;
        String rpcUrl = endpoint.getServiceEntryPoint();
        // Window.alert("rpcURL: " + rpcUrl);
        if (rpcUrl.startsWith("file:")) {
            // set new rpcURL
            endpoint.setServiceEntryPoint(baseUrl + "/GWT.rpc");
        }
    
        return service;
    }

    /**
     * The entry point method checks login status using login service, then shows login link
     * if user is not logged in, else displays main application.
     */
    public void onModuleLoad() {
    	// Inject global styles.
//        Resources.INSTANCE.style().ensureInjected();
		// Get rid of scrollbars, and clear out the window's built-in margin,
		// because we want to take advantage of the entire client area.
		Window.enableScrolling(false);
		Window.setMargin("0px");
		
        if (loginDisabled) {
            loadMainForm();
        } else {
            String hostPageBaseUrl = GWT.getHostPageBaseURL();
            if ( hostPageBaseUrl.startsWith("file:") ) {
                // TODO: maybe append the path-info?
                hostPageBaseUrl = baseUrl;
            }
            loginService.login(hostPageBaseUrl, new AsyncCallback<LoginInfo>() {
                public void onFailure(Throwable error) {
                    // TODO: replace with error handling once a valid Session is required.
                    GWT.log("Asynchronous call of LoginService.login failed to complete normally, " +
                            "forwarding to application without valid user session anyway.", error);
                    loadMainForm();
                }

                public void onSuccess(LoginInfo result) {
                    loginInfo = result;
                    if (loginInfo.isLoggedIn()) {
                        loadMainForm();
                    } else {
                        GWT.log("User is not loggend in, show login form.", null);
                        loadLogin();
                    }
                }
            });
        }
    }

    private void loadLogin() {
        // Assemble login panel.
        signInLink.setHref(loginInfo.getLoginUrl());
        loginPanel.add(loginLabel);
        loginPanel.add(signInLink);
        RootLayoutPanel.get().add(loginPanel);
    }

    private void loadMainForm() {
    	
    	RootLayoutPanel.get().add(new MainForm());
    }
}
