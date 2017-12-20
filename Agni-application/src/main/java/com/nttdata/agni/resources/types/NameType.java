package com.nttdata.agni.resources.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hl7.fhir.dstu3.model.HumanName;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NameType {
	HumanName Name;
	String use, text;
	List<String> family, given;
	String prefix;
	String suffix;
	String parentResource = "patient";
	String lookup = parentResource+".name";
	//NameType nameType = new NameType();
	List<HumanName> nameTypeList = new ArrayList<HumanName>();

public void addFamily(String val){
	family.add(val);
}

void SetValues(HashMap<String,String> map,String parentResource){
	this.parentResource = parentResource;
	NameType nameType = new NameType();
	Set<String> nameKeySet = map.keySet()
	          .stream()
	          .filter(s -> s.startsWith(this.lookup))
	          .collect(Collectors.toSet());
	int i = 1;
	for (String key: nameKeySet){
		if (key.contains(lookup+"."+i+".firstname"))
				addFamily(map.get(key));
		  
		
		  System.out.println(key+"==key,val=="+map.get(key));
		  
	  }
}

}