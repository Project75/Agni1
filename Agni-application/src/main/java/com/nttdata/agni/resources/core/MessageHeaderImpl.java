/**
 * 
 */
package com.nttdata.agni.resources.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
		this.messageheader =  new MessageHeader();
	}

	@Override
	public void setResourceDataFromMap(HashMap<String, String> data) {
		
		setValuesFromMap(data);
		setResourceData();

	}
	
	public void setValuesFromMap(HashMap<String,String> map) {
		this.source = map.get("messageheader.source.name");
		this.destination = map.get("messageheader.destination.name");
		this.triggerevent = map.get("messageheader.event.code");
		this.date=map.get("messageheader.timestamp");
	}
	
	@Override
	public void setResourceData() {
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
	/*public MessageHeader getMessageheader() {
		return messageheader;
	}

	public void setMessageheader(MessageHeader messageheader) {
		this.messageheader = messageheader;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	*/
	
	public String toString(String test) {
		// TODO Auto-generated method stub
		return source+" "+destination+" "+date +" "+triggerevent;
	}
	
}