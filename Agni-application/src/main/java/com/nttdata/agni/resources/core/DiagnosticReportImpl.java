package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.CodeableConcept.*;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.DiagnosticReport;
import org.hl7.fhir.dstu3.model.DiagnosticReport.*;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.Type;

import com.nttdata.agni.resources.utils.FHIRUtils;
import com.nttdata.agni.resources.utils.TransformUtils;

import ca.uhn.fhir.model.primitive.IdDt;

public class DiagnosticReportImpl  extends AbstractResource {
	

	DiagnosticReport diagnosticreport ;

	
	public DiagnosticReportImpl() {
		// TODO Auto-generated constructor stub
		
		this.diagnosticreport = new DiagnosticReport ();
		diagnosticreport.setId(IdDt.newRandomUuid());

	}

	

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
	
	//basedon
	basedonReference,
	basedonIdentifierUse,
	basedonIdentifierTypeCodingSystem,
	basedonIdentifierTypeCodingVersion,
	basedonIdentifierTypeCodingCode,
	basedonIdentifierTypeCodingDisplay,
	basedonIdentifierTypeCodingUserSelected,
	basedonIdentifierTypeText,
	basedonIdentifierSystem,
	basedonIdentifierValue,
	basedonIdentifierPeriodStart,
	basedonIdentifierPeriodEnd,
	basedonIdentifierAssignerDisplay,
	basedonDisplay,
	
	//status
	status,
	
	//category
	categoryCodingSystem,
	categoryCodingVersion,
	categoryCodingCode,
	categoryCodingDisplay,
	categoryCodingUserSelected,
	categoryText,
	
	//code
	codeCodingSystem,
	codeCodingVersion,
	codeCodingCode,
	codeCodingDisplay,
	codeCodingUserSelected,
	codeText,
	
	//subject
	subjectReference,
	subjectIdentifierUse,
	subjectIdentifierTypeCodingSystem,
	subjectIdentifierTypeCodingVersion,
	subjectIdentifierTypeCodingCode,
	subjectIdentifierTypeCodingDisplay,
	subjectIdentifierTypeCodingUserSelected,
	subjectIdentifierTypeText,
	subjectIdentifierSystem,
	subjectIdentifierValue,
	subjectIdentifierPeriodStart,
	subjectIdentifierPeriodEnd,
	subjectIdentifierAssignerDisplay,
	subjectDisplay,
	
	//context
	contextReference,
	contextIdentifierUse,
	contextIdentifierTypeCodingSystem,
	contextIdentifierTypeCodingVersion,
	contextIdentifierTypeCodingCode,
	contextIdentifierTypeCodingDisplay,
	contextIdentifierTypeCodingUserSelected,
	contextIdentifierTypeText,
	contextIdentifierSystem,
	contextIdentifierValue,
	contextIdentifierPeriodStart,
	contextIdentifierPeriodEnd,
	contextIdentifierAssignerDisplay,
	contextDisplay,
	
	effectiveEffectivePeriodStart,
	effectiveEffectivePeriodEnd,	
	//issues
	 issued,
	
	//performer
	
	performerRoleCodingSystem,
	performerRoleCodingVersion,
	performerRoleCodingperformerCode,
	performerRoleCodingDisplay,
	performerRoleCodingUserSelected,
	performerRoleText,
	performerActorReference,
	performerActorIdentifierUse,
	performerActorIdentifierTypeCodingSystem,
	performerActorIdentifierTypeCodingVersion,
	performerActorIdentifierTypeCodingCode,
	performerActorIdentifierTypeCodingDisplay,
	performerActorIdentifierTypeCodingUserSelected,
	performerActorIdentifierTypeText,
	performerActorIdentifierSystem,
	performerActorIdentifierValue,
	performerActorIdentifierPeriodStart,
	performerActorIdentifierPeriodEnd,
	performerActorIdentifierAssignerDisplay,
	performerActorDisplay,
	
	//specimen
	specimenReference,
	specimenIdentifierUse,
	specimenIdentifierTypeCodingSystem,
	specimenIdentifierTypeCodingVersion,
	specimenIdentifierTypeCodingCode,
	specimenIdentifierTypeCodingDisplay,
	specimenIdentifierTypeCodingUserSelected,
	specimenIdentifierTypeText,
	specimenIdentifierSystem,
	specimenIdentifierValue,
	specimenIdentifierPeriodStart,
	specimenIdentifierPeriodEnd,
	specimenIdentifierAssignerDisplay,
	specimenDisplay,
	
	//result
	resultReference,
	resultIdentifierUse,
	resultIdentifierTypeCodingSystem,
	resultIdentifierTypeCodingVersion,
	resultIdentifierTypeCodingCode,
	resultIdentifierTypeCodingDisplay,
	resultIdentifierTypeCodingUserSelected,
	resultIdentifierTypeText,
	resultIdentifierSystem,
	resultIdentifierValue,
	resultIdentifierPeriodStart,
	resultIdentifierPeriodEnd,
	resultIdentifierAssignerDisplay,
	resultDisplay,
	
	//ImagingStudy
	imagingstudyReference,
	imagingstudyIdentifierUse,
	imagingstudyIdentifierTypeCodingSystem,
	imagingstudyIdentifierTypeCodingVersion,
	imagingstudyIdentifierTypeCodingCode,
	imagingstudyIdentifierTypeCodingDisplay,
	imagingstudyIdentifierTypeCodingUserSelected,
	imagingstudyIdentifierTypeText,
	imagingstudyIdentifierSystem,
	imagingstudyIdentifierValue,
	imagingstudyIdentifierPeriodStart,
	imagingstudyIdentifierPeriodEnd,
	imagingstudyIdentifierAssignerDisplay,
	imagingstudyDisplay,
	
