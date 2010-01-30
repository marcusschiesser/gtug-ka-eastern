package org.gtug.karlsruhe.phonegap.client;

import com.google.gwt.core.client.JavaScriptObject;

public class PositionOptions extends JavaScriptObject {
	/** Required for Overlay types */
	protected PositionOptions() {}
	
	/** */
	public static native PositionOptions newInstance() /*-{
		return {};
	}-*/;
	/** */
	public static native PositionOptions newInstance(Boolean enableHighAccuracy) /*-{
		return {
			enableHighAccuracy: enableHighAccuracy
		};
	}-*/;
	/** */
	public static native PositionOptions newInstance(int timeout) /*-{
		return {
			timeout: timeout
		};
	}-*/;
	public static native PositionOptions newInstance(Boolean enableHighAccuracy, int timeout) /*-{
		return {
			enableHighAccuracy: enableHighAccuracy,
			timeout: timeout
		};
	}-*/;
	
}
