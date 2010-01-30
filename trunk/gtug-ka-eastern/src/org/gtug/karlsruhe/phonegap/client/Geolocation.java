package org.gtug.karlsruhe.phonegap.client;

import com.google.gwt.core.client.JavaScriptObject;

public class Geolocation extends JavaScriptObject {
	/** Required for Overlay types */
	protected Geolocation() {}
	
	/** */
	public static native int watchPosition(PositionSuccessCallback successCallback) /*-{
		return $wnd.navigator.geolocation.watchPosition(function() {
			successCallback.@org.gtug.karlsruhe.phonegap.client.PositionSuccessCallback::onPosition(DD)($wnd.navigator.geolocation.lastPosition.coords.latitude, $wnd.navigator.geolocation.lastPosition.coords.longitude);
		});
	}-*/;
}
