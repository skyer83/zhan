package com.black.zhan.common.comm;

/**
 * 与业务有关的公共方法
 * @author jhshen
 *
 */
public class BlackCommon {
	
	/**
	 * 获取配置的国际化 Local
	 * @return
	 */
	public static String getLocale() {
		return "zh_CN"; // 后续可根据数据库配置获取，en_US/zh_CN
	}
}
