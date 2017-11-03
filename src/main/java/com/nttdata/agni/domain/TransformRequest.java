package com.nttdata.agni.domain;


public class TransformRequest {

    private String mapname;


    private String value;


    private String comments;

    public TransformRequest() {
    }

    public TransformRequest(String mapname, String value) {
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}

