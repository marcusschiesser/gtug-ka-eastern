package org.gtug.karlsruhe.bunnycacher.common.service;

import java.util.List;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;
import org.gtug.karlsruhe.bunnycacher.common.domain.TagDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("GWT.rpc")
public interface EggService extends RemoteService {

	void createEgg(EggDto egg);

	void createTag(long eid, String message);

	Integer reserveEid();

	void foundEgg(EggDto egg, String message);

	List<EggDto> getEggsWithin(double minLat, double maxLat, double minLng,
			double maxLng);

	List<TagDto> getTags(EggDto egg);

}
