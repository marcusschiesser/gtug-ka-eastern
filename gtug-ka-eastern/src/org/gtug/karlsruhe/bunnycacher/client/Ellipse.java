package org.gtug.karlsruhe.bunnycacher.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.googlecode.maps3.client.LatLng;
import com.googlecode.maps3.client.MapJSO;

public class Ellipse extends JavaScriptObject {

	protected Ellipse() {
	}

	public static native Ellipse newInstance(LatLng latLng,
			double xradius, double yradius, String color) /*-{
		var points = []; 
		var radians = Math.PI / 180; 
		var latitudeOffset = xradius / 111325; 
		var longitudeOffset = yradius / (Math.cos(latLng.lng() * radians) * 
		111325); 
		for (var i = 0; i < 360; i += 10) { 
		    var point = new $wnd.google.maps.LatLng(latLng.lat() + (longitudeOffset * Math.cos(i 
		* radians)), latLng.lng() + (latitudeOffset * Math.sin(i * radians))); 
		    points.push(point); 
		} 
		points.push(points[0]);// close the circle 
		return new $wnd.google.maps.Polygon({
		paths: points,
		strokeColor: color,
		strokeOpacity: 0.8,
		strokeWeight: 2,
		fillColor: color,
		fillOpacity: 0.35
		});
	}-*/;

	public final native void setMap(MapJSO map) /*-{
		this.setMap(map);
	}-*/;
}