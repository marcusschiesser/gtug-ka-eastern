package org.gtug.karlsruhe.bunnycasher.client;

import org.gtug.karlsruhe.bunnycasher.client.common.Egg;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class NewEggPopup extends DialogBox {

	private DockPanel panel;

	public NewEggPopup() {
		setText("neues Ei verstecken");
		// Enable animation.
		setAnimationEnabled(true);
		// Enable glass background.
		setGlassEnabled(true);
		Button ok = new Button("OK");
		ok.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// TOOD: daten an webservice übergeben
				Egg egg=new Egg(42.0, 43.0, "ein hint");
				Gtug_ka_eastern.eggService.greetServer(egg, null);
				NewEggPopup.this.hide();
			}
		});
		TextBox text = new TextBox();
		panel = new DockPanel();
		panel.add(ok, DockPanel.NORTH);
		panel.add(text, DockPanel.SOUTH);
		setWidget(panel);
	}

}