package com.nttdata.agni.resources.core;

import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.CareTeam;

import com.nttdata.agni.resources.utils.IdentifierUtils;
import com.nttdata.agni.resources.utils.TransformMap;

public class CareTeamImpl extends AbstractResource {
	CareTeam resource;


	/**
	 * Constructor to initialize the FHIR STU3 model Object
	 */
	public CareTeamImpl() {
		
		// TODO Auto-generated constructor stub
		this.resource =  new CareTeam();
		
	}

	@Override
	public void setResourceDataFromMap(TransformMap data) {
		
		setValuesFromMap(data);
		setResourceData(data);

	}
	
	public void setValuesFromMap(TransformMap map) {

	}
	
	@Override
	public void setResourceData(TransformMap map) {
		//resource.setName(NameUtils.getNames(map, resourceName));
		
		resource.setIdentifier(IdentifierUtils.getIdentifierList(map, resourceName));

	}

	/*
	 * Return the FHIR resource object
	 */
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.resource;
	}

}
