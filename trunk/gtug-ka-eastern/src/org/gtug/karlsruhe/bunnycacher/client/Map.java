package org.gtug.karlsruhe.bunnycacher.client;

import java.util.LinkedList;
import java.util.List;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;

import com.google.gwt.user.client.Timer;
import com.googlecode.maps3.client.LatLng;
import com.googlecode.maps3.client.MapWidget;
import com.googlecode.maps3.client.Marker;

public class Map extends MapWidget {

	private static final int MIN_RADIUS = 200;
	private static final int MAX_RADIUS = 220;

	private Marker _position;
	private Ellipse _radar;
	private LatLng _actPos;
	private Timer _timer;
	private double _radius;
	private List<Marker> _eggs;
	
	public Map(LatLng actPos) {
		super(MapFactory.createMapOptions(actPos));
		_eggs = new LinkedList<Marker>();
		_actPos = actPos;
		_radius = MIN_RADIUS;
		_position = MapFactory.createPosition(this, actPos);
		_timer = new Timer() {
			@Override
			public void run() {
				drawRadar(_radius+=10);
				if(_radius>MAX_RADIUS)
					_radius = MIN_RADIUS;
			}
		};
		_timer.scheduleRepeating(200);
	}
	
	public void updatePosition(LatLng actPos) {
		_actPos = actPos;
		// call this function to update the position
		_position.setPosition(actPos);
		this.getMapJSO().setCenter(actPos);
	}
	
	public void setEggs(List<EggDto> eggs) {
		// remove old eggs
		for (Marker egg : _eggs) {
			egg.setMap(null);
		}
		_eggs.clear();
		// add new eggs
		for (EggDto egg : eggs) {
			Marker eggMarker = MapFactory.createEgg(this, LatLng.newInstance(egg.getLatitude(), egg.getLongitude()));
			_eggs.add(eggMarker);
		}
	}
	
	public LatLng getPosition() {
		return _actPos;
	}
	
	private void drawRadar(double radius) {
		if(_radar!=null) {
			_radar.setMap(null);
		}
		_radar = MapFactory.createRadar(this, _actPos, radius);
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
