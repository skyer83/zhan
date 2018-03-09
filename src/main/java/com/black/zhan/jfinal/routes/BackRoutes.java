package com.black.zhan.jfinal.routes;

import com.black.zhan.back.index.web.IndexController;
import com.black.zhan.back.login.web.LoginController;
import com.black.zhan.jfinal.interceptor.BackAuthInterceptor;
import com.jfinal.config.Routes;

/**
 * 后台路由注册
 * @author jhshen
 *
 */
public class BackRoutes extends Routes {
	
	/**
	 * Routes.setBaseViewPath(baseViewPath)方法用于为该 Routes 内部的所有 Controller 设置视
	 * 图渲染时的基础路径，该基础路径与 Routes.add(…, viewPath)方法传入的 viewPath 以及
	 * Controller.render(view)  方法传入的  view 参数联合组成最终的视图路径，规则如下：
	 * finalView = baseViewPath + viewPath + view
	 * 注意：当 view 以  “/” 字符打头时表示绝对路径，baseViewPath  与  viewPath  将被忽略。<BR>
	 * 
	 * 为了避免其它开发人员不理解，对 view 统一以  “/” 字符打头，不设置  baseViewPath
	 */
	@Override
	public void config() {
		addInterceptor(new BackAuthInterceptor());	// 统一后台管理拦截器，统一对所有后台Action对应的 controllerKey进行拦截
		
		// LOGIN模块
		add(RoutesConstants.BACK_LOGIN_LOGIN, LoginController.class);
		// INDEX模块
		add(RoutesConstants.BACK_INDEX_INDEX, IndexController.class);
	}

}
