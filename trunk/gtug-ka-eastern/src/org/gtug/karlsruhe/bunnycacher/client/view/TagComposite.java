package org.gtug.karlsruhe.bunnycacher.client.view;

import org.gtug.karlsruhe.bunnycacher.common.domain.TagDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class TagComposite extends Composite {

    private static TagCompositeUiBinder uiBinder = GWT
            .create(TagCompositeUiBinder.class);

    interface TagCompositeUiBinder extends UiBinder<Widget, TagComposite> {
    }

    @UiField
    HasText userLabel;

    @UiField
    HasText tagLabel;
    
    @UiField
    HasText timeLabel;

    public TagComposite(TagDto tag) {
        initWidget(uiBinder.createAndBindUi(this));
        userLabel.setText(tag.getUserId());
        tagLabel.setText(tag.getMessage());
        timeLabel.setText(tag.getTimestamp().toLocaleString());
    }


}
