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
import com.urlshortener.util.URLValidationUtil;
import com.urlshortener.util.XOREncodingUtil;
import com.urlshortner.service.URLShorteningService;

@Service("urlShorteningService")
public class URLShorteningServiceImpl implements URLShorteningService {

	@Autowired
	private URLShortenerMapper urlShortenerMapper;

	private Base62Util base62Util = Base62Util.createInstance();

	private URLValidationUtil urlValidationUtil = new URLValidationUtil();

	private XOREncodingUtil xorEncodingUtil = new XOREncodingUtil();

	public String urlShortening(String url) throws MalformedURLException, ProtocolException {

		if (!url.contains("//"))
			url = "http://" + url;

		// URL Validation Check
		int returnCode = urlValidationUtil.urlValidate(url);
		if (returnCode == -1)
			return "Invalid URL";

		// longURL을 통한 shortURL 검증
		String shortURL = urlShortenerMapper.getShortByLong(url);
		if (shortURL != null)
			return shortURL;

		// URL 저장
		int newIdx = urlShortenerMapper.getLastIdx();
		String newUrl = null;
		int encodedIdx = xorEncodingUtil.encode(newIdx);

		byte[] result = base62Util.encode(Integer.toString(encodedIdx).getBytes());
		newUrl = "http://localhost:8080/page/" + new String(result);
		Map<String, String> map = new HashMap<String, String>();
		map.put("longURL", url);
		map.put("shortURL", newUrl);
		urlShortenerMapper.insertURL(map);

		return newUrl;
		
	}

	@Override
	public String getLongUrl(String url) {

		String plainUrl = urlShortenerMapper.getLongByShort("http://localhost:8080/page/" + url);
		
		return plainUrl;

	}

}
