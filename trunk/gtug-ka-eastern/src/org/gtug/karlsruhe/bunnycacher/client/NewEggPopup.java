package org.gtug.karlsruhe.bunnycacher.client;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;
import org.gtug.karlsruhe.phonegap.client.Notification;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.maps3.client.LatLng;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class NewEggPopup extends DialogBox {

	private DockPanel panel;
	private DialogBox myself = this;

	public NewEggPopup(final LatLng actPos) {
		final TextBox hintTextInput = new TextBox();
		
		setText("neues Ei verstecken");
		// Enable animation.
		setAnimationEnabled(true);
		// Enable glass background.
		setGlassEnabled(true);
		Button ok = new Button("OK");
		ok.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// TOOD: daten an webservice übergeben
				EggDto egg=new EggDto(actPos.getLatitude(), actPos.getLongitude(), hintTextInput.getText());
				
				// when not running in development mode, i.e. within Phonegap
				// the URL is a file-URL
				// direct RPC calls to http://bunnycacher.appspot.com/bunnycasher/GWT.rpc
				ServiceDefTarget endpoint = (ServiceDefTarget)Application.eggService;
				String rpcUrl = endpoint.getServiceEntryPoint();
				// Window.alert("rpcURL: " + rpcUrl);
				if (rpcUrl.startsWith("file:")) {
					// set new rpcURL
					endpoint.setServiceEntryPoint("http://bunnycacher.appspot.com/bunnycasher/GWT.rpc");
				}
				
				Application.eggService.createEgg(egg, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Notification.alert("Callback exception: " + caught.toString() + " : " + caught.getMessage(), "Error", ":("); // TODO: externalize strings
					}

					@Override
					public void onSuccess(Void result) {
						myself.hide();
						Notification.alert("Your egg has been saved.", "We likez Eggs", ":)"); // TODO: externalize strings
					}
					
				});
			}
		});
		
		panel = new DockPanel();
		panel.add(ok, DockPanel.NORTH);
		panel.add(hintTextInput, DockPanel.SOUTH);
		setWidget(panel);
	}

}