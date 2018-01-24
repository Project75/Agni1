package com.nttdata.agni.test;

/**
 * @author Harendra
 */


import com.nttdata.agni.dao.jpa.MappingListRepository;
import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.service.MappingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MappingServiceTest {

    
    @Mock
    MappingListRepository MappingListRepository;
    
    @InjectMocks
    MappingService MappingListService;

 
    
    private String mapName="test1";
    
    List<MappingList> mapping;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mapName = "test1";
    }

	@Test
	public void testGetAllMappingList(){
		List<MappingList> mapping = new ArrayList<MappingList>();
		mapping.add(new MappingList("patient.identifier","PID-3-1"));
		mapping.add(new MappingList("patient.name.family","PID-5-1"));
		mapping.add(new MappingList("patient.name.given","PID-5-2"));
//mock method
		when(MappingListRepository.findByMapname(mapName)).thenReturn(mapping);
	//	
		List<MappingList> result = MappingListService.getMapping(mapName);
		System.out.println("size="+result.size());
		assertEquals(3, result.size());
	}
	
	
	
	

	@Test
	public void saveMappingList(){
		List<MappingList> mapping = new ArrayList<MappingList>();
		mapping.add(new MappingList("patient.name.given","PID-5-2"));
		//mock
		when(MappingListRepository.save(mapping)).thenReturn(mapping);
		//actual
		List<MappingList> result = MappingListService.updateMapping(mapping);
		
		assertEquals("patient.name.given", result.get(0).getFhir());
		assertEquals("PID-5-2", result.get(0).getHl7());
	}
	
	@Test
	public void removeMappingList(){
		List<MappingList> mapping = new ArrayList<MappingList>();
		mapping.add(new MappingList("patient.name.given","PID-5-2"));
		MappingListService.deleteMapping(mapping);
        verify(MappingListRepository, times(1)).delete(mapping);
	}
	
}
