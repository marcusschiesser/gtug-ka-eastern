package org.gtug.karlsruhe.bunnycacher.client;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;

public class HintPopup extends PopupPanel {

	private static HintPopupUiBinder uiBinder = GWT.create(HintPopupUiBinder.class);
	interface HintPopupUiBinder extends UiBinder<Widget, HintPopup> {}
	
	@UiField
	HasText hintLabel;
	@UiField
	HasClickHandlers foundButton;
	@UiField
	HasClickHandlers notFoundButton;
	
	private EggDto data;
	private MainForm mainForm;
	
	private static HintPopup _instance;
	private static boolean _enabled = true;
	private HintPopup(final MainForm mainForm) {
		super(false);
		this.mainForm = mainForm;
		setWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("foundButton")
	void onFoundButtonClick(ClickEvent e) {
		this.hide();
		mainForm.foundEggView.setData(data);
		mainForm.flipCard(BackSideOfCard.FOUND_EGG_VIEW);
	}
	@UiHandler("notFoundButton")
	void onNotFoundButtonClick(ClickEvent e) {
		this.hide();
	}
	
	
	
	public static void showPopup(MainForm mainForm, EggDto pData) {
		if(_instance==null) {
			_instance = new HintPopup(mainForm);
		}
		if (_enabled) {
			_instance.setData(pData);
			_instance.getElement().getStyle().setPropertyPx("width", Window.getClientWidth() - 10);
			_instance.setPopupPositionAndShow(new PositionCallback() {
		      public void setPosition(int offsetWidth, int offsetHeight) {
		    	  _instance.setPopupPosition(0, Window.getClientHeight() - offsetHeight);
		      }
		    });
		}
	}
	
	public static void enablePopup() {
		_enabled = true;
	}
	
	public static void disablePopup() {
		_enabled = false;
		if (_instance!=null) {
			_instance.hide();
		}
	}

	private void setData(EggDto pData) {
		this.data = pData;
		hintLabel.setText(data.getHint());
	}
	
}
