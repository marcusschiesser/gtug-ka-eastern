package org.gtug.karlsruhe.bunnycacher.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.googlecode.maps3.client.LatLng;
import org.gtug.karlsruhe.bunnycacher.client.res.Resources;
import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;
import org.gtug.karlsruhe.bunnycacher.common.domain.TagDto;
import org.gtug.karlsruhe.phonegap.client.Notification;

public class FoundEggView extends Composite {

	private static FoundEggViewUiBinder uiBinder = GWT
			.create(FoundEggViewUiBinder.class);
	private EggDto eggDto;

	private abstract class AsyncCallbackImplementation<T> implements
			AsyncCallback<T> {
		@Override
		public void onFailure(Throwable caught) {
			Notification.alert("Callback exception: " + caught.toString()
					+ " : " + caught.getMessage(), "Error", ":(");
			FoundEggView.this.parentForm.flipCard(BackSideOfCard.FRONT_SIDE);
		}

	}

	interface FoundEggViewUiBinder extends UiBinder<Widget, FoundEggView> {
	}

	@UiField
	DivElement otherTagsText;

	@UiField
	Button okButton;

	@UiField
	HasText newTagText;

	@UiField(provided = true)
	final Resources resources = Resources.INSTANCE;

	private MainForm parentForm;

	public FoundEggView(MainForm form) {
		this.parentForm = form;
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("okButton")
	void onOkClick(ClickEvent e) {
		Application.eggService.foundEgg(eggDto, newTagText.getText(),
				new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Notification.alert("Eieiei",
								"Etwas ging schief beim spEIchern", ":-(");
					}

					@Override
					public void onSuccess(Void result) {
						FoundEggView.this.parentForm
								.flipCard(BackSideOfCard.FRONT_SIDE);
					}
				});
	}

	public void setData(EggDto eggDto) {
		this.eggDto = eggDto;
		Application.eggService.getTags(eggDto, new AsyncCallback<List<TagDto>>() {

			@Override
			public void onSuccess(List<TagDto> tags) {
				StringBuffer sb = new StringBuffer();
				for (TagDto tag : tags) {
					sb.append(tag.getMessage() + "<br/>");
				}
				otherTagsText.setInnerHTML(sb.toString());
			}

			@Override
			public void onFailure(Throwable caught) {
				Notification.alert("Eieiei",
						"Etwas ging schief beim laden der vorhandenen EI-Tags",
						":-(");

			}
		});

	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent e) {
		this.parentForm.flipCard(BackSideOfCard.FRONT_SIDE);
	}

}
