package com.ym.bean;

import java.util.Date;

public class Account {
	
	
	private int id;               //自增长ID
	private String realName;     //真实姓名
	private String birthday;      //出生年月(YYYYMM)
	private int gender;           //性别(1:男;0:女)
	private String mobileNumber; //手机号码
	private String eMail;        //邮箱地址
	private String address;       //联系地址
	private int eduction;         //学历(0:研究生以上;1:研究生;2:本科;3:大专;4:大专以下)
	private int state;            //是否禁用(1:启用;0:禁用)
	private int cancel;           //是否删除(1:使用;0:删除)
	private String inputUser;    //创建人
	private Date inputTime;      //创建时间
	private String updateUser;   //更新人
	private Date updateTime;     //更新时间
	private String remarks;       //备注
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getEduction() {
		return eduction;
	}
	public void setEduction(int eduction) {
		this.eduction = eduction;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getCancel() {
		return cancel;
	}
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
	public String getInputUser() {
		return inputUser;
	}
	public void setInputUser(String inputUser) {
		this.inputUser = inputUser;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
