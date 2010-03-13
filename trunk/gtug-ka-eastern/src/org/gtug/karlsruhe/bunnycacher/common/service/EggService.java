package org.gtug.karlsruhe.bunnycacher.common.service;


import java.util.List;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("GWT.rpc")
public interface EggService extends RemoteService {

	void createEgg(EggDto egg);
	
	void createTag(Integer eid, String message);
	
	Integer reserveEid();

	List<EggDto> getEggsWithin(double minLat, double maxLat, double minLng,
			double maxLng);
	
}