package org.gtug.karlsruhe.bunnycacher.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.googlecode.maps3.client.MapJSO;

public class Circle extends JavaScriptObject {

	protected Circle() {
	}

	public static native Circle newInstance(double latitude, double longitude) /*-{
		var coords = [
		new $wnd.google.maps.LatLng(latitude, longitude),
		new $wnd.google.maps.LatLng(latitude+0.1, longitude+0.1),
		new $wnd.google.maps.LatLng(latitude+0.1, longitude-0.1)
		];
		return new $wnd.google.maps.Polygon({
		paths: coords,
		strokeColor: "#FF0000",
		strokeOpacity: 0.8,
		strokeWeight: 2,
		fillColor: "#FF0000",
		fillOpacity: 0.35
		});
	}-*/;

	public final native void setMap(MapJSO map) /*-{
		this.setMap(map);
	}-*/;
}