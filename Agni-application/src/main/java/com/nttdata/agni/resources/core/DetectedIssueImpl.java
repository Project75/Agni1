package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.DetectedIssue;
import org.hl7.fhir.dstu3.model.DetectedIssue.DetectedIssueSeverity;

import com.nttdata.agni.resources.utils.FHIRUtils;
import com.nttdata.agni.resources.utils.IdentifierUtil;

import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@ToString @Getter @Setter
public class DetectedIssueImpl extends AbstractResource {
	DetectedIssue detectedissue ;
	
	
	public DetectedIssueImpl() {
		detectedissue = new DetectedIssue() ;

		// TODO Auto-generated constructor stub
	}

	String resourceName="DetectedIssue";
	
	String 
	// Identifier
	identifierUse,
	identifierTypeCodingSystem,
	identifierTypeCodingVersion,
	identifierTypeCodingCode,
	identifierTypeCodingDisplay,
	identifierTypeCodingUserSelected,
	identifierTypeText,
	identifierSystem,
	identifierValue,
	identifierPeriodStart,
	identifierPeriodEnd,
	identifierAssignerDisplay,
	
	//status
	status,
	
	//category
	categoryCodingSystem,
	categoryCodingVersion,
	categoryCodingCode,
	categoryCodingDisplay,
	categoryCodingUserSelected,
	categoryText,

	//severity
	severity,
	
	
	//patient
	patientReference,
	patientIdentifierUse,
	patientIdentifierTypeCodingSystem,
	patientIdentifierTypeCodingVersion,
	patientIdentifierTypeCodingCode,
	patientIdentifierTypeCodingDisplay,
	patientIdentifierTypeCodingUserSelected,
	patientIdentifierTypeText,
	patientIdentifierSystem,
	patientIdentifierValue,
	patientIdentifierPeriodStart,
	patientIdentifierPeriodEnd,
	patientIdentifierAssignerDisplay,
	patientDisplay,
	
	//date
	 date,
	 
    //author
	authorReference,
	authorIdentifierUse,
	authorIdentifierTypeCodingSystem,
	authorIdentifierTypeCodingVersion,
	authorIdentifierTypeCodingCode,
	authorIdentifierTypeCodingDisplay,
	authorIdentifierTypeCodingUserSelected,
	authorIdentifierTypeText,
	authorIdentifierSystem,
	authorIdentifierValue,
	authorIdentifierPeriodStart,
	authorIdentifierPeriodEnd,
	authorIdentifierAssignerDisplay,
	authorDisplay,
	
    //implicated
   implicatedReference,
   implicatedIdentifierUse,
   implicatedIdentifierTypeCodingSystem,
   implicatedIdentifierTypeCodingVersion,
   implicatedIdentifierTypeCodingCode,
   implicatedIdentifierTypeCodingDisplay,
   implicatedIdentifierTypeCodingUserSelected,
   implicatedIdentifierTypeText,
   implicatedIdentifierSystem,
   implicatedIdentifierValue,
   implicatedIdentifierPeriodStart,
   implicatedIdentifierPeriodEnd,
   implicatedIdentifierAssignerDisplay,
   implicatedDisplay,
   
   //detail
   detail,
   
   //reference
   reference,
   
   //mitigation
   mitigationActionCodingSystem,
   mitigationActionCodingVersion,
   mitigationActionCodingCode,
   mitigationActionCodingDisplay,
   mitigationActionCodingUserSelected,
   mitigationActionText,
   mitigationDate,
   mitigationAuthorReference,
   mitigationAuthorIdentifierUse,
   mitigationAuthorIdentifierTypeCodingSystem,
   mitigationAuthorIdentifierTypeCodingVersion,
   mitigationAuthorIdentifierTypeCodingCode,
   mitigationAuthorIdentifierTypeCodingDisplay,
   mitigationAuthorIdentifierTypeCodingUserSelected,
   mitigationAuthorIdentifierTypeText,
   mitigationAuthorIdentifierSystem,
   mitigationAuthorIdentifierValue,
   mitigationAuthorIdentifierPeriodStart,
   mitigationAuthorIdentifierPeriodEnd,
   mitigationAuthorIdentifierAssignerDisplay,
   mitigationAuthorDisplay;

	

	@Override
	public void setResourceDataFromMap(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		setValuesFromMap(map);
		setResourceData();
		
	}

	
	private void setValuesFromMap(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		
		
	this.identifierValue                  =      map.get("DetectedIssue.identifierValue");
	this.categoryCodingCode               = 	 map.get("DetectedIssue.categoryCodingCode");
	this.categoryCodingSystem             = 	 map.get("DetectedIssue.categoryCodingSystem");
	this.categoryCodingDisplay            = 	 map.get("DetectedIssue.categoryCodingDisplay");
	this.severity                         = 	 map.get("DetectedIssue.severity");
	this.patientIdentifierTypeCodingCode  = 	 map.get("DetectedIssue.patientIdentifierTypeCodingCode");
	this.patientIdentifierSystem          = 	 map.get("DetectedIssue.patientIdentifierSystem");
	this.patientIdentifierValue           = 	 map.get("DetectedIssue.patientIdentifierValue");
	this.patientIdentifierPeriodStart     = 	 map.get("DetectedIssue.patientIdentifierPeriodStart");
	this.patientIdentifierPeriodEnd       = 	 map.get("DetectedIssue.patientIdentifierPeriodEnd");
	this.patientIdentifierAssignerDisplay = 	 map.get("DetectedIssue.patientIdentifierAssignerDisplay");
	this.date                             = 	 map.get("DetectedIssue.date");
	this.detail                           = 	 map.get("DetectedIssue.detail");
		
	}

	
	@Override
	public void setResourceData() {
		// TODO Auto-generated method stub
		
		//detectedissue.setIdentifier(new Identifier().setValue(this.identifierValue  ));
		IdentifierUtil IdentifierUtil = new IdentifierUtil();
		//IdentifierUtil.SetPatientMinimalArgs(getId());
		IdentifierUtil.SetPatientArgs(this.identifierValue,"","","","");
		detectedissue.setIdentifier(IdentifierUtil.getIdentifier());
		
		detectedissue.setCategory(new CodeableConcept().addCoding(new Coding().setCode(this.categoryCodingCode).setSystem(this.categoryCodingSystem ).setDisplay(this.categoryCodingDisplay)));
		detectedissue.setSeverity(DetectedIssueSeverity.valueOf(getSeverity()));
		detectedissue.setPatient(FHIRUtils.buildReference(this.patientIdentifierValue, this.patientIdentifierTypeCodingCode, this.patientIdentifierSystem, this.patientIdentifierPeriodStart, this.patientIdentifierPeriodEnd, this.patientIdentifierAssignerDisplay));
		try {
			detectedissue.setDate(new SimpleDateFormat("yyyyMMdd").parse(this.date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		detectedissue.setDetail(this.detail);
		
	}

	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.detectedissue ;
		
	}
}
