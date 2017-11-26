package com.nttdata.agni.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mapstore")
public class MapStore {
	
	
	 @Id
	 @GeneratedValue()
	 private long id;
	    
	 @Column()
	 String mapname;
	 
	 @Column()
	 String mapId;
	 
	 @Column()
	 String owner;
	 
	 @Column()
	 String direction;
	 
	 @Column()
	 String client;
	 
	 @Column()
	 String status;
	 
	 @Column()
	 String type;
	 
	 @Column()
	 String createdDate;
	  
	 @Column()
	 String comments;
	 
	 
}