	//image
	imageComment,
	imageLinkReference,
	imageLinkIdentifierUse,
	imageLinkIdentifierTypeCodingSystem,
	imageLinkIdentifierTypeCodingVersion,
	imageLinkIdentifierTypeCodingCode,
	imageLinkIdentifierTypeCodingDisplay,
	imageLinkIdentifierTypeCodingUserSelected,
	imageLinkIdentifierTypeText,
	imageLinkIdentifierSystem,
	imageLinkIdentifierValue,
	imageLinkIdentifierPeriodStart,
	imageLinkIdentifierPeriodEnd,
	imageLinkIdentifierAssignerDisplay,
	imageLinkDisplay,
	
	//conclusion
	conclusion,
	
	//codedDiagnosis
	codeddiagnosisCodingSystem,
	codeddiagnosisCodingVersion,
	codeddiagnosisCodingCode,
	codeddiagnosisCodingDisplay,
	codeddiagnosisCodingUserSelected,
	codeddiagnosisText,
	
	//presentedForm
	presentedformContenttype,
	presentedformLanguage,
	presentedformData,
	presenteformUrl,
	presenteformSize,
	presenteformHash,
	presenteformTitle,
	presenteformCreation,
	effectiveEffectivedatetime,
	
  // If condition variable
	PRT4;
	
	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		// TODO Auto-generated method stub
		setValuesFromMap(data);
		setResourceData();
		
	}

	private void setValuesFromMap(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		
		this.identifierValue = map.get("DiagnosticReport.identifierValue");
		this.status = map.get("DiagnosticReport.status");
		this.categoryCodingCode = map.get("DiagnosticReport.categoryCodingCode");
		this.codeCodingCode = map.get("DiagnosticReport.codeCodingCode");
		this.subjectIdentifierTypeCodingCode = map.get("DiagnosticReport.subjectIdentifierTypeCodingCode"); // code or Text ?
		this.subjectIdentifierSystem = map.get("DiagnosticReport.subjectIdentifierSystem");
		this.subjectIdentifierValue = map.get("DiagnosticReport.subjectIdentifierValue");
		this.subjectIdentifierPeriodStart = map.get("DiagnosticReport.subjectIdentifierPeriodStart");
		this.subjectIdentifierPeriodEnd = map.get("DiagnosticReport.subjectIdentifierPeriodEnd");
		this.subjectIdentifierAssignerDisplay = map.get("DiagnosticReport.subjectIdentifierAssignerDisplay"); // id or display ?
		this.contextIdentifierTypeCodingCode = map.get("DiagnosticReport.contextIdentifierTypeCodingCode");
		this.contextIdentifierSystem = map.get("DiagnosticReport.contextIdentifierSystem");
		this.contextIdentifierValue = map.get("DiagnosticReport.contextIdentifierValue");
		this.contextIdentifierPeriodStart = map.get("DiagnosticReport.contextIdentifierPeriodStart");
		this.contextIdentifierPeriodEnd = map.get("DiagnosticReport.contextIdentifierPeriodEnd");
		this.contextIdentifierAssignerDisplay = map.get("DiagnosticReport.contextIdentifierAssignerDisplay");
		this.effectiveEffectivedatetime = map.get("DiagnosticReport.effectiveEffectivedatetime");
		this.issued = map.get("DiagnosticReport.issued");
		this.performerRoleCodingDisplay = map.get("DiagnosticReport.performerRoleCodingDisplay");
		this.performerActorReference = map.get("DiagnosticReport.performerActorReference");
		this.PRT4= map.get("DiagnosticReport.PRT4");

		
	}

	@Override
	public void setResourceData() {
		// TODO Auto-generated method stub
		diagnosticreport.addIdentifier().setValue(this.identifierValue);
		diagnosticreport.setStatus(DiagnosticReportStatus.UNKNOWN); // .valueOf(this.status)
		diagnosticreport.setCategory(new CodeableConcept().addCoding(new Coding().setCode(this.categoryCodingCode)));
		diagnosticreport.setCode(new CodeableConcept().addCoding(new Coding().setCode(this.codeCodingCode)));
		diagnosticreport.setSubject(FHIRUtils.buildReference(this.subjectIdentifierValue, this.subjectIdentifierTypeCodingCode, this.subjectIdentifierSystem, this.subjectIdentifierPeriodStart, this.subjectIdentifierPeriodEnd, this.subjectIdentifierAssignerDisplay));
		diagnosticreport.setContext(FHIRUtils.buildReference(this.contextIdentifierValue, this.contextIdentifierTypeCodingCode, this.contextIdentifierSystem, this.contextIdentifierPeriodStart, this.contextIdentifierPeriodEnd, this.contextIdentifierAssignerDisplay));
	    try {
			diagnosticreport.setIssued(new SimpleDateFormat("yyyyMMddHHmm").parse(this.issued));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		diagnosticreport.setEffective(DateTimeType.parseV3(this.effectiveEffectivedatetime));
		if(this.PRT4.equalsIgnoreCase("PO"))
	    {
		 diagnosticreport.addPerformer().setRole(new CodeableConcept().addCoding(new Coding().setDisplay(this.performerRoleCodingDisplay))).setActor(new Reference().setReference(this.performerActorReference));
	    }
				}
		
		
	
	
	

	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.diagnosticreport ;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
}
