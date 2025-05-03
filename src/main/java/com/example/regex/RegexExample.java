package com.example.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
	
	public static String parse(String tag) {
		// example 305=5~309=.dMICN0000FNUS~311=M1CNX Index~318=USD  
		String ricTag= "309";
		String pattern = "(?<=" + ricTag + "=)[^~]+";
		Pattern ricPattern = Pattern.compile(pattern);
		Matcher matcher = ricPattern.matcher(tag);
		
		while(matcher.find()) {
			return matcher.group(0);
		}
		
		return tag;
	}
	
	public static void replace() {
		String expr = "order.SOR_OUTCOME_BROKER = 'ABC' AND 1=1 AND ORDER.QTY>10 AND FUNCTION.DV09_S > 1";
		// String regex = "(?<=^|[^A-Za-z])([A-Z][a-z]*)(?=[^A-Za-z]|$)";
		String regex = "(([A-Za-z]+)\\.([[A-Z0-9][_]?]+))";
		Pattern pattern = Pattern.compile(regex);
		String[] splits = pattern.split(expr);
		
		System.out.println("splits " + splits.toString());		
		
		Matcher matcher = pattern.matcher(expr);		
		
		while(matcher.find()) {
			System.out.println("matcher group " + matcher.group(0));
			// System.out.println("matcher group " + matcher.start());			
			// System.out.println("matcher group " + matcher.end());			
		}
	}
	
	public static void backRef() {
		String test = "Hello .World";
		
		String regex = "(\\w+)(\\s+)(\\.\\w+)";
		
		String regex1 = "(Hello\\s+.*\\w+)(\\s+)(\\.\\w+)";
		
		
		String result = test.replaceAll(regex, "$1$3");
		
		System.out.println("test.replaceAll(regex, $1) > " + test.replaceAll(regex, "$1"));
		
		System.out.println("input replaced RESULT " + result);
	}
	
	public static void backRefExample2() {
		String test = "FUNCTION.ACCT_CD IN ('MBINC') OR FUNCTION.ACCT_CD IN ('FEAT')";
		
		String fragment = "FUNCTION.ACCT_CD";
		
		String predicate1= "IN \\('MBINC'\\)";
		
		String regex1 = "(" + fragment + "\\s+" + predicate1 + ")";
		
		String regex = fragment+"\\s+"+predicate1;
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(test);
		
		while(matcher.find()) {
			System.out.println("matcher group " + matcher.group(0));
		}
		
		
		
		String replaceStr1 = "(SELECT ACCT_CD FROM CRD_ADMIN.CS_FUND WHERE ACT_CD)";
		
		System.out.println("test.replaceAll(regex, $1) > " + test.replaceAll(regex, replaceStr1));
	}
	
	public static void matchExample() {
		String test = "orderId AND secId OR";
		
		String regex = "\\w+";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(test);
		
		while(matcher.find()) {
			System.out.println("matcher group " + matcher.group(0));
		}
	}
	
	
	public static void matchExample1() {
		String test = "orderId IN AND secId OR";
		
		String regex = "\\s*\\w+\\s*(AND|OR)";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(test);
		
		while(matcher.find()) {
			System.out.println("matcher group " + matcher.group(0));
		}
	}	
	
	public static void matchExample2() {
		String test = "orderId IN AND secId OR";
		
		String regex = "(\\s*\\w+\\s*)(AND|OR)";
		
		String regex1 = "(AND|OR)";
		
		Pattern pattern = Pattern.compile(regex1);
		Matcher matcher = pattern.matcher(test);
		
		while(matcher.find()) {
			System.out.println("matcher group " + matcher.group(0));
			System.out.println("matcher group " + matcher.start());
		}
	}		
	

}
