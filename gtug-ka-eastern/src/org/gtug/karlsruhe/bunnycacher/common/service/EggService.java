package org.gtug.karlsruhe.bunnycacher.common.service;


import org.gtug.karlsruhe.bunnycacher.common.domain.Egg;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("GWT.rpc")
public interface EggService extends RemoteService {

	void createEgg(Egg egg);
	Egg[] getEggsWithin(double latitude, double longitude);
	
}
