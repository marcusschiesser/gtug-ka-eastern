package org.gtug.karlsruhe.bunnycacher.client.res;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {
	Resources INSTANCE = GWT.create(Resources.class);

	@Source("new-egg.png")
	ImageResource newEgg();
		
	// This is the way it should be but GWT's CSS-parser does not
	// understand the -webkit CSS additions so we need to bypass the parser.
	// If the parser is smart enough, 
	//  (a) uncomment this interface
	//  (b) remove styles.css from the module XML
	//  (c) use this interface everywhere you used a explicit CSS class name
	//      e.g. instead of "topToolbar" you would use Resources.INSTANCE.style().topToolbar()
	//      in UIBinder-XML files, use {resources.style.topToolbar}
//	@CssResource.NotStrict
//	@Source("styles.css")
//	Style style();
//	public interface Style extends CssResource {
//		String topToolbar();
//		String cardFace();
//		String cardFaceBack();
//		String cardContainer();
//		String cardCard();
//		String cardCardFlipped();
//		String sheet();
//	}

}
