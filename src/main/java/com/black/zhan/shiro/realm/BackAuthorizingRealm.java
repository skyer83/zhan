package com.black.zhan.shiro.realm;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.black.zhan.back.baseInfo.model.BiUser;
import com.black.zhan.shiro.authc.UsernamePasswordToken;
import com.black.zhan.shiro.subject.BackPrincipal;
import com.jfinal.log.Log;

public class BackAuthorizingRealm extends AuthorizingRealm {

	private static final Log log = Log.getLog(BackAuthorizingRealm.class);

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermission("sys:manager");
		info.addStringPermission("user");
		log.debug("开始进行 perm 的授权");
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//		BiUser biUser = BiUser.dao.findFirst("SELECT T.* FROM BI_USER T WHERE T.LOGIN_NAME = ? ", token.getUsername());
//		if (biUser == null) {
//			throw new UnknownAccountException("用户名不存在");
//		}
		BiUser biUser = new BiUser();
		biUser.setLoginName(token.getUsername());
		biUser.setLoginPwd(new String(token.getPassword()));
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(new BackPrincipal(biUser), biUser.getLoginPwd(), null, getName());
		return authenticationInfo;
	}

	/**
	 * 设定密码校验的MD5算法与迭代次数
	 * 
	 * https://www.cnblogs.com/landiljy/p/5764515.html
	 * PostConstruct说明：
	 *   被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，类似于Serclet的inti()方法。
	 *   被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("MD5");
		setCredentialsMatcher(matcher);
	}

	@Override
	protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	protected void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthorizationInfo();
		clearAllCachedAuthenticationInfo();
	}

}
