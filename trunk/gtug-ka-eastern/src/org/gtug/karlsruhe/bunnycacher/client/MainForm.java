package org.gtug.karlsruhe.bunnycacher.client;

import java.util.List;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;
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
	private HorizontalPanel toolBarPanel;
	private Map _map;
	
	public MainForm() {
		super(Unit.PX);
		setWidth("100%");
		setHeight("100%");
		toolBarPanel = new HorizontalPanel();
		toolBarPanel.setStyleName("top-toolbar");
		addNorth(toolBarPanel, 39);
		newEggButton = new Button("neues Ei verstecken",new ClickHandler() {			
			public void onClick(ClickEvent event) {
				new NewEggPopup(_map.getPosition()).show();
			}
		});
		newEggButton.getElement().setId("egg-button");
		toolBarPanel.add(newEggButton);
		LatLng actPos = LatLng.newInstance(49.001971,8.38304);
		_map = new Map(actPos);
		add(_map.getMap());
		
		Geolocation.watchPosition( new PositionSuccessCallback(){
			public void onPosition(double lat, double lon) {
				_map.updatePosition(LatLng.newInstance(lat, lon));
				Application.eggService.getEggsWithin(lat,lon, new AsyncCallback<List<EggDto>>() {
					
					@Override
					public void onSuccess(List<EggDto> eggs) {
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