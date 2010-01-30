package org.gtug.karlsruhe.phonegap.client;

import com.google.gwt.core.client.JavaScriptObject;

public class Notification extends JavaScriptObject {
	/** Required for Overlay types */
	protected Notification() {}
	
	/** Native alert message */
	public static native void alert(String message, String title, String buttonLabel) /*-{
		return $wnd.navigator.notification.alert(message, title, buttonLabel);
	}-*/;
	/** Native alert message */
	public static native void alert(String message, String title) /*-{
		return $wnd.navigator.notification.alert(message, title);
	}-*/;
	/** Native alert message */
	public static native void alert(String message) /*-{
		return $wnd.navigator.notification.alert(message);
	}-*/;
	
	/** Show non-modal activity indicator (spinnig wheel in status bar) */
	public static native void activityStart() /*-{
		return $wnd.navigator.notification.activityStart();
	}-*/;
	/** Hide non-modal activity indicator (spinnig wheel in status bar) */
	public static native void activityStop() /*-{
		return $wnd.navigator.notification.activityStop();
	}-*/;
	
	/** Show modal activity indicator (spinnig wheel over whole app) */
	public static native void loadingStart() /*-{
		return $wnd.navigator.notification.loadingStart();
	}-*/;
	/** Hide modal activity indicator (spinnig wheel over whole app) */
	public static native void loadingStop() /*-{
		return $wnd.navigator.notification.loadingStop();
	}-*/;
	
	/** Causes the device to vibrate. 
	 * @param millis The number of milliseconds to vibrate for.
	 * */
	public static native void vibrate(int mills) /*-{
		return $wnd.navigator.notification.vibrate(millis);
	}-*/;
	/** Causes the device to vibrate. 
	 * */
	public static native void vibrate() /*-{
		return $wnd.navigator.notification.vibrate();
	}-*/;
	/** Causes the device to beep. 
	 * @param count The number of beeps.
	 * @param volume The volume of the beep.
	 * */
	public static native void beep(int count, int volume) /*-{
		return $wnd.navigator.notification.beep(count, volume);
	}-*/;
	/** Causes the device to beep. 
	 * */
	public static native void beep() /*-{
		return $wnd.navigator.notification.beep();
	}-*/;
	
}
