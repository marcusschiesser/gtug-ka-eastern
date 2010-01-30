package com.googlecode.maps3.client;

import com.google.gwt.core.client.JavaScriptObject;

public class MarkerImage extends JavaScriptObject {

	protected MarkerImage() {
	}

	public static native MarkerImage newInstance(String url, Size size, Point origin, Point anchor, Size scaledSize) /*-{
		return new $wnd.google.maps.MarkerImage(url, size, origin, anchor, scaledSize);
	}-*/;

	public static native MarkerImage newInstance(String url, Point anchor) /*-{
		return new $wnd.google.maps.MarkerImage(url, null, null, anchor);
	}-*/;

}
