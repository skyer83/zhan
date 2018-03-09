package com.black.zhan.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 全局拦截器
 * @author jhshen
 *
 */
public class GlobalAuthInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		inv.invoke(); // 继续往下走
	}

}
