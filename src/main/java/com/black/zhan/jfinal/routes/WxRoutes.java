package com.black.zhan.jfinal.routes;

import com.black.zhan.wx.index.web.IndexController;
import com.black.zhan.jfinal.interceptor.WxAuthInterceptor;
import com.jfinal.config.Routes;

/**
 * 微信路由注册
 * @author jhshen
 *
 */
public class WxRoutes extends Routes {

	/*
	 * (non-Javadoc)
	 * @see com.black.blog.jfinal.routes.BackRoutes#config()
	 */
	@Override
	public void config() {
		addInterceptor(new WxAuthInterceptor());
		
		// INDEX模块
		add(RoutesConstants.WX_INDEX_INDEX, IndexController.class);
	}

}
