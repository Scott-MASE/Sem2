package com.example.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class MoreExamples {
	
	
	
	public static void main(String args[]) {
		q1();
        
        
	}
	
	static void q1() {
        // Load French ResourceBundle
        Locale frenchLocale = Locale.FRANCE;
        ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", frenchLocale);
        
        // Retrieve and print the localized message for key "menu1"
        String menuOption = messages.getString("menu1");
        System.out.println("French translation for menu1: " + menuOption);
        
        // Load Chinese ResourceBundle
        Locale chineseLocale = Locale.SIMPLIFIED_CHINESE;
        messages = ResourceBundle.getBundle("MessagesBundle", chineseLocale);
        System.out.println("Chinese translation for menu1: " + messages.getString("menu1"));
	}
	
	static void q2() {
		
	}
}
