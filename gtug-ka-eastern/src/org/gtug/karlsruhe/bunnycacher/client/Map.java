package org.gtug.karlsruhe.bunnycacher.client;

import com.googlecode.maps3.client.LatLng;
import com.googlecode.maps3.client.MapWidget;
import com.googlecode.maps3.client.Marker;

public class Map {

	private MapWidget _mapWidget;
	private Marker _position;
	
	public Map(LatLng actPos) {
		_mapWidget = MapFactory.createMap(actPos);
		_position = MapFactory.createPosition(_mapWidget, actPos);
	}
	
	public MapWidget getMap() {
		return _mapWidget;
	}
	
	public void updatePosition(LatLng actPos) {
		// call this function to update the position
		_position.setPosition(actPos);
		_mapWidget.getMapJSO().setCenter(actPos);
	}

}
