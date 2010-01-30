package org.gtug.karlsruhe.bunnycasher.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

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
	}
	
	public native static void doAlert() /*-{
		alert("ok");
	}-*/;

}