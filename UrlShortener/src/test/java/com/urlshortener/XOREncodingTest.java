package com.urlshortener;

import org.junit.Test;

import com.urlshortener.util.XOREncodingUtil;

public class XOREncodingTest {
	
	private XOREncodingUtil xorEncodingUtil = new XOREncodingUtil();

	@Test
	public void encodingTest() {
		int idx = 3;
		for(int i=1; i<2; i++) {
			int result = xorEncodingUtil.encode(i);
			System.out.println(result);
		}
	}
}
