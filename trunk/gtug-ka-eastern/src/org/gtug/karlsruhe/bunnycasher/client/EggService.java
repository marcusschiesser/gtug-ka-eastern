package org.gtug.karlsruhe.bunnycasher.client;

import org.gtug.karlsruhe.bunnycasher.client.common.Egg;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("egg")
public interface EggService {

	void createEgg(Egg egg);
	
}
