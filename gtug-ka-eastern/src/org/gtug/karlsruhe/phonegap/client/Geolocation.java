package org.gtug.karlsruhe.phonegap.client;

import com.google.gwt.core.client.JavaScriptObject;

public class Geolocation extends JavaScriptObject {
	/** Required for Overlay types */
	protected Geolocation() {
	}

	/** */
	public static native int watchPosition(
			PositionCallback callback) /*-{
		return $wnd.navigator.geolocation.watchPosition(function(position) {
			function isPositionChanged(oldPosition, newPosition){ // TODO: refactor: move this to GWT code, not native JavaScript code
				// http://www.movable-type.co.uk/scripts/latlong.html
				// Spherical Law of Cosines
				var R = 6371, // km
					lat1 = oldPosition.coords.latitude  * Math.PI / 180, 
					lon1 = oldPosition.coords.longitude * Math.PI / 180, 
					lat2 = newPosition.coords.latitude  * Math.PI / 180, 
					lon2 = newPosition.coords.longitude * Math.PI / 180; 
				var d = Math.acos(Math.sin(lat1)*Math.sin(lat2) + 
		          Math.cos(lat1)*Math.cos(lat2) *
		          Math.cos(lon2-lon1)) * R;
				return d > 0.005; // distance bigger than 5 meters
				// TODO: fine-tune distance
			}
			var now = (new Date()).getTime();
			if (now - position.timestamp > 60 * 60 * 5) {
				// TODO: debug log -> stale position
			} else {
				if (!$wnd.navigator.geolocation.savedPosition || isPositionChanged($wnd.navigator.geolocation.savedPosition, position)) {
					// poor man's deep copy
					$wnd.navigator.geolocation.savedPosition = {
						coords : {
							latitude : position.coords.latitude,
							longitude : position.coords.longitude,
							altitude : position.coords.altitude,
							accuracy : position.coords.accuracy,
							altitudeAccuracy : position.coords.altitudeAccuracy,
							heading : position.coords.heading,
							speed : position.coords.speed
						},
						timestamp : position.timestamp
					};
					callback.@org.gtug.karlsruhe.phonegap.client.PositionCallback::onPosition(Lorg/gtug/karlsruhe/phonegap/client/Position;)(position);
				}
			}
		});
	}-*/;
}
