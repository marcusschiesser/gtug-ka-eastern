package org.gtug.karlsruhe.bunnycacher.client.view;

import java.util.LinkedList;
import java.util.List;

import org.gtug.karlsruhe.bunnycacher.client.domain.MapFactory;
import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;
import org.gtug.karlsruhe.phonegap.client.Geolocation;

import com.googlecode.maps3.client.LatLng;
import com.googlecode.maps3.client.MapWidget;
import com.googlecode.maps3.client.Marker;

public class Map extends MapWidget {

	private LatLng _actPos;
	private Marker _position;
	private List<MyEgg> _eggs;

	class MyEgg {
		MyEgg(EggDto data, Marker marker) {
			this.data = data;
			this.marker = marker;
		}
		EggDto data;
		Marker marker;
	}

	public Map(LatLng actPos) {
		super(MapFactory.createMapOptions(actPos));
		_eggs = new LinkedList<MyEgg>();
		_actPos = actPos;
		_position = MapFactory.createPosition(this, actPos);
	}

	public void updatePosition(MainForm mainForm, LatLng actPos) {
		_actPos = actPos;
		// call this function to update the position
		_position.setPosition(actPos);
		this.getMapJSO().setCenter(actPos);
		// find nearest egg
		double minDistance = Double.MAX_VALUE;
		MyEgg minEgg = null;
		for (MyEgg egg : _eggs) {
			double distance = Geolocation.distance(actPos.getLatitude(), actPos
					.getLongitude(), egg.data.getLatitude(), egg.data
					.getLongitude());
			if (distance < minDistance) {
				minEgg = egg;
				minDistance = distance;
			}
		}
		// display hint if nearest egg is nearby (10m)
		if (minEgg!=null && minDistance < 0.1) {
			HintPopup.showPopup(mainForm, minEgg.data);
		}
	}

	public void setEggs(List<EggDto> eggs) {
		// remove old eggs

		for (MyEgg egg : _eggs) {
			egg.marker.setMap(null);
		}
		_eggs.clear();
		// add new eggs
		for (EggDto egg : eggs) {
			Marker eggMarker = MapFactory.createEgg(this, LatLng.newInstance(
					egg.getLatitude(), egg.getLongitude()));
			_eggs.add(new MyEgg(egg, eggMarker));
		}
	}

	public LatLng getPosition() {
		return _actPos;
	}

}
