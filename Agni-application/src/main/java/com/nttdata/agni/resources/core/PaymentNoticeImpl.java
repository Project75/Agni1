package com.nttdata.agni.resources.core;


import java.util.Date;
import com.nttdata.agni.resources.utils.TransformMap;

import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.PaymentNotice;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;




public class PaymentNoticeImpl extends AbstractResource{

	PaymentNotice paymentnotice ;
	
	public PaymentNoticeImpl() {
		// TODO Auto-generated constructor stub
		
		this.paymentnotice = new PaymentNotice();
		
	}
	

	String identifier, status, requestreference, requestidentifier, requestdisplay, responsereference, responseidentifier, responsedisplay,
	statusDate, created, paymentStatus, targetreference, targetidentifier, targetdisplay, providerreference, provideridentifier, providerdisplay, organizationreference, organizationidentifier, organizationdisplay;


@Override
public void setResourceDataFromMap(TransformMap data) {
	// TODO Auto-generated method stub
	setValuesFromMap(data);
	setResourceData(data);
	
}

private void setValuesFromMap(TransformMap map) {
	// TODO Auto-generated method stub
	this.identifier = map.get("PaymentNotice.identifier");
	this.status = map.get("PaymentNotice.status");
	this.requestreference = map.get("PaymentNotice.request.reference");
	this.requestidentifier = map.get("PaymentNotice.request.identifier");
	this.requestdisplay = map.get("PaymentNotice.request.display");
	this.responsereference = map.get("PaymentNotice.response.reference");
	this.responseidentifier = map.get("PaymentNotice.response.identifier");
	this.responsedisplay = map.get("PaymentNotice.response.display");
	this.statusDate = map.get("PaymentNotice.statusDate");
	this.created = map.get("PaymentNotice.created");
	this.paymentStatus = map.get("PaymentNotice.paymentStatus");
	this.targetreference = map.get("PaymentNotice.target.reference");
	this.targetidentifier = map.get("PaymentNotice.target.identifier");
	this.targetdisplay = map.get("PaymentNotice.target.display");
	this.providerreference = map.get("PaymentNotice.provider.reference");
	this.provideridentifier = map.get("PaymentNotice.provider.identifier");
	this.providerdisplay = map.get("PaymentNotice.provider.display");
	this.organizationreference = map.get("PaymentNotice.organization.reference");
	this.organizationidentifier = map.get("PaymentNotice.organization.identifier");
	this.organizationdisplay = map.get("PaymentNotice.organization.display");
	

}

@Override
public void setResourceData(TransformMap map) {
	// TODO Auto-generated method stub
	paymentnotice.addIdentifier().setValue(identifier);
	paymentnotice.setRequest(new Reference().setDisplay(requestdisplay).setIdentifier(new Identifier().setValue(requestidentifier)).setReference(requestreference));
	paymentnotice.setRequest(new Reference().setDisplay(responsedisplay).setIdentifier(new Identifier().setValue(responseidentifier)).setReference(responsereference));
}
public Resource getResource() {
	// TODO Auto-generated method stub
	return this.paymentnotice;
}


@Override
public String toString() {
	return identifier ;
}

}