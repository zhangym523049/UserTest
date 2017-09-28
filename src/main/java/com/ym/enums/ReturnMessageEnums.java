package com.ym.enums;

/**
 * 
 * 返回视图信息
 * @author Administrator
 *
 */
public enum ReturnMessageEnums {
	
	
	ERROR(500,"error"),
	INTERNET_ERROR(500,"网络故障，请重新操作！"),
	NULL_ERROR(500,"输入为空，请重新输入！"),
	VERIFYCODE_ERROR(500,"验证码输入错误,请重新输入！"),
	UNKNOWN_ERROR(500,"未知错误，请刷新重试！");
	
	
	
	private String eMessage;
	private int eCode;

	public String geteMessage() {
		return eMessage;
	}

	public int geteCode() {
		return eCode;
	}
	ReturnMessageEnums(int eCode,String eMessage) {
		this.eMessage = eMessage;
		this.eCode = eCode;
	}
	
	
	

}
