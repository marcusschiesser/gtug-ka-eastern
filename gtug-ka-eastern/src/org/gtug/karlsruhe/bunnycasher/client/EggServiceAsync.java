package org.gtug.karlsruhe.bunnycasher.client;

import org.gtug.karlsruhe.bunnycasher.common.Egg;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EggServiceAsync {
	
	void greetServer(Egg egg, AsyncCallback<Void> callback);

}
