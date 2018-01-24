/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.nttdata.agni.resources.utils.TransformMap;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.MessageHeader;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.MessageHeader.MessageDestinationComponent;
import org.hl7.fhir.dstu3.model.MessageHeader.MessageSourceComponent;
import org.hl7.fhir.dstu3.model.Reference;

import ca.uhn.fhir.model.primitive.IdDt;
import org.hl7.fhir.dstu3.model.ResourceFactory;
import org.hl7.fhir.exceptions.FHIRException;
/**
 * Copyright NTT Data
 * Agni-Applicationo-
 * @author Harendra Pandey
 *
 */
@ToString
@Getter 
@Setter 
public class MessageHeaderImpl extends AbstractResource{

	MessageHeader messageheader;
	private String source;
	private String destination;
	private String date;
	private String triggerevent;
	
	String resourceName="messageheader";
	
	public MessageHeaderImpl() {
		super();
		// TODO Auto-generated constructor stub
		try {
			this.messageheader = (MessageHeader) ResourceFactory.createResource("MessageHeader");
		} catch (FHIRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} ;
				//new MessageHeader();
		messageheader.setId(ResourceFactory.createUUID());
				//IdDt.newRandomUuid());
	}

	@Override
	public void setResourceDataFromMap(TransformMap data) {
		
		setValuesFromMap(data);
		setResourceData(data);

	}
	
	public void setValuesFromMap(TransformMap map) {
		this.source = map.get("messageheader.source.name");
		this.destination = map.get("messageheader.destination.name");
		this.triggerevent = map.get("messageheader.event.code");
		this.date=map.get("messageheader.timestamp");
	}
	
	@Override
	public void setResourceData(TransformMap map) {
		List<MessageDestinationComponent> theDestination = new ArrayList<MessageDestinationComponent>();
		MessageDestinationComponent messageDestinationComponent = new MessageDestinationComponent();		
		messageDestinationComponent.setEndpoint(destination);
		theDestination.add(messageDestinationComponent);
		//messageheader.addDestination(messageDestinationComponent);
		messageheader.setDestination(theDestination);
		
		MessageSourceComponent messageSourceComponent = new MessageSourceComponent();
		messageSourceComponent.setEndpoint(source);
		//messageheader.setReceiver(value);
		messageheader.setSource(messageSourceComponent);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if (getDate() != null){
			messageheader.setTimestamp(new java.util.Date());
					//formatter.parse(getDate()));
		}
}

	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return this.messageheader;
	}
	
	public String toString(String test) {
		// TODO Auto-generated method stub
		return source+" "+destination+" "+date +" "+triggerevent;
	}
	
}