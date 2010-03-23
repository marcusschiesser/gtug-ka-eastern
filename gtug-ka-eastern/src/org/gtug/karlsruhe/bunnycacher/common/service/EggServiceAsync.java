package org.gtug.karlsruhe.bunnycacher.common.service;

import java.util.List;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;
import org.gtug.karlsruhe.bunnycacher.common.domain.TagDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EggServiceAsync {

	void createEgg(EggDto egg, AsyncCallback<Void> callback);

	void getEggsWithin(double minLat, double maxLat, double minLng,
			double maxLng, AsyncCallback<List<EggDto>> callback);

	void reserveEid(AsyncCallback<Integer> callback);

	void createTag(Integer eid, String message, AsyncCallback<Void> callback);

	void foundEgg(EggDto egg, String message, AsyncCallback<Void> callback);

	void getTags(EggDto egg, AsyncCallback<List<TagDto>> callback);

}
