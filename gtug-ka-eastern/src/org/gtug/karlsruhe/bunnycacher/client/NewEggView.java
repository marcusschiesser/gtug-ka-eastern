package org.gtug.karlsruhe.bunnycacher.client;

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
import org.gtug.karlsruhe.phonegap.client.Notification;

public class NewEggView extends Composite {

    private static NewEggViewUiBinder uiBinder = GWT.create(NewEggViewUiBinder.class);

    private abstract class AsyncCallbackImplementation<T> implements
            AsyncCallback<T> {
        @Override
        public void onFailure(Throwable caught) {
            Notification.alert("Callback exception: " + caught.toString() + " : " + caught.getMessage(), "Error", ":("); // TODO: externalize strings
            NewEggView.this.parentForm.flipCard(BackSideOfCard.FRONT_SIDE);
        }

    }

    interface NewEggViewUiBinder extends UiBinder<Widget, NewEggView> {
    }

    @UiField
    SpanElement eidSpan;

    @UiField
    DivElement loginPrompt;

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
    private long eid;

    public NewEggView(MainForm form) {
        this.parentForm = form;
        initWidget(uiBinder.createAndBindUi(this));
        Application.eggService.reserveEid(new AsyncCallbackImplementation<Long>() {
            @Override
            public void onSuccess(Long result) {
                setEid(result);
            }
        });

        Application.loginService.getLoginInfo(GWT.getHostPageBaseURL(), new AsyncCallbackImplementation<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo result) {
                if (!result.isLoggedIn()) {
                    okButton.setEnabled(false);
                    loginPrompt.setInnerHTML("Bitte <a href='" + result.getLoginUrl() + "'>einloggen</a>, um ein Ei zu verstecken.");
                }
            }
        });

    }

    private void setEid(Long eid) {
        this.eid = eid;
        eidSpan.setInnerText(String.valueOf(eid));
    }

    public void setPosition(LatLng actPos) {
        this.actPos = actPos;
    }

    @UiHandler("okButton")
    void onOkClick(ClickEvent e) {
        // TOOD: daten an webservice übergeben
        EggDto egg = new EggDto(eid, actPos.getLatitude(), actPos.getLongitude(), hintText.getText());

        Application.eggService.createEgg(egg, new AsyncCallbackImplementation<Void>() {

            @Override
            public void onSuccess(Void result) {
                AsyncCallback<Void> callback = new AsyncCallbackImplementation<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        NewEggView.this.parentForm.flipCard(BackSideOfCard.FRONT_SIDE);
                        Notification.alert("Your egg has been saved.", "We likez Eggs", ":)"); // TODO: externalize strings
                    }
                };
                if (!tagText.getText().isEmpty()) {
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
        this.parentForm.flipCard(BackSideOfCard.FRONT_SIDE);
    }

}
