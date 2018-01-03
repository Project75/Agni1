/**
 * 
 */
package com.nttdata.agni.resources.core;

import com.nttdata.agni.resources.utils.TransformMap;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.DocumentReference;
import org.hl7.fhir.dstu3.model.DocumentReference.DocumentReferenceContentComponent;
import org.hl7.fhir.dstu3.model.DocumentReference.DocumentReferenceContextComponent;
import org.hl7.fhir.dstu3.model.DocumentReference.DocumentReferenceRelatesToComponent;
import org.hl7.fhir.dstu3.model.DocumentReference.DocumentRelationshipType;
import org.hl7.fhir.dstu3.model.Enumerations.DocumentReferenceStatus;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Period;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;

import ca.uhn.fhir.model.primitive.IdDt;

/**
 * Copyright NTT Data
 * Agni-Applicationo-
 * @author Harendra Pandey
 *
 */
@ToString
@Getter 
@Setter 
public class DocumentReferenceImpl extends AbstractResource{
			
	DocumentReference resource;
	private String masterIdentifier;
	private String identifier;
	private String status;
	private String docStatus;
	private String type;
	private String refClass;
	private String subject;
	private String created;
	private String indexed;
	private String author;
	private String authenticator;
	private String custodian;
	private DocumentReferenceRelatesToComponent relatesTo;
	private DocumentRelationshipType relatesToCode;
	private String relatesToTarget;
	private String description;
	private String securityLabel;
	private String content;
	private String contentAttachment;
	private String contentFormat;
	private String context;
	private String contextEncounter;
	private String contextEvent;
	private String contextPeriod;
	private String contextFacilityType;
	private String contextPracticeSetting;
	private String contextSourcePatientInfo;
	private String contextRelated;
	private String contextRelatedidentifier;
	private String contextRelatedref;

	
	String resourceName="DocumentReference";
	
	public DocumentReferenceImpl() {
		
		// TODO Auto-generated constructor stub
		this.resource =  new DocumentReference();
		resource.setId(IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(TransformMap data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	
	public void setValuesFromMap(TransformMap map) {
		this.masterIdentifier = map.get("documentreference.masteridentifier");
		this.identifier = map.get("documentreference.identifier");
		this.status = map.get("documentreference.status");
		this.docStatus = map.get("documentreference.docstatus");
		this.type = map.get("documentreference.type");
		this.refClass = map.get("documentreference.refclass");
		this.subject = map.get("documentreference.subject");
		this.created = map.get("documentreference.created");
		this.indexed = map.get("documentreference.indexed");
		this.author = map.get("documentreference.author");
		this.authenticator = map.get("documentreference.authenticator");
		this.custodian = map.get("documentreference.custodian");
		//this.relatesTo = map.get("documentreference.relatesto");
		//this.relatesToCode = map.get("documentreference.relatesto.code");
		this.relatesToTarget = map.get("documentreference.relatesto.target");
		this.description = map.get("documentreference.description");
		this.securityLabel = map.get("documentreference.securitylabel");
		this.content = map.get("documentreference.content");
		this.contentAttachment = map.get("documentreference.content.attachment");
		this.contentFormat = map.get("documentreference.content.format");
		this.context = map.get("documentreference.context");
		this.contextEncounter = map.get("documentreference.context.encounter");
		this.contextEvent = map.get("documentreference.context.event");
		this.contextPeriod = map.get("documentreference.context.period");
		this.contextFacilityType = map.get("documentreference.context.facilitytype");
		this.contextPracticeSetting = map.get("documentreference.context.practicesetting");
		this.contextSourcePatientInfo = map.get("documentreference.context.sourcepatientinfo");
		this.contextRelated = map.get("documentreference.context.related");
		this.contextRelatedidentifier = map.get("documentreference.context.related.identifier");
		this.contextRelatedref = map.get("documentreference.context.related.ref");



	}
	
	@Override
	public void setResourceData() {
		resource.setMasterIdentifier(new Identifier().setValue(resource.getId()));
		resource.addIdentifier(new Identifier().setValue(resource.getId()));
		resource.setStatus(DocumentReferenceStatus.CURRENT);
		 
		
		//resource.setDocStatus(new Coding().setCode(docStatus));
		resource.setType(new CodeableConcept().setText(type));
		resource.setClass_(new CodeableConcept().setText(refClass));
		resource.setSubject(new Reference().setReference(subject));
		//resource.setCreated(created);
		//resource.setIndexed(indexed);
		resource.addAuthor(new Reference().setReference(author));
		resource.setAuthenticator(new Reference().setReference(authenticator));
		resource.setCustodian(new Reference().setReference(custodian));
		//resource.addRelatesTo(relatesTo);
		resource.addRelatesTo().setCode(relatesToCode);
		resource.addRelatesTo().setTarget(new Reference().setReference(relatesToTarget));
		resource.setDescription(description);
		resource.addSecurityLabel(new CodeableConcept().setText(securityLabel));
		DocumentReferenceContentComponent t = new DocumentReferenceContentComponent();
		//t.ad
		resource.addContent(t );
		DocumentReferenceContextComponent context = new DocumentReferenceContextComponent();
		
		
		
		context.setEncounter(new Reference().setReference(contextEncounter));
		context.addEvent(new CodeableConcept().setText(contextEvent));
		context.setPeriod(new Period());
		context.setFacilityType(new CodeableConcept().setText(contextFacilityType));
		context.setPracticeSetting(new CodeableConcept().setText(contextPracticeSetting));
		context.setSourcePatientInfo(new Reference().setReference(contextSourcePatientInfo));
		context.addRelated().setRef(new Reference().setReference(contextRelated));
		context.addRelated().setIdentifier(new Identifier().setValue(contextRelatedidentifier));
		resource.setContext(context);
}

	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.resource;
	}
	

	
}
