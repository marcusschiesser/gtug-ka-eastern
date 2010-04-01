package org.gtug.karlsruhe.bunnycacher.client.domain;


import com.googlecode.maps3.client.LatLng;
import com.googlecode.maps3.client.MapOptions;
import com.googlecode.maps3.client.MapWidget;
import com.googlecode.maps3.client.Marker;
import com.googlecode.maps3.client.MarkerImage;
import com.googlecode.maps3.client.Point;

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
	
	public static MapOptions createMapOptions(LatLng latLng) {
		MapOptions opts = MapOptions.newInstance();
		opts.setMapTypeId();
		opts.setCenter(latLng);
		opts.setZoom(14);
		return opts;
	}
	
	public static Marker createPosition(MapWidget map, LatLng latLng) {
		Marker pos = Marker.newInstance();
		pos.setPosition(latLng);
		pos.setIcon("icons/rabbiticon.png");
		pos.setMap(map.getMapJSO());
		return pos;
	}

	public static Marker createEgg(MapWidget map, LatLng latLng) {
		Marker pos = Marker.newInstance();
		pos.setPosition(latLng);
		MarkerImage icon = MarkerImage.newInstance("icons/eggicon.png", Point.newInstance(32/2, 32/2));
		pos.setIcon(icon);
		pos.setMap(map.getMapJSO());
		return pos;
	}

	public static Ellipse createRadar(MapWidget map, LatLng pos, double radius) {
		Ellipse circle = Ellipse.newInstance(pos, 1.7*radius, radius, "#0000ff");
		circle.setMap(map.getMapJSO());
		return circle;
	}

}
