package org.gtug.karlsruhe.phonegap.client;

import com.google.gwt.core.client.JavaScriptObject;

public class Position extends JavaScriptObject {
	/** Required by overlays */
	protected Position() {}

	/** Creation */
	public static native Position newInstance() /*-{
		return { coords:{} };
	}-*/;

	public final native void setLatitude(double value) /*-{ this.coords.latitude = value; }-*/;
	public final native void setLongitude(double value) /*-{ this.coords.longitude = value; }-*/;
	public final native void setAltitude(double value) /*-{ this.coords.altitude = value; }-*/;
	public final native void setAccuracy(double value) /*-{ this.coords.accuracy = value; }-*/;
	public final native void setAltitudeAccuracy(double value) /*-{ this.coords.altitudeAccuracy = value; }-*/;
	public final native void setHeading(double value) /*-{ this.coords.heading = value; }-*/;
	public final native void setSpeed(double value) /*-{ this.coords.speed = value; }-*/;
	
	public final native double getLatitude() /*-{ return this.coords.latitude; }-*/;
	public final native double getLongitude() /*-{ return this.coords.longitude; }-*/;
	public final native double getAltitude() /*-{ return this.coords.altitude; }-*/;
	public final native double getAccuracy() /*-{ return this.coords.accuracy; }-*/;
	public final native double getAltitudeAccuracy() /*-{ return this.coords.altitudeAccuracy; }-*/;
	public final native double getHeading() /*-{ return this.coords.heading; }-*/;
	public final native double getSpeed() /*-{ return this.coords.speed; }-*/;
}
