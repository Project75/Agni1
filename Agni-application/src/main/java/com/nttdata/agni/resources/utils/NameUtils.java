package com.nttdata.agni.resources.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Period;

public class NameUtils extends TypeUtils{
	
	List<HumanName> names =  new ArrayList<HumanName>();
	
	String[] keys = {"name","use","text","family","given",
			"prefix","suffix","period.start","period.end"};
	String TYPE=".name";
	
	public static List<HumanName> getNames(TransformMap map, String resourceName){
		NameUtils NameUtils = new NameUtils();
		return NameUtils.buildDataType(map, resourceName.toLowerCase());
	}
	
	public List<HumanName> buildDataType(TransformMap map, String resourceName){
		HumanName name = null;
		Map<String, String> kmap = createKeysMap(resourceName, keys);
		int size= map.getCount(kmap.get("family"));

		for(int i=0;i<size;i++){
			name = new HumanName();
			if (map.get(kmap.get("given"),i).equalsIgnoreCase("OFFICIAL"))				
				name.setUse(HumanName.NameUse.OFFICIAL);
			else if (map.get(kmap.get("given"),i).equalsIgnoreCase("USUAL"))				
				name.setUse(HumanName.NameUse.USUAL);
			else if (i==0)
				name.setUse(HumanName.NameUse.USUAL);
			else if (i==1)
				name.setUse(HumanName.NameUse.OFFICIAL);
			//System.out.println(i+"-"+map.get(kmap.get("family"),i)+"-"+map.get(kmap.get("given"),i));
			name.setFamily(map.get(kmap.get("family"),i));
			name.addGiven(map.get(kmap.get("given"),i));
			name.addSuffix(map.get(kmap.get("suffix"),i));
			name.addPrefix(map.get(kmap.get("prefix"),i));			
			name.setText(map.get(kmap.get("text"),i));
			
			Period pidPeriod = buildPeriod(map, kmap, i );
		
			Optional<Period> pidPeriodOpt = Optional.of(pidPeriod);
			if (pidPeriodOpt.isPresent())
			name.setPeriod(pidPeriodOpt.get());
					
			
			//add to list of names
			names.add(name);
		}
		
		
		
		return names;
		
	}
	
	
}
