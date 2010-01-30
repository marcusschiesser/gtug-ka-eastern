package org.gtug.karlsruhe.bunnycacher.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;

public class BunnyCacherGuiceServletContextListener extends
		GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(
			      new BunnyCacherServletModule(),
			      PersistenceService.usingJpa()
			      		.across(UnitOfWork.REQUEST)
			      		.buildModule());
	}

}
