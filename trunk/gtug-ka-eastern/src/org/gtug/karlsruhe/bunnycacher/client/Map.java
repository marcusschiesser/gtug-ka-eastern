package org.gtug.karlsruhe.bunnycacher.client;

import java.util.LinkedList;
import java.util.List;

import org.gtug.karlsruhe.bunnycacher.common.domain.Egg;

import com.googlecode.maps3.client.LatLng;
import com.googlecode.maps3.client.MapWidget;
import com.googlecode.maps3.client.Marker;

public class Map {

	private MapWidget _mapWidget;
	private Marker _position;
	private Circle _radar;
	private LatLng _actPos;
	private List<Marker> _eggs;
	
	public Map(LatLng actPos) {
		_eggs = new LinkedList<Marker>();
		_actPos = actPos;
		_mapWidget = MapFactory.createMap(actPos);
		_position = MapFactory.createPosition(_mapWidget, actPos);
		_radar = MapFactory.createCircle(_mapWidget, actPos, 0.1);
	}
	
	public MapWidget getMap() {
		return _mapWidget;
	}
	
	public void updatePosition(LatLng actPos) {
		_actPos = actPos;
		// call this function to update the position
		_position.setPosition(actPos);
		_mapWidget.getMapJSO().setCenter(actPos);
		// draw radar
	}
	
	public void setEggs(Egg[] eggs) {
		// remove old eggs
		for (Marker egg : _eggs) {
			egg.setMap(null);
		}
		_eggs.clear();
		// add new eggs
		for (Egg egg : eggs) {
			Marker eggMarker = MapFactory.createEgg(_mapWidget, LatLng.newInstance(egg.getLatitude(), egg.getLongitude()));
			_eggs.add(eggMarker);
		}
	}
	
	public LatLng getPosition() {
		return _actPos;
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
