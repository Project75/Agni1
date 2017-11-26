package com.nttdata.agni.domain;

import javax.persistence.*;

/*
 * a simple domain entity doubling as a DTO
 */
@Entity
@Table(name = "mappingitem")
public class MappingList {

    @Id
    @GeneratedValue()
    private long id;
    
    @Column()
    String mapname;
    
    public String getMapname() {
		return mapname;
	}

	public void setMapname(String mapname) {
		this.mapname = mapname;
	}

	@Column()
    String hl7;
    
    @Column()
    String fhir;

    Boolean IsFHIRRequired;
    Boolean IsHL7Required;

    public MappingList() {
    }

    public MappingList( String fhir,String hl7) {
        this.hl7 = hl7;
        this.fhir = fhir;
        this.mapname = "default";
                
    }
    public MappingList( String fhir,String hl7,String mapname) {
        this.hl7 = hl7;
        this.fhir = fhir;
        this.mapname = mapname;
                
    }


 

    public String getHL7() {
        return hl7;
    }

    public void setHL7(String hl7) {
        this.hl7 = hl7;
    }
    
    public String getFHIR() {
        return fhir;
    }

    public void setFHIR(String fhir) {
        this.fhir = fhir;
    }

    @Override
    public String toString() {
        return "MappingList {" +
                "mapname=" + mapname +
                ", hl7='" + hl7 + '\'' +
                ", fhir='" + fhir + '\'' +

                '}';
    }
}



