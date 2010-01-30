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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.maps3.client.LatLng;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class NewEggPopup extends DialogBox {

	private DockPanel panel;
	private DialogBox myself = this;

	public NewEggPopup(final LatLng actPos) {
		final TextArea hintTextInput = new TextArea();
		
		setText("Neues Ei verstecken");
		// Enable animation.
		setAnimationEnabled(true);
		// Enable glass background.
		setGlassEnabled(true);
		Button ok = new Button("Ei verstecken");
		ok.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// TOOD: daten an webservice übergeben
				EggDto egg=new EggDto(actPos.getLatitude(), actPos.getLongitude(), hintTextInput.getText());
				
				Application.eggService.createEgg(egg, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Notification.alert("Callback exception: " + caught.toString() + " : " + caught.getMessage(), "Error", ":("); // TODO: externalize strings
						myself.hide();
					}

					@Override
					public void onSuccess(Void result) {
						myself.hide();
						Notification.alert("Your egg has been saved.", "We likez Eggs", ":)"); // TODO: externalize strings
					}
					
				});
			}
		});
		Button cancel = new Button("Abbruch");
		cancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				myself.hide();
			}
		});
		HorizontalPanel buttons = new HorizontalPanel();
		buttons.add(ok);
		buttons.add(cancel);
		VerticalPanel center = new VerticalPanel();
		center.add(new HTML("<div class='hint'>Hier kannst Du ein Osterei, das Du an der aktuellen Position versteckt hast, für andere zum Finden eintragen. <br /><br />Gib einen Hinweis für die Suchenden an:</div>", true));
		hintTextInput.setCharacterWidth(40);
		hintTextInput.setVisibleLines(3);
		center.add(hintTextInput);
		panel = new DockPanel();
		panel.add(center, DockPanel.CENTER);
		panel.add(buttons, DockPanel.SOUTH);
		setWidget(panel);
	}

}