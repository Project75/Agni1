package com.nttdata.agni.resources.core;

import java.util.HashMap;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class TransformMap {
	HashMap<String,String> map =  new HashMap<String,String>();
	HashSet<String> mapKeys =new HashSet<String>();
//PID-5(1)-1-1
//PID-5(1)-1-2,
//PID-5(2)-1-1
//patient.name(1).family(1)
	
	public String get(String key) {
		return map.get(key);
	}
	
	public String put(String key, String value) {
		return map.put(key, value);
	}
}
