package com.EhcacheExample;

import org.springframework.stereotype.Component;

@Component
public class StringExample {
	
	public boolean compareExample(String str1) {
		System.out.println("Hari" + str1);
		return str1.equalsIgnoreCase("jagan"); 
	}

}
