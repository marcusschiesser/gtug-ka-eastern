package org.gtug.karlsruhe.bunnycacher.client.util;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class ScrollUtils extends JavaScriptObject {
	/** Required for Overlay types */
	protected ScrollUtils() {}
	
	public static final native void setUpScroll(Element el) /*-{
		new $wnd.iScroll(el);
	}-*/;
}
