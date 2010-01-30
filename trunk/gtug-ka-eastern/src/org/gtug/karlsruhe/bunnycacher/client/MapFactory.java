package org.gtug.karlsruhe.bunnycacher.client;

import com.googlecode.maps3.client.LatLng;
import com.googlecode.maps3.client.MapOptions;
import com.googlecode.maps3.client.MapWidget;
import com.googlecode.maps3.client.Marker;

public class MapFactory {
	public static MapWidget createMap(LatLng latLng) {
		MapOptions opts = MapOptions.newInstance();
		opts.setMapTypeId();
		opts.setCenter(latLng);
		opts.setZoom(14);	
		MapWidget map = new MapWidget(opts);
		map.setWidth("100%");
		map.setHeight("100%");
		return map;
	}
	
	public static Marker createPosition(MapWidget map, LatLng latLng) {
		Marker pos = Marker.newInstance();
		pos.setPosition(latLng);
		pos.setMap(map.getMapJSO());
		return pos;
	}
	
	public static Circle createCircle(MapWidget map, LatLng pos, double radius) {
		Circle circle = Circle.newInstance(pos.getLatitude(), pos.getLongitude());
		circle.setMap(map.getMapJSO());
		return circle;
	}
}
