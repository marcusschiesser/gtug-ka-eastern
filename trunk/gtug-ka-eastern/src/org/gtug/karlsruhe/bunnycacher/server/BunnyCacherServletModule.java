package org.gtug.karlsruhe.bunnycacher.server;

import org.gtug.karlsruhe.bunnycacher.common.service.EggService;

import com.google.inject.servlet.ServletModule;
import com.wideplay.warp.jpa.JpaUnit;

public class BunnyCacherServletModule extends ServletModule {

	@Override
	protected void configureServlets() {
		serve("/bunnycacher/GWT.rpc").with(GuiceRemoteServiceServlet.class);

		// cannot use @ImplementedBy
		bind(EggService.class).to(EggServiceImpl.class);
		
		bindConstant().annotatedWith(JpaUnit.class).to("default");
	}

}
