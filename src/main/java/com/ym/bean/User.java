package com.ym.bean;

import java.util.Date;

public class User {

	private int id; // 自增长ID
	private String userName; // 用户名(唯一)
	private String password; // 密码(MD5加密)
	private int accountId; // 账户ID(Account表中的id)
	private int state; // 是否禁用(1:启用;0:禁用)
	private int cancel; // 是否删除(1:使用;0:删除)
	private String inputUser; // 创建人
	private Date inputTime; // 创建时间
	private String updateUser; // 更新人
	private Date updateTime; // 更新时间
	private String remarks; // 备注

	// 注册构造函数
	public User(String userName, String password, int accountId) {
		this.userName = userName;
		this.password = password;
		this.inputUser = userName;
		this.inputTime = new Date();
		this.cancel = 1;
		this.state = 1;
		this.accountId = accountId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
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
