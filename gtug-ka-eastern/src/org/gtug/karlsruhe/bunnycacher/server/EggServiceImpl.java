package org.gtug.karlsruhe.bunnycacher.server;

import org.gtug.karlsruhe.bunnycacher.client.EggService;
import org.gtug.karlsruhe.bunnycacher.common.Egg;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class EggServiceImpl extends RemoteServiceServlet implements
		EggService {

	@Override
	public void createEgg(Egg egg) {
		
	}

}
