package org.gtug.karlsruhe.bunnycacher.client;

import org.gtug.karlsruhe.bunnycacher.common.Egg;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("egg")
public interface EggService extends RemoteService {

	void createEgg(Egg egg);
	
}
