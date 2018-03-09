package com.black.zhan.jfinal.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

/**
 * 路径带jsessionid，比如/index;jsessionid=CC2427CDC849E2BE2A33FA967BC0047A，jFinal报404
 * 
 * 参考：http://www.jfinal.com/feedback/2376
 * 
 * @author jhshen
 * 
 */
public class SessionIdHandler extends Handler {

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		boolean hasSessionIdInURL = request.isRequestedSessionIdFromURL() || request.isRequestedSessionIdFromCookie();
		if (hasSessionIdInURL) {
			int index = target.indexOf(";");
			if (index > 0) {
				target = target.substring(0, index);
			}
		}
		next.handle(target, request, response, isHandled);
	}

}
