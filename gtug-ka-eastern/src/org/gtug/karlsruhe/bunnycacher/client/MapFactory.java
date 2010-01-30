package org.gtug.karlsruhe.bunnycacher.client;

import com.googlecode.maps3.client.LatLng;
import com.googlecode.maps3.client.MapOptions;
import com.googlecode.maps3.client.MapWidget;

public class MapFactory {
	public static MapWidget createMap() {
		MapOptions opts = MapOptions.newInstance();
		opts.setMapTypeId();
		opts.setCenter(LatLng.newInstance(37.760773,-122.434448));
		opts.setZoom(14);	
		return new MapWidget(opts);
	}
}
