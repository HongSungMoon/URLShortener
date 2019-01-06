package com.urlshortener.util;

public class XOREncodingUtil {
	
	public int encode(int idx) {
		int key = 0x3B2F;
		int encoded_idx = (idx ^ key);
		return encoded_idx;
	}
	
}
