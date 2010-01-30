package org.gtug.karlsruhe.bunnycacher.common.service;

import java.util.List;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EggServiceAsync {

	void createEgg(EggDto egg, AsyncCallback<Void> callback);

	void getEggsWithin(double latitude, double longitude, AsyncCallback<List<EggDto>> callback);

}
