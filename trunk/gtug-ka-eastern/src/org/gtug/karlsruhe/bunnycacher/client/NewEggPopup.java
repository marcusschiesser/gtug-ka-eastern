package org.gtug.karlsruhe.bunnycacher.client;

import org.gtug.karlsruhe.bunnycacher.common.Egg;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class NewEggPopup extends DialogBox {

	private DockPanel panel;
	private DialogBox myself = this;

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
				Application.eggService.createEgg(egg, new AsyncCallback() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Callback exception");
					}

					@Override
					public void onSuccess(Object result) {
						// TODO Auto-generated method stub
						myself.hide();
						
					}
					
				});
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