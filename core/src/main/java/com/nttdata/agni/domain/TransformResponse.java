package com.nttdata.agni.domain;

public class TransformResponse {
    private String mapname;

    private String value;
   
    public TransformResponse() {
    }

    public TransformResponse(String mapname, String value) {
        this.mapname = mapname;
        this.value = value;
        
    }

	public String getMapname() {
		return mapname;
	}

	public void setMapname(String mapname) {
		this.mapname = mapname;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
