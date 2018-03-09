package com.black.zhan.wx.index.web;

import java.util.HashMap;
import java.util.Map;

import com.black.zhan.back.common.BackController;
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
		map.put("aaa", "aaa111");
		map.put("bbb", "bbb222");
		
		renderJson(map);
	}
}
