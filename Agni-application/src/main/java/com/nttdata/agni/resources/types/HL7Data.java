package com.nttdata.agni.resources.types;

import java.util.HashMap;
import java.util.List;

public class HL7Data {
	
	List<String> segmentList;
	HashMap<String, Integer> segmentCount;
	HashMap<Integer,HL7Segment> segmentMapWithIndex;

}

class HL7Segment{
	String segment;
	HashMap<Integer,HL7Field> fieldsMap;
}

class HL7Field{
	String field;
	int count;
	
}