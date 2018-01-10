package com.nttdata.agni.resources.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hl7.fhir.dstu3.model.Address;
import org.hl7.fhir.dstu3.model.Address.AddressType;
import org.hl7.fhir.dstu3.model.Address.AddressUse;
import org.hl7.fhir.dstu3.model.Period;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.exceptions.FHIRException;


public class AddressUtils extends TypeUtils{
	
	List<Address> dTypes =  new ArrayList<Address>();
	
	String[] keys = {"address","use","type","text","line","city","district",
			"state","postalcode","country","period.start","period.end"};
	
	
	public static List<Address> getAddress(TransformMap map, String resourceName){
		AddressUtils dTypeUtils = new AddressUtils();
		return dTypeUtils.buildDataType(map, resourceName);
	}
	
	public List<Address> buildDataType(TransformMap map, String resourcedType){
		Address dType = null;
		Map<String, String> kmap = createKeysMap(resourcedType, keys);
		int size= map.getCount(kmap.get("city"));

		for(int i=0;i<size;i++){
			dType = new Address();
			
			String use = Optional.ofNullable(map.get(kmap.get("use"),i)).orElse("default");
			
			switch (use.toUpperCase()){
			case "HOME":
				dType.setUse(AddressUse.HOME);
			case "WORK":
				dType.setUse(AddressUse.WORK);
			default:
				dType.setUse(AddressUse.HOME);
			}
			String type = Optional.ofNullable(map.get(kmap.get("type"),i)).orElse("default");
			switch (type.toUpperCase()){
			case "PHYSICAL":
				dType.setType(AddressType.PHYSICAL);
			case "POSTAL":
				dType.setType(AddressType.POSTAL);
			default:
				//dType.setType(AddressType.PHYSICAL);
			}
			//System.out.println(i+"-"+map.get(kmap.get("family"),i)+"-"+map.get(kmap.get("given"),i));
			
			String line_address = map.get(kmap.get("line"),i);
			if (line_address !=null ){
				List<StringType> theLine = new ArrayList<StringType>();
				StringType stringType = new StringType(line_address);			
				theLine.add(stringType);
				dType.setLine(theLine);
			}
			
			dType.setCity(map.get(kmap.get("city"),i));
			dType.setState(map.get(kmap.get("state"),i));
			dType.setCountry(map.get(kmap.get("country"),i));
			dType.setDistrict(map.get(kmap.get("district"),i));
			dType.setPostalCode(map.get(kmap.get("postalcode"),i));
			dType.setText(map.get(kmap.get("text"),i));
			
			
			Period pidPeriod = buildPeriod(map, kmap, i );
		
			Optional<Period> pidPeriodOpt = Optional.of(pidPeriod);
			if (pidPeriodOpt.isPresent())
			dType.setPeriod(pidPeriodOpt.get());
					
			
			//add to list of dTypes
			dTypes.add(dType);
		}
		
		
		
		return dTypes;
		
	}

	public static Address getFirstAddress(TransformMap map, String resourceName) {
		// TODO Auto-generated method stub
		List<Address> addressList= getAddress(map, resourceName);
		if (!addressList.isEmpty())
			return addressList.get(0);
		else 
			return null;
	}
	
	
}
