package com.black.zhan.back.index.web;

import com.black.zhan.back.common.BackController;
import com.black.zhan.back.index.common.IndexJspConstants;
import com.jfinal.log.Log4jLog;

/**
 * @author jhshen
 *
 */
public class IndexController extends BackController {
	
	@SuppressWarnings("unused")
	private static final Log4jLog log = Log4jLog.getLog(IndexController.class);
	
	/**
	 * 访问首页
	 */
	public void index() {
		render(IndexJspConstants.INDEX);
	}
}
