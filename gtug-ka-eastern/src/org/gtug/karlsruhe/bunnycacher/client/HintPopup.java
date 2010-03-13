package org.gtug.karlsruhe.bunnycacher.client;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HintPopup extends PopupPanel {

	private Label hintLabel;
	private Button foundButton;
	private EggDto data;
	
	private static HintPopup _instance;
	
	private HintPopup() {
		super(true);
		VerticalPanel container = new VerticalPanel();
		hintLabel = new Label();
		container.add(hintLabel);
		foundButton = new Button("Ja, Ei gefunden!");
		container.add(foundButton);
		add(container);
		this.center();
		foundButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				HintPopup.this.hide();
				Application.eggService.foundEgg(data, "TODO", new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}
					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
					}
				});
			}
		});
	}
	
	public static void showPopup(EggDto pData) {
		if(_instance==null) {
			_instance = new HintPopup();
		}
		_instance.setData(pData);
		_instance.show();
	}

	private void setData(EggDto pData) {
		this.data = pData;
		hintLabel.setText("Das Ei in deiner Nähe sagt: " + data.getHint() + ". Hast du es gefunden?");
	}
	
}
