package com.urlshortener.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLValidationUtil {
	
	public int urlValidate(String url) throws MalformedURLException {
		
		URL u = new URL (url);
		HttpURLConnection huc = null;
		int code = 0;
		
		try {
			huc = ( HttpURLConnection )  u.openConnection ();
			huc.setRequestMethod ("GET"); 
			huc.connect () ; 
			code = huc.getResponseCode() ;
		} catch (IOException e) {
			return -1;
		} 
		
		if(code == 404)
			return -1;
		
		return code;
		
	}

}
