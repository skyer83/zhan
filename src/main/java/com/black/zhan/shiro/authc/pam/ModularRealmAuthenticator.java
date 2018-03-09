package com.black.zhan.shiro.authc.pam;

import java.util.Collection;
import java.util.Map;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;

import com.black.zhan.common.enumeration.LoginType;
import com.black.zhan.shiro.authc.UsernamePasswordToken;

public class ModularRealmAuthenticator extends org.apache.shiro.authc.pam.ModularRealmAuthenticator {
	
	private Map<String, Object> definedRealms;

	/**
	 * 多个realm实现
	 */
	@Override
	protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms, AuthenticationToken token) {
		return super.doMultiRealmAuthentication(realms, token);
	}

	/**
	 * 调用单个realm执行操作
	 */
	@Override
	protected AuthenticationInfo doSingleRealmAuthentication(Realm realm, AuthenticationToken token) {
		if (!realm.supports(token)) { // 如果该realms不支持(不能验证)当前token
			throw new ShiroException("AuthenticationToken 类型错误!");
		}
		AuthenticationInfo info = null;
		try {
			info = realm.getAuthenticationInfo(token);
			if (info == null) {
				throw new ShiroException("token 不存在!");
			}
		} catch (Exception e) {
			throw new ShiroException("用户名或者密码错误!");
		}
		return info;
	}

	/**
	 * 判断登录类型执行操作
	 */
	@Override
	protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
		this.assertRealmsConfigured();
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		Realm realm = null;
		if (token.getLoginType().equals(LoginType.LoginType_1.getValue())) { // 后台用户登录
			realm = (Realm) this.definedRealms.get("backRealm");
		} else if (token.getLoginType().equals(LoginType.LoginType_2.getValue())) { // 前台用户登录
			realm = (Realm) this.definedRealms.get("wxRealm");
		}
		return this.doSingleRealmAuthentication(realm, authenticationToken);
	}

	/**
	 * 判断realm是否为空
	 */
	@Override
	protected void assertRealmsConfigured() throws IllegalStateException {
		this.definedRealms = this.getDefinedRealms();
		if (CollectionUtils.isEmpty(this.definedRealms)) {
			throw new ShiroException("值传递错误!");
		}
	}

	public Map<String, Object> getDefinedRealms() {
		return this.definedRealms;
	}

	public void setDefinedRealms(Map<String, Object> definedRealms) {
		this.definedRealms = definedRealms;
	}
}
