package com.black.zhan.back.login.web;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.black.zhan.back.common.BackController;
import com.black.zhan.back.login.common.LoginConstants;
import com.black.zhan.back.login.common.LoginJspConstants;
import com.black.zhan.back.login.msg.LoginMsg;
import com.black.zhan.common.comm.BlackConstants;
import com.black.zhan.common.comm.CaptchaUtils;
import com.black.zhan.common.utils.BlackUtils;
import com.black.zhan.common.utils.StringUtils;
import com.black.zhan.shiro.authc.UsernamePasswordToken;
import com.jfinal.kit.HashKit;
import com.jfinal.log.Log;

/**
 * @author jhshen
 *
 */
public class LoginController extends BackController {
	
	private static final Log log = Log.getLog(LoginController.class);
	
	/**
	 * 访问登录页
	 */
	public void index() {
		render(LoginJspConstants.LOGIN_JSP);
	}
	
	/**
	 * 生成验证码
	 */
	public void generateCaptcha() {
		HttpServletResponse response = this.getResponse();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		String captcha = CaptchaUtils.generateCaptcha(4); // 生成 4 位随机字串
		this.getSession(true).setAttribute(LoginConstants.CAPTCHA_KEY, captcha); // 存入会话session
		int w = LoginConstants.CAPTCHA_WIDTH, h = LoginConstants.CAPTCHA_HEIGHT; // 生成图片
		try {
			CaptchaUtils.outputImage(w, h, response.getOutputStream(), captcha);
		} catch (IOException e) { // 生成验证码失败
			log.error(null, e);
		}
		renderNull(); // 不渲染，即不向客户端返回数据
		
		//this.renderCaptcha(); // jfinal 自带生成验证码的方法，参见：http://www.jfinal.com/share/81?p=2#reply_start
	}
	
	/**
	 * 登录
	 */
	public void doLogin() {
		
		HttpServletRequest request = this.getRequest();
		
		String userName = this.getPara("userName");
		// 123456:e10adc3949ba59abbe56e057f20f883e
		String password = HashKit.md5(this.getPara("password"));
		boolean rememberMe = isRememberMe(request);
		String host = BlackUtils.getRemoteAddr(request);
		String loginType = this.getPara("loginType");
		
		String captcha = this.getPara("captcha");
		String captchaSession = (String) this.getSession().getAttribute(LoginConstants.CAPTCHA_KEY);
		if (!"8888".equals(captcha) && (StringUtils.isEmpty(captchaSession) || !StringUtils.equalsIgnoreCase(captchaSession, captcha))) {
			renderJson(BlackConstants.FAIL_MSG_KEY, LoginMsg.get(LoginMsg.LOGIN_INFO_002));
			return;
		}
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password.toCharArray(), rememberMe, host, loginType);
		try {
			/*
			 * 在 ModularRealmAuthenticator 分发 Realm（BackAuthorizingRealm， WxAuthorizingRealm）；
			 * 在 Realm（BackAuthorizingRealm， WxAuthorizingRealm） 做登录认证
			 */
			subject.login(token);
			this.getSession().setAttribute(BlackConstants.LOGIN_SUCC_KEY, BlackConstants.LOGIN_SUCC_1);
			
			renderJson(BlackConstants.SUCCESS_KEY, BlackConstants.SUCCESS_1);
		} catch (ShiroException e) {
			log.error(null, e);
			renderJson(BlackConstants.FAIL_MSG_KEY, e.getMessage());
		} catch (Exception e) {
			log.error(null, e);
			renderJson(BlackConstants.FAIL_MSG_KEY, e.getMessage());
		}
	}
	
    private boolean isRememberMe(ServletRequest request) {
        return WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
    }
	
	/**
	 * 退出
	 */
	public void doLogout() {
		Subject subject = SecurityUtils.getSubject();
	    subject.logout();
		redirect(LoginJspConstants.LOGIN_URL);
	}
}
