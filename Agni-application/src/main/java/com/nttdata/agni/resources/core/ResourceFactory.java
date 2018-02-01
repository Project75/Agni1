/**
 * 
 */
package com.nttdata.agni.resources.core;

/**
 * Copyright NTT Data
 * core
 * @author Harendra Pandey
 *
 */
public class ResourceFactory {
	public static AbstractResource getResource(String type){
		//if(type.equalsIgnoreCase("patient")) return new PatientImpl();
		
		switch (type.toLowerCase()) {
	      case "patient":
	        return new PatientImpl();
	      case "observation":
		        return new ObservationImpl();
	      case "encounter":
		        return new EncounterImpl();
	      case "bundle":
		        return new BundleImpl();
	      case "messageheader":
		        return new MessageHeaderImpl();
			//Sameer
	      case "appointment" :
	    	  	return new AppointmentImpl();
	      case "immunization" :
	    	  return new ImmunizationImpl();
			  //Neha
		  case "practitioner":
		        return new PractitionerImpl(); 
	      case "practitionerrole":
		        return new PractitionerRoleImpl();
	      case "procedure":
		        return new ProcedureImpl();
	      case "procedurerequest":
		        return new ProcedureRequestImpl();
	      case "referralrequest":
		        return new ReferralRequestImpl();
				//Ankit
		  case "medicationstatement":
		        return new MedicationStatementImpl();
	      case "organization":
		        return new OrganizationImpl();
	      case "dataelement":
		        return new DataElementImpl();
			//harendra
		  case "documentmanifest":
		        return new DocumentManifestImpl();				
		  case "documentreference":
		        return new DocumentReferenceImpl();
		  case "immunizationrecommendation":
		        return new ImmunizationRecommendationImpl();
		  case "medicationadministration":
		        return new MedicationAdministrationImpl();
		  case "operationoutcome":
		        return new OperationOutcomeImpl();
		  case "location":
		        return new LocationImpl();
		  case "bodysite":
		        return new BodySiteImpl();
		  case "diagnosticreport":
		        return new DiagnosticReportImpl();
		  case "detectedissue":
		        return new DetectedIssueImpl();
		  case "VisionPrescription":
		        return new VisionPrescriptionImpl(); 
	      case "PaymentNotice":
		        return new PaymentNoticeImpl(); 
	      case "Specimen" : return new SpecimenImpl();
	      case "AllergyIntolerance":return new AllergyIntoleranceImpl();     
	      case "AppointmentResponse" : return new AppointmentResponseImpl();
	      case "CarePlan" : return new CarePlanImpl();
	      case "CareTeam" : return new CareTeamImpl();
	      case "ChargeItem" : return new ChargeItemImpl();
	      case "Condition" : return new ConditionImpl();
	      case "Consent" : return new ConsentImpl();
	      case "Coverage" : return new CoverageImpl();
	      case "Goal" : return new GoalImpl();
	      case "Media" : return new MediaImpl();
	      case "Person" : return new PersonImpl();
	      case "RelatedPerson" : return new RelatedPersonImpl();
	      case "SupplyRequest" : return new SupplyRequestImpl();

	      default:
	          // Error
	          return null;
	    }
		
		
	}
}
