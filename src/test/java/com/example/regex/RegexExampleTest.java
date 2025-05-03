package com.example.regex;

import org.junit.jupiter.api.Test;

class RegexExampleTest {

	@Test
	void test() {
		String result = RegexExample.parse("305=5~309=.dMICN0000FNUS~311=M1CNX Index~318=USD");
		System.out.println("result is" + result );
	}
	
	@Test
	void testReplace() {
		RegexExample.replace();
	}
	
	@Test
	void testBackRef() {
		RegexExample.backRef();
	}
	
	@Test
	void testBackRefExample2() {
		RegexExample.backRefExample2();
	}
	
	@Test
	void testMatchExample() {
		RegexExample.matchExample();
	}
	
	@Test
	void testMatchExample1() {
		RegexExample.matchExample1();
	}		
	
	@Test
	void testMatchExample2() {
		RegexExample.matchExample2();
	}			
	
	

}
