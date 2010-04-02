package org.gtug.karlsruhe.bunnycacher.client.view;

import java.util.List;

import org.gtug.karlsruhe.bunnycacher.client.Application;
import org.gtug.karlsruhe.bunnycacher.client.res.Resources;
import org.gtug.karlsruhe.bunnycacher.client.util.ScrollUtils;
import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;
import org.gtug.karlsruhe.bunnycacher.common.domain.TagDto;
import org.gtug.karlsruhe.phonegap.client.Notification;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class FoundEggView extends Composite {

	private static FoundEggViewUiBinder uiBinder = GWT
			.create(FoundEggViewUiBinder.class);
	private EggDto eggDto;

	interface FoundEggViewUiBinder extends UiBinder<Widget, FoundEggView> {
	}

	@UiField
	Panel otherTags;

	@UiField
	Button okButton;

	@UiField
	HasText newTagText;
	
	@UiField
	HTMLPanel scroller;

	@UiField(provided = true)
	final Resources resources = Resources.INSTANCE;

	private MainForm parentForm;

	public FoundEggView(MainForm form) {
		this.parentForm = form;
		initWidget(uiBinder.createAndBindUi(this));
		ScrollUtils.setUpScroll(scroller.getElement());
	}

	@UiHandler("okButton")
	void onOkClick(ClickEvent e) {
		Application.eggService.foundEgg(eggDto, newTagText.getText(),
				new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Notification.alert("Etwas ging schief beim spEIchern", "Eieiei", ":-(");
					}

					@Override
					public void onSuccess(Void result) {
						FoundEggView.this.parentForm
								.flipCard(BackSideOfCard.FRONT_SIDE);
					}
				});
	}

	public void setData(EggDto eggDto) {
		otherTags.clear();
		newTagText.setText("");
		this.eggDto = eggDto;
		Application.eggService.getTags(eggDto, new AsyncCallback<List<TagDto>>() {

			@Override
			public void onSuccess(List<TagDto> tags) {
				try {
					for (final TagDto tag : tags) {
						otherTags.add(new TagComposite(tag));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Notification.alert("Etwas ging schief beim Laden der vorhandenen Ei-Tags", 
						"Eieiei", ":-(");
			}
		});

	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent e) {
		this.parentForm.flipCard(BackSideOfCard.FRONT_SIDE);
	}

}
