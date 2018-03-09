package com.black.zhan.back.login.msg;

import com.black.zhan.common.comm.BlackCommon;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;

/**
 * 登录模块国际化信息获取类
 * @author jhshen
 *
 */
public class LoginMsg {

	private static String BASE_NAME = "com/black/blog/back/login/msg/login";
	private static Res res = I18n.use(BASE_NAME, BlackCommon.getLocale());
	
	public static String get(String key) {
		return res.get(key);
	}

	public static String format(String key, Object... arguments) {
		return res.format(key, arguments);
	}
	
	// INFO
	/** 用户名和密码不一致！ */
	public static final String LOGIN_INFO_001 = "login_info_0001";
	/** 验证码不一致！ */
	public static final String LOGIN_INFO_002 = "login_info_0002";
	
	// WARN
	/** 预留 */
	public static final String LOGIN_WARN_001 = "login_warn_0001";
	
	// ERR
	/** 预留 */
	public static final String LOGIN_ERR_001 = "login_err_0001";
}
