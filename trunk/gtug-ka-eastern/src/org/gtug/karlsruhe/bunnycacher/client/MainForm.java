package org.gtug.karlsruhe.bunnycacher.client;

import java.util.List;

import org.gtug.karlsruhe.bunnycacher.client.res.Resources;
import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;
import org.gtug.karlsruhe.phonegap.client.Geolocation;
import org.gtug.karlsruhe.phonegap.client.Position;
import org.gtug.karlsruhe.phonegap.client.PositionCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.maps3.client.LatLng;
import com.googlecode.maps3.client.LatLngBounds;
import com.googlecode.maps3.client.MapEventType;

enum BackSideOfCard {
	FRONT_SIDE, NEW_EGG_VIEW, FOUND_EGG_VIEW
}

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainForm extends Composite {
	private static MainFormUiBinder uiBinder = GWT
			.create(MainFormUiBinder.class);

	interface MainFormUiBinder extends UiBinder<Widget, MainForm> {
	}

	@UiField
	Map map;

	@UiField
	Button newEggButton;

	@UiField(provided = true)
	final Resources resources = Resources.INSTANCE;

	@UiField
	NewEggView newEggView;

	@UiField
	FoundEggView foundEggView;

	public MainForm() {
		initWidget(uiBinder.createAndBindUi(this));

		map.addListener(MapEventType.TILESLOADED, new Runnable() {
			@Override
			public void run() {
				LatLngBounds bounds = map.getMapJSO().getBounds();
				double minLat = bounds.getSouthWest().getLatitude();
				double maxLat = bounds.getNorthEast().getLatitude();
				double minLng = bounds.getSouthWest().getLongitude();
				double maxLng = bounds.getNorthEast().getLongitude();
				Application.eggService.getEggsWithin(minLat, maxLat, minLng,
						maxLng, new AsyncCallback<List<EggDto>>() {

							@Override
							public void onSuccess(List<EggDto> eggs) {
								map.setEggs(eggs);
							}

							@Override
							public void onFailure(Throwable caught) {

							}
						});
			}
		});
		Geolocation.watchPosition(new PositionCallback() {
			@Override
			public void onPosition(Position value) {
				newEggButton.setEnabled(true);
				map.updatePosition(MainForm.this, LatLng.newInstance(value.getLatitude(),
						value.getLongitude()));
			}
		});
	}

	@UiHandler("newEggButton")
	public void onNewEggButtonClick(ClickEvent event) {
		newEggView.setPosition(map.getPosition());
		this.flipCard(BackSideOfCard.NEW_EGG_VIEW);
	}

	@UiFactory
	Map makeMap() {
		return new Map(LatLng.newInstance(49.001971, 8.38304));
	}

	@UiFactory
	NewEggView makeNewEggView() {
		return new NewEggView(this);
	}

	@UiFactory
	FoundEggView makeFoundEggView() {
		return new FoundEggView(this);
	}

	public void flipCard(BackSideOfCard backSideOfCardEnum) {
		Element card = Document.get().getElementById("card");
		if (backSideOfCardEnum.equals(BackSideOfCard.FRONT_SIDE)) {
			card.setClassName("cardCard");
		} else {
			if (backSideOfCardEnum == BackSideOfCard.FOUND_EGG_VIEW) {
				foundEggView.getElement().getParentElement().removeClassName("cardFaceHidden");
				newEggView.getElement().getParentElement().addClassName("cardFaceHidden");
			} else {
				foundEggView.getElement().getParentElement().addClassName("cardFaceHidden");
				newEggView.getElement().getParentElement().removeClassName("cardFaceHidden");
			}
			card.setClassName("cardCard cardCardFlipped");
		}
	}

}
