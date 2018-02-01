package com.nttdata.agni.test;

import com.fasterxml.jackson.databind.ObjectMapper;

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

		when(MappingListRepository.findByMapname(mapName)).thenReturn(mapping);
	
		List<MappingList> result = MappingListService.getMapping(mapName);
		printMapping(result);
		System.out.println("size="+result.size());
		assertEquals(3, result.size());
	}
	
	
	
	

	@Test
	public void saveMappingList(){
		List<MappingList> mapping = new ArrayList<MappingList>();
		MappingList item = new MappingList("patient.name.given","PID-5-2");
		mapping.add(item);
		//mock
		when(MappingListRepository.save(item)).thenReturn(item);
		//when(MappingListRepository.save(mapping)).thenReturn(mapping);
		//actual
		List<MappingList> result = MappingListService.createMapping2(mapName, mapping);
				//updateMapping(mapping);
		assertEquals(1, result.size());
		assertEquals("patient.name.given", result.get(0).getFhir());
		assertEquals("PID-5-2", result.get(0).getHl7());
	}
	
	//@Test
	public void removeMappingList(){
		List<MappingList> mapping = new ArrayList<MappingList>();
		mapping.add(new MappingList("patient.name.given","PID-5-2"));
		MappingListService.deleteMapping(mapping);
        verify(MappingListRepository, times(1)).delete(mapping);
	}
	
	@Test
	public void getRemoveMappingById(){
		//List<MappingList> mapping = new ArrayList<MappingList>();
		
		MappingList mapping = new MappingList("patient.name.given","PID-5-2");
		long id= 0;

		when(MappingListRepository.findById(id)).thenReturn(mapping);
	
		MappingList result = MappingListService.getById(id);

		assertEquals("patient.name.given", result.getFhir());
		assertEquals("PID-5-2", result.getHl7());
		System.out.println("getRemoveMappingById="+result.toString());
		
		
		MappingListService.deleteById(id);
        verify(MappingListRepository, times(1)).deleteById(id);
	}
	
	
	public static void main(String[] args){
		List<MappingList> mapping = new ArrayList<MappingList>();
		mapping.add(new MappingList("patient.name.given","PID-5-2"));
		printMapping(mapping);
	}
	static void printMapping(List<MappingList> mapping){
		for (MappingList map1:mapping){
			System.out.println(map1.toString());
		}
	}
		
	
	
}
