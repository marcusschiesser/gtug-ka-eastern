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
		map.setWidth("500px");
		map.setHeight("500px");
		return map;
	}
	
	public static Marker createPosition(MapWidget map, LatLng latLng) {
		Marker pos = Marker.newInstance();
		pos.setPosition(latLng);
		pos.setMap(map.getMapJSO());
		return pos;
	}
}
