package com.urlshortener;

import java.util.HashMap;

import org.junit.Test;

import com.urlshortener.util.Base62Util;

public class Base62UtilTest {
	
	@Test
	public void encoding() {
		Base62Util base62Util = Base62Util.createInstance();
		byte[] result = base62Util.encode(Long.toString("/get/url".hashCode()).getBytes());
		System.out.println(new String(result));
	}

}
