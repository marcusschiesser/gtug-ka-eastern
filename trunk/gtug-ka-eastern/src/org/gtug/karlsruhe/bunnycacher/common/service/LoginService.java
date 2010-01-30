package org.gtug.karlsruhe.bunnycacher.common.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.gtug.karlsruhe.bunnycacher.client.LoginInfo;

@RemoteServiceRelativePath("GWT.rpc")
public interface LoginService extends RemoteService {

    public LoginInfo login(String requestUri);

    String getCurrentUserId();
}

