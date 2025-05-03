package com.example.streams;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StreamExampleTest {
	
	private StreamExample subject;

	@BeforeEach
	void setUp() throws Exception {
		this.subject = new StreamExample();
	}

	@AfterEach
	void tearDown() throws Exception {
		this.subject = null;
	}

	@Test
	void test() {
		String result = this.subject.concatListWithDelimiter(Arrays.asList(new Long[] {123L, 124L, 125L }));
		assertEquals("123,124,125", result);
	}

}
