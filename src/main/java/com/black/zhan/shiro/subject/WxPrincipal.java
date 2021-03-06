package com.black.zhan.shiro.subject;

import com.black.zhan.back.baseInfo.model.BiUser;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

/**
 * 后台管理系统常用Session变量实体
 * @author jhshen
 *
 */
public class WxPrincipal extends Principal {
	
	private static final long serialVersionUID = 1L;
	
	// 当前用户
	private BiUser biUser;
//	// 当前用户所在的组集合
//	private List<Group> groupsList;
//	// 当前用户的授权资源集合
//	private List<Resource> authorizationInfo;
//	// 当前用户的菜单集合
//	private List<Resource> menusList;

	public WxPrincipal() {

	}

	public WxPrincipal(BiUser biUser) {
		this.biUser = biUser;
	}

//	public Principal(User user,List<Resource> authorizationInfo, List<Resource> menusList) {
//		this.user = user;
//		this.groupsList = user.getGroupsList();
//		this.authorizationInfo = authorizationInfo;
//		this.menusList = menusList;
//	}

	/**
	 * 获取当前用户
	 * 
	 * @return {@link User}
	 */
	public BiUser getBiUser() {
		return biUser;
	}

	/**
	 * 设置当前用户
	 * 
	 * @param user 当前用户
	 */
	public void setBiUser(BiUser biUser) {
		this.biUser = biUser;
	}

//	/**
//	 * 获取当前用户所在的组集合
//	 * 
//	 * @return List
//	 */
//	public List<Group> getGroupsList() {
//		return groupsList;
//	}
//
//	/**
//	 * 设置当前用户所在的组集合
//	 * 
//	 * @param groupsList 组集合
//	 */
//	public void setGroupsList(List<Group> groupsList) {
//		this.groupsList = groupsList;
//	}
//
//	/**
//	 * 获取当前用户的所有授权资源集合
//	 * 
//	 * @return List
//	 */
//	public List<Resource> getAuthorizationInfo() {
//		return authorizationInfo;
//	}
//
//	/**
//	 * 设置当前用户的所有授权资源集合
//	 * 
//	 * @param authorizationInfo 资源集合
//	 */
//	public void setAuthorizationInfo(List<Resource> authorizationInfo) {
//		this.authorizationInfo = authorizationInfo;
//	}
//
//	/**
//	 * 获取当前用户拥有的菜单集合
//	 * 
//	 * @return List
//	 */
//	public List<Resource> getMenusList() {
//		return menusList;
//	}
//
//	/**
//	 * 设置当前用户拥有的菜单集合
//	 * 
//	 * @param menusList 资源集合
//	 */
//	public void setMenusList(List<Resource> menusList) {
//		this.menusList = menusList;
//	}
}
