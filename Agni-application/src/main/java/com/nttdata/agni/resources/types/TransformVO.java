package com.nttdata.agni.resources.types;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

public class TransformVO {
	String inputType;
	String outputType;
	HashMap<String, String> dataMap;
	TreeMap<String, Integer> indexMap;
	Multimap<String, String> dataMultimap = ArrayListMultimap.create();
	ListMultimap<String, String> multimap = ArrayListMultimap.create();
	Multiset<String> myMultiset = HashMultiset.create();

	
	public void setDataValue(String key, String value) {
		dataMultimap.put(key, value);
		//this.dataMultimap = dataMultimap;
	}
	
	public Collection<String> getDataValue(Set<String> keys) {
		Collection<String> valueSet = null;
		for (String key: keys){
			valueSet = dataMultimap.get(key);
			//valueSet.size();
		}
		
		return valueSet;
	}
	
	public void printAll(){
		for (String firstName : multimap.keySet()) {
		     List<String> lastNames = multimap.get(firstName);
		     System.out.println(firstName + ": " + lastNames);
		   }
	}
	
	public Collection<String> getDataValue(String key) {
		Collection<String> valueSet = dataMultimap.get(key);
		valueSet.size();
		return null;
	}
	public void setDataMultimap(Multimap<String, String> dataMultimap) {
		this.dataMultimap = dataMultimap;
	}
	
	public TreeMap<String, Integer> getIndexMap() {
		return indexMap;
	}
	public void setIndexMap(TreeMap<String, Integer> indexMap) {
		this.indexMap = indexMap;
	}
	
	
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public String getOutputType() {
		return outputType;
	}
	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
}
