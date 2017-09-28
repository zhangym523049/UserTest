package com.ym.enums;

/**
 * 
 * 返回类型
 * @author Administrator
 *
 */
public enum ReturnTypeEnums {
	
	INFO("returnInfo"),
	ERROR("returnError");
	
	private String type;

	public String getType() {
		return type;
	}

	private ReturnTypeEnums(String type) {
		this.type = type;
	}
	
	
	
	
}
