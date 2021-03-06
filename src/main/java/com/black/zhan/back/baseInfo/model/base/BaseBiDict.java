package com.black.zhan.back.baseInfo.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseBiDict<M extends BaseBiDict<M>> extends Model<M> implements IBean {

	public M setID(java.lang.String ID) {
		set("ID", ID);
		return (M)this;
	}
	
	public java.lang.String getID() {
		return getStr("ID");
	}

	public M setTypeCode(java.lang.String typeCode) {
		set("TYPE_CODE", typeCode);
		return (M)this;
	}
	
	public java.lang.String getTypeCode() {
		return getStr("TYPE_CODE");
	}

	public M setTypeName(java.lang.String typeName) {
		set("TYPE_NAME", typeName);
		return (M)this;
	}
	
	public java.lang.String getTypeName() {
		return getStr("TYPE_NAME");
	}

	public M setDictKey(java.lang.String dictKey) {
		set("DICT_KEY", dictKey);
		return (M)this;
	}
	
	public java.lang.String getDictKey() {
		return getStr("DICT_KEY");
	}

	public M setDictName(java.lang.String dictName) {
		set("DICT_NAME", dictName);
		return (M)this;
	}
	
	public java.lang.String getDictName() {
		return getStr("DICT_NAME");
	}

	public M setDictValue(java.lang.String dictValue) {
		set("DICT_VALUE", dictValue);
		return (M)this;
	}
	
	public java.lang.String getDictValue() {
		return getStr("DICT_VALUE");
	}

	public M setVisiStatus(java.lang.String visiStatus) {
		set("VISI_STATUS", visiStatus);
		return (M)this;
	}
	
	public java.lang.String getVisiStatus() {
		return getStr("VISI_STATUS");
	}

	public M setDictOrder(java.lang.Integer dictOrder) {
		set("DICT_ORDER", dictOrder);
		return (M)this;
	}
	
	public java.lang.Integer getDictOrder() {
		return getInt("DICT_ORDER");
	}

	public M setREMARK(java.lang.String REMARK) {
		set("REMARK", REMARK);
		return (M)this;
	}
	
	public java.lang.String getREMARK() {
		return getStr("REMARK");
	}

	public M setEditStatus(java.lang.String editStatus) {
		set("EDIT_STATUS", editStatus);
		return (M)this;
	}
	
	public java.lang.String getEditStatus() {
		return getStr("EDIT_STATUS");
	}

	public M setCreateTime(java.lang.String createTime) {
		set("CREATE_TIME", createTime);
		return (M)this;
	}
	
	public java.lang.String getCreateTime() {
		return getStr("CREATE_TIME");
	}

	public M setCreateUserId(java.lang.String createUserId) {
		set("CREATE_USER_ID", createUserId);
		return (M)this;
	}
	
	public java.lang.String getCreateUserId() {
		return getStr("CREATE_USER_ID");
	}

	public M setModifyTime(java.lang.String modifyTime) {
		set("MODIFY_TIME", modifyTime);
		return (M)this;
	}
	
	public java.lang.String getModifyTime() {
		return getStr("MODIFY_TIME");
	}

	public M setModifyUserId(java.lang.String modifyUserId) {
		set("MODIFY_USER_ID", modifyUserId);
		return (M)this;
	}
	
	public java.lang.String getModifyUserId() {
		return getStr("MODIFY_USER_ID");
	}

}
