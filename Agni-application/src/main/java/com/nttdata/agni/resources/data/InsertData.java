package com.nttdata.agni.resources.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.nttdata.agni.domain.MappingList;

public class InsertData {



private DataSource dataSource;
private JdbcTemplate jdbcTemplate;

public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
}

public  void insert(List<MappingList> mappingListDefault ){

	String sql = "INSERT INTO mappingitem " +
		"(mapname, hl7, fhir) VALUES (?, ?, ?)";

	jdbcTemplate = new JdbcTemplate(dataSource);
	if (mappingListDefault.size() > 0) {        	
    	for (MappingList entity : mappingListDefault) {	    		
    		jdbcTemplate.update(sql, new Object[] { 
    				entity.getMapname(),entity.getHL7(),entity.getFHIR()});
    		}
        }
	}
	

}
