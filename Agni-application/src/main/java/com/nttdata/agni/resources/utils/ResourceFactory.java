/**
 * 
 */
package com.nttdata.agni.resources.utils;

import com.nttdata.agni.resources.core.AbstractResource;
import com.nttdata.agni.resources.core.AllergyIntoleranceImpl;
import com.nttdata.agni.resources.core.AppointmentImpl;
import com.nttdata.agni.resources.core.AppointmentResponseImpl;
import com.nttdata.agni.resources.core.BodySiteImpl;
import com.nttdata.agni.resources.core.BundleImpl;
import com.nttdata.agni.resources.core.CarePlanImpl;
import com.nttdata.agni.resources.core.CareTeamImpl;
import com.nttdata.agni.resources.core.ChargeItemImpl;
import com.nttdata.agni.resources.core.ConditionImpl;
import com.nttdata.agni.resources.core.ConsentImpl;
import com.nttdata.agni.resources.core.CoverageImpl;
import com.nttdata.agni.resources.core.DataElementImpl;
import com.nttdata.agni.resources.core.DetectedIssueImpl;
import com.nttdata.agni.resources.core.DiagnosticReportImpl;
import com.nttdata.agni.resources.core.DocumentManifestImpl;
import com.nttdata.agni.resources.core.DocumentReferenceImpl;
import com.nttdata.agni.resources.core.EncounterImpl;
import com.nttdata.agni.resources.core.GoalImpl;
import com.nttdata.agni.resources.core.ImmunizationImpl;
import com.nttdata.agni.resources.core.ImmunizationRecommendationImpl;
import com.nttdata.agni.resources.core.LocationImpl;
import com.nttdata.agni.resources.core.MediaImpl;
import com.nttdata.agni.resources.core.MedicationAdministrationImpl;
import com.nttdata.agni.resources.core.MedicationStatementImpl;
import com.nttdata.agni.resources.core.MessageHeaderImpl;
import com.nttdata.agni.resources.core.ObservationImpl;
import com.nttdata.agni.resources.core.OperationOutcomeImpl;
import com.nttdata.agni.resources.core.OrganizationImpl;
import com.nttdata.agni.resources.core.PatientImpl;
import com.nttdata.agni.resources.core.PaymentNoticeImpl;
import com.nttdata.agni.resources.core.PersonImpl;
import com.nttdata.agni.resources.core.PractitionerImpl;
import com.nttdata.agni.resources.core.PractitionerRoleImpl;
import com.nttdata.agni.resources.core.ProcedureImpl;
import com.nttdata.agni.resources.core.ProcedureRequestImpl;
import com.nttdata.agni.resources.core.ReferralRequestImpl;
import com.nttdata.agni.resources.core.RelatedPersonImpl;
import com.nttdata.agni.resources.core.SpecimenImpl;
import com.nttdata.agni.resources.core.SupplyRequestImpl;
import com.nttdata.agni.resources.core.VisionPrescriptionImpl;

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
		  case "visionprescription":
		        return new VisionPrescriptionImpl(); 
	      case "paymentnotice":
		        return new PaymentNoticeImpl(); 
	      case "specimen" : return new SpecimenImpl();
	      case "allergyintolerance":return new AllergyIntoleranceImpl();     
	      case "appointmentresponse" : return new AppointmentResponseImpl();
	      case "careplan" : return new CarePlanImpl();
	      case "careteam" : return new CareTeamImpl();
	      case "chargeitem" : return new ChargeItemImpl();
	      case "condition" : return new ConditionImpl();
	      case "consent" : return new ConsentImpl();
	      case "coverage" : return new CoverageImpl();
	      case "goal" : return new GoalImpl();
	      case "media" : return new MediaImpl();
	      case "person" : return new PersonImpl();
	      case "relatedperson" : return new RelatedPersonImpl();
	      case "supplyrequest" : return new SupplyRequestImpl();

	      default:
	          // Error
	          return null;
	    }
		
		
	}
}
