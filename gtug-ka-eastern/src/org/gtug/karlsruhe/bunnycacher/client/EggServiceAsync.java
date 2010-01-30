package org.gtug.karlsruhe.bunnycacher.client;

import org.gtug.karlsruhe.bunnycacher.common.Egg;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EggServiceAsync {
	
	void createEgg(Egg egg, AsyncCallback<Void> callback);

}
