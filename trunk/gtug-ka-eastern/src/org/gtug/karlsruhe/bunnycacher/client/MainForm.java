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
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.maps3.client.LatLng;
import com.googlecode.maps3.client.LatLngBounds;
import com.googlecode.maps3.client.MapEventType;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainForm extends Composite {
	private static MainFormUiBinder uiBinder = GWT.create(MainFormUiBinder.class);

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
	HTMLPanel cardFront;
	@UiField
	HTMLPanel cardBack;
	
	public MainForm() {
		initWidget(uiBinder.createAndBindUi(this));
		showMain(true);
		
		map.addListener(MapEventType.TILESLOADED, new Runnable() {
			@Override
			public void run() {
				LatLngBounds bounds = map.getMapJSO().getBounds();
			    double minLat = bounds.getSouthWest().getLatitude();
			    double maxLat = bounds.getNorthEast().getLatitude();
			    double minLng = bounds.getSouthWest().getLongitude();
			    double maxLng = bounds.getNorthEast().getLongitude();
				Application.eggService.getEggsWithin(minLat, maxLat, minLng, maxLng, new AsyncCallback<List<EggDto>>() {
					
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
		Geolocation.watchPosition( new PositionCallback(){
			@Override
			public void onPosition(Position value) {
				newEggButton.setEnabled(true);
				map.updatePosition(LatLng.newInstance(value.getLatitude(), value.getLongitude()));
			}
		});
	}

	@UiHandler("newEggButton")
	public void onClick(ClickEvent event) {
		newEggView.setPosition(map.getPosition());
		this.flipCard();
	}
	@UiFactory Map makeMap() {
		return new Map(LatLng.newInstance(49.001971,8.38304));
	}
	@UiFactory NewEggView makeNewEggView() {
		return new NewEggView(this);
	}

	public void flipCard() {
		Element card = Document.get().getElementById("card");
		if (card.getClassName().equals("cardCard")) {
			showMain(true);
			card.setClassName("cardCard cardCardFlipped");
		} else {
			showMain(false);
			card.setClassName("cardCard");
		}
	}
	
	/**
	 * Shows the main form and hiddens the new egg view on non-WebKit browser or visa versa.
	 * 
	 * @param show
	 */
	private void showMain(boolean show) {
		if (!"WebKit".contains(getUserAgent())) {
			cardFront.setVisible(show);
			cardBack.setVisible(!show);
		}
	}
	
	public static native String getUserAgent() /*-{
		return navigator.userAgent.toLowerCase();
	}-*/;
}
