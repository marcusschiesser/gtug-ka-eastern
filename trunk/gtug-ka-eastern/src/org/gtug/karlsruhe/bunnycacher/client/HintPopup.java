package org.gtug.karlsruhe.bunnycacher.client;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HintPopup extends PopupPanel {

	private Label hintLabel;
	private Button foundButton;
	private EggDto data;
	private Button notFoundButton;
	
	private static HintPopup _instance;
	private HintPopup(final MainForm mainForm) {
		super(true);
		VerticalPanel container = new VerticalPanel();
		hintLabel = new Label();
		container.add(hintLabel);
		foundButton = new Button("Ja, Ei gefunden!");
		container.add(foundButton);
		notFoundButton = new Button("Nein, noch nicht...");
		container.add(notFoundButton);		
		add(container);
		this.center();
		notFoundButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				HintPopup.this.hide();			
			}
		});
		foundButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				HintPopup.this.hide();
				mainForm.foundEggView.setData(data);
				mainForm.flipCard(BackSideOfCard.FOUND_EGG_VIEW);
			}
		});
	}
	
	public static void showPopup(MainForm mainForm, EggDto pData) {
		if(_instance==null) {
			_instance = new HintPopup(mainForm);
		}
		_instance.setData(pData);
		_instance.show();
	}

	private void setData(EggDto pData) {
		this.data = pData;
		hintLabel.setText("Das Ei in deiner Nähe sagt: " + data.getHint() + ". Hast du es gefunden?");
	}
	
}
