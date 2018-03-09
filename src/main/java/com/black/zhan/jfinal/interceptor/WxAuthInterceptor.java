package com.black.zhan.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 统一微信端管理拦截器，统一对所有微信端Action对应的 controllerKey进行拦截
 * @author jhshen
 *
 */
public class WxAuthInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		inv.invoke();
	}

}
