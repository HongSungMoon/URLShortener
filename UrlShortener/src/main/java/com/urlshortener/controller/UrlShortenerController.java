package com.urlshortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.urlshortener.model.TargetURL;

@Controller
public class UrlShortenerController {
	
	@RequestMapping(value = "/get/url", method = RequestMethod.POST)
	public ResponseEntity<String> getURL(TargetURL url) {
		String shorturl = "new " + url.getUrl();
		return new ResponseEntity<String>(shorturl, HttpStatus.OK);
	}

}
