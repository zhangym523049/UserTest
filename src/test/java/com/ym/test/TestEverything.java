package com.ym.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestEverything {
	

	 private static boolean match(String regex, String str) {
		 Pattern pattern = Pattern.compile(regex);
		 Matcher matcher = pattern.matcher(str);
		 return matcher.matches();
		 }
	
	public static void main(String[] args) {
		
		 System.out.println("///////");
		 
		 
		
	}
}
