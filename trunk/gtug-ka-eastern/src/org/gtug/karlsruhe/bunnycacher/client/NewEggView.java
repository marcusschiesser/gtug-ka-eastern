package org.gtug.karlsruhe.bunnycacher.client;

import org.gtug.karlsruhe.bunnycacher.client.res.Resources;
import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;
import org.gtug.karlsruhe.phonegap.client.Notification;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.maps3.client.LatLng;

public class NewEggView extends Composite {

	private static NewEggViewUiBinder uiBinder = GWT.create(NewEggViewUiBinder.class);

	interface NewEggViewUiBinder extends UiBinder<Widget, NewEggView> {}

	@UiField
	Button okButton;
	
	@UiField
	Button cancelButton;
	
	@UiField
	HasText hintText;
	
	@UiField(provided = true)
	final Resources resources = Resources.INSTANCE;

	private LatLng actPos;
	private MainForm parentForm;
	
	public NewEggView(MainForm form) {
		this.parentForm = form;
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setPosition(LatLng actPos) {
		this.actPos = actPos;
	}

	@UiHandler("okButton")
	void onOkClick(ClickEvent e) {
		// TOOD: daten an webservice übergeben
		EggDto egg=new EggDto(actPos.getLatitude(), actPos.getLongitude(), hintText.getText());
		
		Application.eggService.createEgg(egg, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Notification.alert("Callback exception: " + caught.toString() + " : " + caught.getMessage(), "Error", ":("); // TODO: externalize strings
				NewEggView.this.parentForm.flipCard();
			}

			@Override
			public void onSuccess(Void result) {
				NewEggView.this.parentForm.flipCard();
				Notification.alert("Your egg has been saved.", "We likez Eggs", ":)"); // TODO: externalize strings
			}
			
		});
	}
	
	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent e) {
		this.parentForm.flipCard();
	}

}
