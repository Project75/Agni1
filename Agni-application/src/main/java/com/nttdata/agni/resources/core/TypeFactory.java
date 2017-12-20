package com.nttdata.agni.resources.core;

public class TypeFactory {
	
	class IdentifierType{
		String use, type, system, value, period, assigner;
		
	}

	class CConceptType{
		CodingType code;
		String text;
		
	}

	class CodingType{
		String system, version, code, display, userSelected;
		
	}

	class RerefenceType{
		String system, version, code, display, userSelected;
		
	}

}
