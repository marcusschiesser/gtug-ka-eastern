package org.gtug.karlsruhe.bunnycacher.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootLayoutPanel;
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

    // private static String baseUrl = "http://bunnycacher.appspot.com/bunnycacher";
    private static String baseUrl = "http://bunnycasher-dev.appspot.com/bunnycacher";

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
        RootLayoutPanel.get().add(new MainForm());
    }
}
