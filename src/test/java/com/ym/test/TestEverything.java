package com.ym.test;

import org.apache.commons.codec.digest.DigestUtils;

public class TestEverything {
	
	public static void main(String[] args) {
		String password = "qw";
		
		System.out.println(DigestUtils.md5Hex(password));
		System.out.println(DigestUtils.md5Hex(password).length());
		
	}
}
