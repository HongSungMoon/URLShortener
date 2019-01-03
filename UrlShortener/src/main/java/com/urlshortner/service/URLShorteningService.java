package com.urlshortner.service;

import java.net.MalformedURLException;
import java.net.ProtocolException;

public interface URLShorteningService {
	
	public String urlShortening(String url) throws MalformedURLException, ProtocolException;

	public String getLongUrl(String url);
	
}
