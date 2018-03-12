package com.black.zhan.wx.index.web;

import java.util.HashMap;
import java.util.Map;

import com.black.zhan.back.common.BackController;
import com.black.zhan.common.comm.BlackConstants;
import com.jfinal.log.Log;

/**
 * @author jhshen
 *
 */
public class IndexController extends BackController {
	
	private static final Log log = Log.getLog(IndexController.class);
	
	/**
	 * 访问登录页
	 */
	public void index() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("aaa", "aaa11122");
		map.put("bbb", "bbb222");
		
		renderJson(map);
	}
	
	public void checkOpenid() {
		String openid = this.getPara("openid");
		if ("opf0I4xUkbkepmmLZ3Q3utaJsKrw".equals(openid)) {
			renderJson(BlackConstants.SUCCESS_KEY, BlackConstants.SUCCESS_1);
		} else {
			renderJson(BlackConstants.SUCCESS_KEY, BlackConstants.SUCCESS_0);
		}
	}
}
