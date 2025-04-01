package com.example.localization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

public class DateApplication {

    PrintWriter pw = new PrintWriter(System.out, true);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Locale ruLocale = new Locale("ru", "RU");
//    Locale currentLocale = Locale.US;
    Locale irlLocale = new Locale("en", "IE");   
    Locale currentLocale = irlLocale;           // start out with en_IE (i.e. English in Ireland) menu
    ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

    public static void main(String[] args) {
        DateApplication dateApp = new DateApplication();
        dateApp.run();
    }
    public void java8(){
        
    }

    public void run() {
        String line = "";

        while (!(line.equals("q"))) {
            this.printMenu();
            try {
                line = this.br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

            switch (line) {
                case "1": setEnglish(); break;
                case "2": setFrench(); break;
                case "3": setChinese(); break;
                case "4": setRussian(); break;
            }
        }
    }

    public void printMenu() {
        pw.println("=== Date App ===");
        // As opposed to lots of formatting (as in the Java 7 lab), I am focusing on 
        // the new Java 8 material (java.time packages). There are some format q's 
        // still in this lab; plus formatting in also the Notes (slide 13).
        pw.println("\n--- Choose Language Option ---");
        pw.println("1. " + messages.getString("menu1"));
        pw.println("2. " + messages.getString("menu2"));
        pw.println("3. " + messages.getString("menu3"));
        pw.println("4. " + messages.getString("menu4"));
        pw.println("q. " + messages.getString("menuq"));
        System.out.print(messages.getString("menucommand") + " ");
    }

    public void setEnglish() {
        // Set currentLocale
        // Set messages
    }

    public void setFrench() {
        // Set currentLocale
        // Set messages
    }

    public void setChinese() {
        // Set currentLocale
        // Set messages
    }

    public void setRussian() {
        // Set currentLocale
        // Set messages
    }
}
