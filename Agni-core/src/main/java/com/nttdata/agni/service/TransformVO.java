package com.nttdata.agni.service;

public class TransformVO {
	String familyName, givenName, id, gender, DOB, AddressLine, AddressCity, AddressState, AddressPostalCode, AddressCountry, 
			Telecom, MaritalStatus, Deceased, Birth, ContactRel, ContactName, ContactTel, ContactAddress, ContactGender, ContactOrg,
			GeneralPractitioner, Link, ObservationID, ObservationStatus, ObservationCode, ObservationSubject, ObservationEffective,
			ObservationIssued, ObservationPerformer, ObservationValue, ObservationInterpretation, ObservationComment, ObservationBodySite, 
			ObservationMethod, ObservationDevice, ReferenceRangeLow, ReferenceRangeHigh, ReferenceRangeType, ReferenceRangeAppliesTo, ReferenceRangeText;
	
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getgender() {
		return gender;
	}
	public void setgender(String gender) {
		this.gender = gender;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String DOB) {
		this.DOB = DOB.substring(0, 4) +"-"+ DOB.substring(4, 6) +"-"+ DOB.substring(6, 8);
	}
	public String getAddressLine() {
		return AddressLine;
	}
	public void setAddressLine(String AddressLine) {
		this.AddressLine = AddressLine;
	}
	public String getAddressCity() {
		return AddressCity;
	}
	public void setAddressCity(String AddressCity) {
		this.AddressCity = AddressCity;
	}
	public String getAddressState() {
		return AddressState;
	}
	public void setAddressState(String AddressState) {
		this.AddressState = AddressState;
	}
	public String getAddressPostalCode() {
		return AddressPostalCode;
	}
	public void setAddressPostalCode(String AddressPostalCode) {
		this.AddressPostalCode = AddressPostalCode;
	}
	public String getAddressCountry() {
		return AddressCountry;
	}
	public void setAddressCountry(String AddressCountry) {
		this.AddressCountry = AddressCountry;
	}
	public String getTelecom() {
		return Telecom;
	}
	public void setTelecom(String Telecom) {
		this.Telecom = Telecom;
	}
	public String getMaritalStatus() {
		return MaritalStatus;
	}
	public void setMaritalStatus(String MaritalStatus) {
		this.MaritalStatus = MaritalStatus;
	}
	public String getDeceased() {
		return Deceased;
	}
	public void setDeceased(String Deceased) {
		this.Deceased = Deceased;
	}
	public String getBirth() {
		return Birth;
	}
	public void setBirth(String Birth) {
		this.Birth = Birth;
	}
	public String getContactRel() {
		return ContactRel;
	}
	public void setContactRel(String ContactRel) {
		this.ContactRel = ContactRel;
	}
	public String getContactName() {
		return ContactName;
	}
	public void setContactName(String ContactName) {
		this.ContactName = ContactName;
	}
	public String getContactTel() {
		return ContactTel;
	}
	public void setContactTel(String ContactTel) {
		this.ContactTel = ContactTel;
	}
	public String getContactAddress() {
		return ContactAddress;
	}
	public void setContactAddress(String ContactAddress) {
		this.ContactAddress = ContactAddress;
	}
	public String getContactGender() {
		return ContactGender;
	}
	public void setContactGender(String ContactGender) {
		this.ContactGender = ContactGender;
	}
	public String getContactOrg() {
		return ContactOrg;
	}
	public void setContactOrg(String ContactOrg) {
		this.ContactOrg = ContactOrg;
	}
	public String getGeneralPractitioner() {
		return GeneralPractitioner;
	}
	public void setGeneralPractitioner(String GeneralPractitioner) {
		this.GeneralPractitioner = GeneralPractitioner;
	}
	public String getLink() {
		return Link;
	}
	public void setLink(String Link) {
		this.Link = Link;
	}
	public String getObservationID() {
		return ObservationID;
	}
	public void setObservationID(String ObservationID) {
		this.ObservationID = ObservationID;
	}
	public String getObservationStatus() {
		return ObservationStatus;
	}
	public void setObservationStatus(String ObservationStatus) {
		this.ObservationStatus = ObservationStatus;
	}
	public String getObservationCode() {
		return ObservationCode;
	}
	public void setObservationCode(String ObservationCode) {
		this.ObservationCode = ObservationCode;
	}
	public String getObservationSubject() {
		return ObservationSubject;
	}
	public void setObservationSubject(String ObservationSubject) {
		this.ObservationSubject = ObservationSubject;
	}
	public String getObservationEffective() {
		return ObservationEffective;
	}
	public void setObservationEffective(String ObservationEffective) {
		this.ObservationEffective = ObservationEffective.substring(0, 4) +"-"+ ObservationEffective.substring(4, 6) 
									+"-"+ ObservationEffective.substring(6, 8) +" "+ ObservationEffective.substring(8, 10) 
				 					+":"+ ObservationEffective.substring(10, 12) +":"+ ObservationEffective.substring(12, 14);
	}
	public String getObservationIssued() {
		return ObservationIssued;
	}
	public void setObservationIssued(String ObservationIssued) {
		this.ObservationIssued = ObservationIssued.substring(0, 4) +"-"+ ObservationIssued.substring(4, 6) 
								+"-"+ ObservationIssued.substring(6, 8)+" "+ ObservationIssued.substring(8, 10) 
			 					+":"+ ObservationIssued.substring(10, 12) +":"+ ObservationIssued.substring(12, 14);
	}
	public String getObservationPerformer() {
		return ObservationPerformer;
	}
	public void setObservationPerformer(String ObservationPerformer) {
		this.ObservationPerformer = ObservationPerformer;
	}
	public String getObservationValue() {
		return ObservationValue;
	}
	public void setObservationValue(String ObservationValue) {
		this.ObservationValue = ObservationValue;
	}
	public String getObservationInterpretation() {
		return ObservationInterpretation;
	}
	public void setObservationInterpretation(String ObservationInterpretation) {
		this.ObservationInterpretation = ObservationInterpretation;
	}
	public String getObservationComment() {
		return ObservationComment;
	}
	public void setObservationComment(String ObservationComment) {
		this.ObservationComment = ObservationComment;
	}
	public String getObservationBodySite() {
		return ObservationBodySite;
	}
	public void setObservationBodySite(String ObservationBodySite) {
		this.ObservationBodySite = ObservationBodySite;
	}
	public String getObservationMethod() {
		return ObservationMethod;
	}
	public void setObservationMethod(String ObservationMethod) {
		this.ObservationMethod = ObservationMethod;
	}
	public String getObservationDevice() {
		return ObservationDevice;
	}
	public void setObservationDevice(String ObservationDevice) {
		this.ObservationDevice = ObservationDevice;
	}
	public String getReferenceRangeLow() {
		return ReferenceRangeLow;
	}
	public void setReferenceRangeLow(String ReferenceRangeLow) {
		this.ReferenceRangeLow = ReferenceRangeLow;
	}
	public String getReferenceRangeHigh() {
		return ReferenceRangeHigh;
	}
	public void setReferenceRangeHigh(String ReferenceRangeHigh) {
		this.ReferenceRangeHigh = ReferenceRangeHigh;
	}
	public String getReferenceRangeType() {
		return ReferenceRangeType;
	}
	public void setReferenceRangeType(String ReferenceRangeType) {
		this.ReferenceRangeType = ReferenceRangeType;
	}
	public String getReferenceRangeAppliesTo() {
		return ReferenceRangeAppliesTo;
	}
	public void setReferenceRangeAppliesTo(String ReferenceRangeAppliesTo) {
		this.ReferenceRangeAppliesTo = ReferenceRangeAppliesTo;
	}
	public String getReferenceRangeText() {
		return ReferenceRangeText;
	}
	public void setReferenceRangeText(String ReferenceRangeText) {
		this.ReferenceRangeText = ReferenceRangeText;
	}
}
