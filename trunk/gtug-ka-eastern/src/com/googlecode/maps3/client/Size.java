package com.googlecode.maps3.client;

import com.google.gwt.core.client.JavaScriptObject;

public class Size extends JavaScriptObject {

	protected Size() {
	}

	public static native Size newInstance(double width, double height, String widthUnit, String heightUnit) /*-{
		return new $wnd.google.maps.Size(width, height, widthUnit, heightUnit);
	}-*/;
}
