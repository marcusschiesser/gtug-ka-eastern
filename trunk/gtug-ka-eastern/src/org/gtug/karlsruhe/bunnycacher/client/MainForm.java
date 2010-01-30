package org.gtug.karlsruhe.bunnycacher.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.googlecode.maps3.client.LatLng;
import com.googlecode.maps3.client.LatLngBounds;
import com.googlecode.maps3.client.MapWidget;
import com.googlecode.maps3.client.Marker;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainForm extends DockPanel {
	Button newEggButton;
	HorizontalPanel toolBarPanel;

	public MainForm() {
		toolBarPanel=new HorizontalPanel ();
		add(toolBarPanel,NORTH);
		newEggButton =new Button("neues Ei verstecken",new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				new NewEggPopup().show();
				//doAlert();
			}
		});
		toolBarPanel.add(newEggButton);
		LatLng actPos = LatLng.newInstance(49.001971,8.38304);
		Map map = new Map(actPos);
		add(map.getMap(), CENTER);
		// call this function to update the position
		map.updatePosition(LatLng.newInstance(49, 8.383));
	}
	
	public native static void doAlert() /*-{
		alert("ok");
	}-*/;

}