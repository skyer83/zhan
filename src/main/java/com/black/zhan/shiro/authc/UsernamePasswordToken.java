package com.black.zhan.shiro.authc;

/**
 * 参考：
 *  http://www.zuidaima.com/blog/3336789530561536.htm
 *  https://www.cnblogs.com/diaoniwa/p/6918896.html
 *  http://blog.csdn.net/u013482947/article/details/51002567
 * @author jhshen
 * 
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

	private static final long serialVersionUID = 1L;
	
	private String loginType; // 登录类型

	public String getLoginType() {
		return loginType;
	}


	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public UsernamePasswordToken(String username, char[] password, boolean rememberMe, String host, String loginType) {
		super(username, password, rememberMe, host);
		this.loginType = loginType;
	}
}
