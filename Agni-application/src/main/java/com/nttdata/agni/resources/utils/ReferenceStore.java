package com.nttdata.agni.resources.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.hl7.fhir.dstu3.model.Reference;

public class ReferenceStore {
	HashMap<String,Reference> refMap;
	
	public void addReference(String key, Reference ref){
		refMap.put(key, ref);
	}
	
	public Reference getReference(String key){
		return refMap.get(key);
	}
}
