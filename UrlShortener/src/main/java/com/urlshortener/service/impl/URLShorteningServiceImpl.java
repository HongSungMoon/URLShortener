package com.urlshortener.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlshortener.database.URLShortenerMapper;
import com.urlshortener.util.Base62Util;
import com.urlshortner.service.URLShorteningService;

@Service("urlShorteningService")
public class URLShorteningServiceImpl implements URLShorteningService {
	
	@Autowired
	private URLShortenerMapper urlShortenerMapper;

	private Base62Util base62Util =  Base62Util.createInstance();
	
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

		// longURL을 통한 shortURL검
		String shortURL = urlShortenerMapper.getShortByLong(url);
		if(shortURL != null)
			return shortURL;
		
		// URL 저
		int hashCode = url.hashCode();
		String longURL = null;
		String newUrl = null;
		while (true) {
			byte[] result = base62Util.encode(Integer.toString((hashCode / 100000)).getBytes());
			newUrl = "http://localhost:8080/page/" + new String(result);
			longURL = urlShortenerMapper.getLongByShort(newUrl);
			if(longURL == null) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("longURL", url);
				map.put("shortURL", newUrl);
				urlShortenerMapper.insertURL(map);
				break;
			}
			hashCode += 100000;
		}
		
		return newUrl;
	}

	@Override
	public String getLongUrl(String url) {
		
		String plainUrl = urlShortenerMapper.getLongByShort("http://localhost:8080/page/" + url);
		return plainUrl;
		
	}

}
