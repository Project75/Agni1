package com.nttdata.agni.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TransformRequest {

    private String mapname;


    private String value;
    String mappingStrategy="DEFAULT";

    private String comments;
    
    public TransformRequest() {};

/*    public TransformRequest(String mapname) {
    	this.mapname = mapname;
    	String msg  = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01||P|2.2\r"
                + "PID||001|199||JOHN^DOE||19751027|Female|||street 53^^PHOENIX^AZ^85013^US||(111)222-3333||N|W|||001|||||false||||||false|||||PID.35\r"
                + "NK1|0222555|NOTREAL^JAMES^R|FA|STREET^OTHER STREET^CITY^ST^55566|(222)111-3333|(888)999-0000|Father||||||ORGANIZATION||Male\r"
                +"PV1||O|5501^0113^02|U|00060292||00276^DELBARE^POL^^DR.|00276^DELBARE^POL^^DR.||1901|||N|01|||||0161782703\r"
                +"PD1|1|2|3|4|5|6|7|8|9|10\r"
                +"OBX|1|TX|3|4|5|6|7|8|9|10|FINAL||13|20170828|15|16|17|18|19|20|21|22|23|24|25|26\r"
                +"OBR|1|2|3|4|5||||9|10|11|12|13||15|16||18|19|20|21|20170828|23|24|25|26\r"
                +"NTE|1|2|3|4\r";
        this.value = msg;
    }*/

    public TransformRequest(String mapname, String value) {
        this.mapname = mapname;
        this.value = value;
        
    }
    
    public TransformRequest(String mapname, String value, String strategy) {
        this.mapname = mapname;
        this.value = value;
        this.mappingStrategy = strategy;
        
    }




}

