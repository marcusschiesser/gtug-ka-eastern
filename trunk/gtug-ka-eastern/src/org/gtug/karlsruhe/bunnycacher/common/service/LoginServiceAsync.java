package org.gtug.karlsruhe.bunnycacher.common.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.gtug.karlsruhe.bunnycacher.client.LoginInfo;

public interface LoginServiceAsync {

    public void getLoginInfo(String requestUri, AsyncCallback<LoginInfo> async);

}
