package org.gtug.karlsruhe.bunnycacher.client;

import org.gtug.karlsruhe.bunnycacher.client.res.Resources;
import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;
import org.gtug.karlsruhe.phonegap.client.Notification;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.maps3.client.LatLng;

public class NewEggView extends Composite {

	private static NewEggViewUiBinder uiBinder = GWT.create(NewEggViewUiBinder.class);

	private abstract class AsyncCallbackImplementation<T> implements
			AsyncCallback<T> {
		@Override
		public void onFailure(Throwable caught) {
			Notification.alert("Callback exception: " + caught.toString() + " : " + caught.getMessage(), "Error", ":("); // TODO: externalize strings
			NewEggView.this.parentForm.flipCard();
		}

	}

	interface NewEggViewUiBinder extends UiBinder<Widget, NewEggView> {}

	@UiField
	SpanElement eidSpan;
	
	@UiField
	Button okButton;
	
	@UiField
	Button cancelButton;
	
	@UiField
	HasText hintText;
	
	@UiField
	HasText tagText;
	
	@UiField(provided = true)
	final Resources resources = Resources.INSTANCE;

	private LatLng actPos;
	private MainForm parentForm;
	private Integer eid;
	
	public NewEggView(MainForm form) {
		this.parentForm = form;
		initWidget(uiBinder.createAndBindUi(this));
		Application.eggService.reserveEid(new AsyncCallbackImplementation<Integer>() {
			@Override
			public void onSuccess(Integer result) {
				setEid(result);
			}
		});
	}
	
	private void setEid(Integer eid) {
		this.eid = eid;
		eidSpan.setInnerText(Integer.toString(eid));
	}
	
	public void setPosition(LatLng actPos) {
		this.actPos = actPos;
	}

	@UiHandler("okButton")
	void onOkClick(ClickEvent e) {
		// TOOD: daten an webservice übergeben
		EggDto egg=new EggDto(actPos.getLatitude(), actPos.getLongitude(), hintText.getText());
		egg.setEid(eid);
		
		Application.eggService.createEgg(egg, new AsyncCallbackImplementation<Void>() {

			@Override
			public void onSuccess(Void result) {
				AsyncCallback<Void> callback = new AsyncCallbackImplementation<Void>() {
					@Override
					public void onSuccess(Void result) {
						NewEggView.this.parentForm.flipCard();
						Notification.alert("Your egg has been saved.", "We likez Eggs", ":)"); // TODO: externalize strings
					}
				};
				if(!tagText.getText().isEmpty()) {
					// user left a tag, store it
					Application.eggService.createTag(eid, tagText.getText(), callback);				
				} else {
					callback.onSuccess(null);
				}
			}
			
		});
	}
	
	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent e) {
		this.parentForm.flipCard();
	}

}
