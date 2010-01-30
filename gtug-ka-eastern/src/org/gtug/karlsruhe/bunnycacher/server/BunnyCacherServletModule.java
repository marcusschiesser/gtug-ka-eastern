package org.gtug.karlsruhe.bunnycacher.server;

import org.gtug.karlsruhe.bunnycacher.common.service.EggService;

import com.google.inject.servlet.ServletModule;

public class BunnyCacherServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		serve("/bunnycacher/GWT.rpc").with(GuiceRemoteServiceServlet.class);

		// cannot use @ImplementedBy
		bind(EggService.class).to(EggServiceImpl.class);
	}

}
