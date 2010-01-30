package org.gtug.karlsruhe.bunnycacher.client;

import com.googlecode.maps3.client.LatLng;
import com.googlecode.maps3.client.MapWidget;
import com.googlecode.maps3.client.Marker;

public class Map {

	private MapWidget _mapWidget;
	private Marker _position;
	private Circle _radar;
	
	public Map(LatLng actPos) {
		_mapWidget = MapFactory.createMap(actPos);
		_position = MapFactory.createPosition(_mapWidget, actPos);
		_radar = MapFactory.createCircle(_mapWidget, actPos, 0.1);
	}
	
	public MapWidget getMap() {
		return _mapWidget;
	}
	
	public void updatePosition(LatLng actPos) {
		// call this function to update the position
		_position.setPosition(actPos);
		_mapWidget.getMapJSO().setCenter(actPos);
		// draw radar
	}
	
	private void drawRadar(LatLng actPos, double radius) {
	/*	function drawCircle(center, radius, color, width, complexity) { 
		    var points = []; 
		    var radians = Math.PI / 180; 
		    var longitudeOffset = radius / (Math.cos(center.y * radians) * 
		111325); 
		    var latitudeOffset = radius / 111325; 
		    for (var i = 0; i < 360; i += complexity) { 
		        var point = new GPoint(center.x + (longitudeOffset * Math.cos(i 
		* radians)), center.y + (latitudeOffset * Math.sin(i * radians))); 
		        points.push(point); 
		    } 
		    points.push(points[0]);// close the circle 
		    var polygon = new GPolygon(points, true, "#F0F0F0",0.25, true); 
		    map.addOverlay(polygon); 
		} 		*/
	}

}
