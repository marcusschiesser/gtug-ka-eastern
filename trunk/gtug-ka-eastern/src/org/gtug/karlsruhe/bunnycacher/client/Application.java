package org.gtug.karlsruhe.bunnycacher.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.rpc.ServiceDefTarget;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.RootPanel;
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
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */

    /**
     * Create a remote service proxy to talk to the server-side Greeting
     * service.
     */
    
    public static LoginServiceAsync loginService = (LoginServiceAsync)updateEndpoint(GWT.create(LoginService.class));
    public static final EggServiceAsync eggService = (EggServiceAsync)updateEndpoint(GWT.create(EggService.class));

    private LoginInfo loginInfo = null;
    private VerticalPanel loginPanel = new VerticalPanel();
    private Label loginLabel = new Label("Bitte log Dich mit Deinem Google Account ein.");
    private Anchor signInLink = new Anchor("Login");


	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private static Object updateEndpoint(Object service) {
		
		// when not running in development mode, i.e. within Phonegap
		// the URL is a file-URL
		// direct RPC calls to http://bunnycacher.appspot.com/bunnycasher/GWT.rpc
		ServiceDefTarget endpoint = (ServiceDefTarget)service;
		String rpcUrl = endpoint.getServiceEntryPoint();
		// Window.alert("rpcURL: " + rpcUrl);
		if (rpcUrl.startsWith("file:")) {
			// set new rpcURL
			endpoint.setServiceEntryPoint("http://bunnycacher.appspot.com/bunnycacher/GWT.rpc");
		}
		
		return service;
	}
	
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        // Check login status using login service.





        loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
            public void onFailure(Throwable error) {
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

    private void loadLogin() {
        // Assemble login panel.
        signInLink.setHref(loginInfo.getLoginUrl());
        loginPanel.add(loginLabel);
        loginPanel.add(signInLink);
        RootPanel.get("main").add(loginPanel);
    }

    private void loadMainForm() {
        RootPanel.get("main").add(new MainForm());
    }
}
