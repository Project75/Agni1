package com.nttdata.agni.resources.utils;

public class MapConfig {
	String mapName;
	String mappingStrategy="DEFAULT";
/*
 * 		Strategy
 *		DEFAULT = If mapping not present get it from default  
 *		MINIMAL  = If mapping not present do not map	
 */
	public static enum Strategy {
		DEFAULT,
		MINIMAL;
	}
	
}
