package com.nttdata.agni.resources.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class TransformMap {
	ListMultimap<String, String> map = ArrayListMultimap.create();
	ReferenceMap referenceMap = new ReferenceMap() ;
		
	public String get(String key) {
		//List<String> values = getRepeating(key);
		if (getCount(key)> 0)
			return map.get(key).get(0);
		else 
			return null;
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
	
	
	public void put(String key, String value) {
		 map.put(key, value);
		 
	}
	public void putAll(String key, List<String> valueList) {
		 map.putAll(key, valueList);
		 
	}
	//Not Used
	public TransformMap switchMaps(TransformMap inputMap,TransformMap outputMap ){
		
		return outputMap;
	}
}
