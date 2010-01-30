package org.gtug.karlsruhe.bunnycacher.client;

import org.gtug.karlsruhe.bunnycacher.common.domain.Egg;
import org.gtug.karlsruhe.phonegap.client.Geolocation;
import org.gtug.karlsruhe.phonegap.client.PositionSuccessCallback;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.googlecode.maps3.client.LatLng;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainForm extends DockLayoutPanel {
	private Button newEggButton;
	private Button updatePosButton;
	private HorizontalPanel toolBarPanel;
	private Map _map;
	private InlineLabel debugLabel;
	
	public MainForm() {
		super(Unit.EM);
		setWidth("100%");
		setHeight("100%");
		toolBarPanel=new HorizontalPanel ();
		addNorth(toolBarPanel, 2);
		newEggButton =new Button("neues Ei verstecken",new ClickHandler() {			
			public void onClick(ClickEvent event) {
				new NewEggPopup(_map.getPosition()).show();
			}
		});
		updatePosButton = new Button("update Pos", new ClickHandler() {
			public void onClick(ClickEvent event) {
				// call this function to update the position
				_map.updatePosition(LatLng.newInstance(49, 8.383));
			}
		});
		toolBarPanel.add(newEggButton);
		toolBarPanel.add(updatePosButton); 
		LatLng actPos = LatLng.newInstance(49.001971,8.38304);
		_map = new Map(actPos);
		add(_map.getMap());
		
		debugLabel = new InlineLabel("Debug");
		toolBarPanel.add(debugLabel);
		Geolocation.watchPosition( new PositionSuccessCallback(){
			public void onPosition(double lat, double lon) {
				_map.updatePosition(LatLng.newInstance(lat, lon));
				debugLabel.setText("Lat: " + lat + " Lon: " + lon);
				Application.eggService.getEggsWithin(lat,lon, new AsyncCallback<Egg[]>() {
					
					@Override
					public void onSuccess(Egg[] eggs) {
						_map.setEggs(eggs);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						
					}
				});
			}
		});
	}

}