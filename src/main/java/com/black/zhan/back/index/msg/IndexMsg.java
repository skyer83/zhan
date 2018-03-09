package com.black.zhan.back.index.msg;

import com.black.zhan.common.comm.BlackCommon;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;

/**
 * 登录模块国际化信息获取类
 * @author jhshen
 *
 */
public class IndexMsg {

	private static String BASE_NAME = "com/black/blog/back/index/msg/index";
	private static Res res = I18n.use(BASE_NAME, BlackCommon.getLocale());
	
	public static String get(String key) {
		return res.get(key);
	}

	public static String format(String key, Object... arguments) {
		return res.format(key, arguments);
	}
	
	// INFO
	/** 预留 */
	public static final String INDEX_INFO_001 = "index_info_0001";
	
	// WARN
	/** 预留 */
	public static final String INDEX_WARN_001 = "index_warn_0001";
	
	// ERR
	/** 预留 */
	public static final String INDEX_ERR_001 = "index_err_0001";
}
