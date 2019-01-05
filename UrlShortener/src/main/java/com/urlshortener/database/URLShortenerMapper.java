package com.urlshortener.database;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface URLShortenerMapper {

	public String getCurrentDateTime();
	
	public int getLastIndex();
	
	public void insertURL(Map<String, String> map);
	
	public String getLongByShort(String shortURL);
	
	public String getShortByLong(String longURL);

}
