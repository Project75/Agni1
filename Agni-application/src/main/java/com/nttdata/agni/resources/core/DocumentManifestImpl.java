package com.nttdata.agni.resources.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.DocumentManifest;
import org.hl7.fhir.dstu3.model.DocumentManifest.DocumentManifestContentComponent;
import org.hl7.fhir.dstu3.model.Enumerations.DocumentReferenceStatus;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.exceptions.FHIRException;

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
public class DocumentManifestImpl extends AbstractResource{

	DocumentManifest resource;
	private String masterIdentifier;
	private String identifier;
	private String status;
	private String type;
	private String subject;
	private String created;
	private String author;
	private String recipient;
	private String source;
	private String description;
	private String content;
	private String contentPX;
	private String contentPAttachment;
	private String contentPReference;
	private String related;
	private String relatedIdentifier;
	private String relatedRef;

	
	String resourceName="DocumentManifest";
	
	public DocumentManifestImpl() {
		
		// TODO Auto-generated constructor stub
		this.resource =  new DocumentManifest();
		resource.setId(IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	
	public void setValuesFromMap(HashMap<String,String> map) {
		this.masterIdentifier = map.get("documentmanifest.masteridentifier");
		this.identifier = map.get("documentmanifest.identifier");
		this.status = map.get("documentmanifest.status");
		this.type = map.get("documentmanifest.type");
		this.subject = map.get("documentmanifest.subject");
		this.created = map.get("documentmanifest.created");
		this.author = map.get("documentmanifest.author");
		this.recipient = map.get("documentmanifest.recipient");
		this.source = map.get("documentmanifest.source");
		this.description = map.get("documentmanifest.description");
		this.content = map.get("documentmanifest.content");
		this.contentPX = map.get("documentmanifest.content.px");
		this.contentPAttachment = map.get("documentmanifest.content.pattachment");
		this.contentPReference = map.get("documentmanifest.content.preference");
		this.related = map.get("documentmanifest.related");
		this.relatedIdentifier = map.get("documentmanifest.related.identifier");
		this.relatedRef = map.get("documentmanifest.related.ref");

	}
	
	@Override
	public void setResourceData() {
		resource.setMasterIdentifier(new Identifier().setValue(masterIdentifier));
		resource.addIdentifier(new Identifier().setValue(identifier));
		resource.setStatus(DocumentReferenceStatus.CURRENT);
		resource.setType(new CodeableConcept().setText(type));
		resource.setSubject(new Reference().setReference(subject));
		//resource.setCreated(new Date("yyyy-MM-dd"));//(new DateTimeType(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(created)));
		resource.addAuthor(new Reference().setReference(author));
		resource.addRecipient(new Reference().setReference(recipient));
		resource.setSource(source);
		resource.setDescription(description);

		DocumentManifestContentComponent contentType = new DocumentManifestContentComponent();
		try {
			contentType.addChild(contentPAttachment);
		} catch (FHIRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //contentPReference
		resource.getContent().add(contentType);
		
		


		resource.addRelated().setRef(new Reference().setReference(relatedIdentifier));
		resource.addRelated().setRefTarget(resource); //relatedRef);

}

	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.resource;
	}
	

	
}
