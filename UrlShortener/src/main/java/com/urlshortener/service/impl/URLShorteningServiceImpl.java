package com.urlshortener.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.urlshortener.util.Base62Util;
import com.urlshortner.service.URLShorteningService;

@Service("urlShorteningService")
public class URLShorteningServiceImpl implements URLShorteningService {

	private Base62Util base62Util =  Base62Util.createInstance();
	private HashMap<String, String> shortMap = new HashMap<String, String>();
	private HashMap<String, String> longMap = new HashMap<String, String>();
	
	public String urlShortening(String url) throws MalformedURLException, ProtocolException {
		
		if(!url.contains("//"))
			url = "http://" + url;
		
		// URL Validation Check
		URL u = new URL (url);
		HttpURLConnection huc = null;
		int code = 0;
		
		try {
			huc = ( HttpURLConnection )  u.openConnection ();
			huc.setRequestMethod ("GET"); 
			huc.connect () ; 
			code = huc.getResponseCode() ;
		} catch (IOException e) {
			return "Invalid URL";
		} 
		
		if(code == 404)
			return "Invalid URL";

		if(longMap.containsKey(url))
			return longMap.get(url);
		
		int hashCode = url.hashCode();
		String newUrl = null;
		while (true) {
			byte[] result = base62Util.encode(Integer.toString((hashCode / 100000)).getBytes());
			newUrl = "http://localhost:8080/page/" + new String(result);
			if(!shortMap.containsKey(newUrl)) {
				shortMap.put(newUrl, url);
				longMap.put(url, newUrl);
				break;
			}
			hashCode += 100000;
		}
		
		return newUrl;
	}

	@Override
	public String getLongUrl(String url) {
		String plainUrl = shortMap.get("http://localhost:8080/page/" + url);
		return plainUrl;
	}

}
