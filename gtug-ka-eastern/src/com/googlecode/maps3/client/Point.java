package com.googlecode.maps3.client;

import com.google.gwt.core.client.JavaScriptObject;

public class Point extends JavaScriptObject {
	/** Required by overlays */
	protected Point() {}
	
	public static native Point newInstance(double x, double y) /*-{
		return new $wnd.google.maps.Point(x, y);
	}-*/;
}
