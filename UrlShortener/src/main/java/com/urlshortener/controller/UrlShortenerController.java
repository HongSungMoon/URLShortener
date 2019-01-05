package com.urlshortener.controller;

import java.net.MalformedURLException;
import java.net.ProtocolException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.urlshortener.database.URLShortenerMapper;
import com.urlshortener.model.TargetURL;
import com.urlshortner.service.URLShorteningService;

@Controller
public class UrlShortenerController {
	
	@Autowired
	URLShorteningService urlShorteningService;
	
	@Autowired
	URLShortenerMapper urlShortenerMapper;
	
	@RequestMapping(value="/")
    public String home() {
        return "home";       
    }
	
	@RequestMapping(value="/login")
    public String loginPage() {
        return "login";       
    }

	@RequestMapping(value = "/get/url", method = RequestMethod.POST)
	public ResponseEntity<String> getURL(TargetURL url) throws MalformedURLException, ProtocolException {
		String shorturl = urlShorteningService.urlShortening(url.getUrl());
		return new ResponseEntity<String>(shorturl, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/page/{url}", method = RequestMethod.GET)
	public String redirectPage(@PathVariable String url) {
		String redirectUrl = urlShorteningService.getLongUrl(url);
		if(redirectUrl == null)
			return "redirect:/";
		return "redirect:" + redirectUrl;
	}

}
