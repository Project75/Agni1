package com.nttdata.agni.resources.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Encounter;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.exceptions.FHIRException;

import ca.uhn.fhir.model.primitive.IdDt;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class EncounterImpl extends AbstractResource {
	String episodeOFcare="EpisodeofCare/1235434";
	String episodeOFcare1="EpisodeofCare/1235434";
	
	Encounter encounter;
	
	EncounterImpl(){
		encounter =  new Encounter();
		encounter.setId(IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	//input map ("observation.status","ACTIVE")
	public void setValuesFromMap(HashMap<String,String> map) {
		 this.episodeOFcare= map.get("observation.status");
		 this.episodeOFcare1= map.get("observation.code");
	}
	
	@Override
	public void setResourceData() {
		// TODO Auto-generated method stub
		
		List<Reference> theEpisodeOfCare = new ArrayList<Reference>() ;
		Reference ref = new Reference();
		ref.setReference(episodeOFcare);
		theEpisodeOfCare.add(ref);
		
		encounter.setEpisodeOfCare(theEpisodeOfCare);
		
		Coding value =  new Coding();
		value.setSystem(getEpisodeOFcare1());
		encounter.setClass_(value );
		
		
	}
	
	
	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.encounter;
	}
	
}
