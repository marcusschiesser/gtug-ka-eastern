package org.gtug.karlsruhe.bunnycasher.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gtug_ka_eastern implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	public static final EggServiceAsync eggService = GWT.create(EggService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel.get("main").add(new MainForm());

	}
}
