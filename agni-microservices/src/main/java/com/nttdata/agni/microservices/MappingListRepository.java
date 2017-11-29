package com.nttdata.agni.microservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;




/**
 * Repository for Mapping data implemented using Spring Data JPA.
 * 
 * @author 
 */
public interface MappingListRepository extends JpaRepository<MappingList, Long> {
    List<MappingList> findByMapname(String mapname);

	void deleteByMapname(String name);

	
	
	/*@Query("SELECT count(*) from mappinglist")
	public int countMappings();*/

	

}
