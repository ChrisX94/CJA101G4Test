package com.g4.service.model;

import java.security.DrbgParameters.NextBytes;
import java.util.Scanner;

public class HelloGit {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String c = sc.next();
		String h = "Hello Git!";
		String y = "pass!";
		if(c == h) {
			System.out.println(h);
		}else {
			System.out.println(y);
		}
		
	}
	


}
