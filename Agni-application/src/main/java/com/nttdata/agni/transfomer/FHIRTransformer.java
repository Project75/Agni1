package com.nttdata.agni.transfomer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleType;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.*;
import ca.uhn.fhir.util.FhirTerser;
import ca.uhn.fhir.util.UrlUtil;
import ca.uhn.fhir.util.UrlUtil.UrlParts;

import org.hl7.fhir.dstu3.model.*;
import org.hl7.fhir.dstu3.model.Bundle.*;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.api.IBaseReference;
import org.hl7.fhir.instance.model.api.IBaseResource;

import com.nttdata.agni.domain.MappingList;

public class FHIRTransformer {
	private void doTransaction( final Bundle theRequest, final String theActionName) {
		BundleType transactionType = theRequest.getTypeElement().getValue();
		if (transactionType == BundleType.BATCH) {
			//return batch( theRequest);
		}

		if (transactionType == null) {
			String message = "Transactiion Bundle did not specify valid Bundle.type, assuming " + BundleType.TRANSACTION.toCode();
			//ourLog.warn(message);
			transactionType = BundleType.TRANSACTION;
		}
		if (transactionType != BundleType.TRANSACTION) {
			//throw new InvalidRequestException("Unable to process transaction where incoming Bundle.type = " + transactionType.toCode());
		}

		//ourLog.info("Beginning {} with {} resources", theActionName, theRequest.getEntry().size());

		long start = System.currentTimeMillis();
		final Date updateTime = new Date();

		//final Set<IdType> allIds = new LinkedHashSet<IdType>();
		//final Map<IdType, IdType> idSubstitutions = new HashMap<IdType, IdType>();
		//final Map<IdType, DaoMethodOutcome> idToPersistedOutcome = new HashMap<IdType, DaoMethodOutcome>();

		// Do all entries have a verb?
		for (int i = 0; i < theRequest.getEntry().size(); i++) {
			BundleEntryComponent nextReqEntry = theRequest.getEntry().get(i);
			HTTPVerb verb = nextReqEntry.getRequest().getMethodElement().getValue();
			if (verb == null) {
				//throw new Exception(getContext().getLocalizer().getMessage(BaseHapiFhirSystemDao.class, "transactionEntryHasInvalidVerb", nextReqEntry.getRequest().getMethod(), i));
			}
		}
		final Bundle response = new Bundle();
		List<BundleEntryComponent> getEntries = new ArrayList<BundleEntryComponent>();
		final IdentityHashMap<BundleEntryComponent, Integer> originalRequestOrder = new IdentityHashMap<Bundle.BundleEntryComponent, Integer>();
		for (int i = 0; i < theRequest.getEntry().size(); i++) {
			originalRequestOrder.put(theRequest.getEntry().get(i), i);
			response.addEntry();
			if (theRequest.getEntry().get(i).getRequest().getMethodElement().getValue() == HTTPVerb.GET) {
				getEntries.add(theRequest.getEntry().get(i));
			}
		}
		

	}

	private Object getContext() {
		// TODO Auto-generated method stub
		return null;
	}
	public static void getFHIRValue(IBaseResource nextResource) {
		FhirContext ctx = FhirContext.forDstu3();
		FhirTerser terser = ctx.newTerser();
		//IBaseResource nextResource = null;
		List<StringDt> allStringData = terser.getAllPopulatedChildElementsOfType(nextResource, StringDt.class);
		if (allStringData.size() > 0) {        	
	    	for (StringDt entity : allStringData) {	    		
	    		System.out.println(entity.getValueAsString());
	        }
    	}
	}
}