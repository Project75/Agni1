package com.nttdata.agni.resources.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class ReferenceMap {
	//ListMultimap<String, String> map = ArrayListMultimap.create();
	Map<String, String> map = new HashMap<String,String>();
	
	
	public String get(String key) {
		return map.get(key);
	
	}
	public void put(String key, String value) {
		 map.put(key, value);
		 
	}
	/*
	  
	public String get(String key) {
		//List<String> values = getRepeating(key);
		if (getCount(key)> 0)
			return map.get(key).get(0);
		else 
			return null;
	}
	public void put(String key, String value) {
		 map.put(key, value);
		 
	}
	public List<String> getAll(List<String> keys) {
		List<String> outList =  new ArrayList<String>();
		for (String key: keys){
			List<String> tmpList = map.get(key);
			outList.addAll(tmpList);
		}
		
		return outList;
	}
	
	public List<String> getAll(String key) {
		return map.get(key);
	}
	public String get(String key,int index) {
		if (index < getCount(key))
			return map.get(key).get(index);
		else 
			return null;
	}
	public int getCount(String key) {
		return map.get(key).size();
	}
	
	
	
	public void putAll(String key, List<String> valueList) {
		 map.putAll(key, valueList);
		 
	}
	
	*/
}
