package org.gtug.karlsruhe.bunnycacher.common.service;

import org.gtug.karlsruhe.bunnycacher.common.domain.Egg;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EggServiceAsync {

	void createEgg(Egg egg, AsyncCallback<Void> callback);

	void getEggsWithin(double latitude, double longitude, AsyncCallback<Egg[]> callback);
}
