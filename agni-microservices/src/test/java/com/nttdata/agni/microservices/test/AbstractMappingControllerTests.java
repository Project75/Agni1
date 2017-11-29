package com.nttdata.agni.microservices.test;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import com.nttdata.agni.microservices.MappingList;
import com.nttdata.agni.microservices.MappingController;
import com.nttdata.agni.microservices.MappingListRepository;





public  class AbstractMappingControllerTests {

	protected static final String MAPPING_1 = "default";


	@Autowired
	MappingController mappingController;

	@Test
	public void validMappings() {
		Logger.getGlobal().info("Start validMappings test");
		List<MappingList> mappings = mappingController.byOwner("default");
		Logger.getGlobal().info("In validMappings test");

		Assert.assertNotNull(mappings);
		Assert.assertEquals(52, mappings.size());

		MappingList mappingList = mappings.get(0);
		Assert.assertEquals(MAPPING_1, mappingList.getMapname());
		//Assert.assertEquals(MAPPING_1_NAME, account.getOwner());
		Logger.getGlobal().info("End validMappings test");
	}

	
	
}
