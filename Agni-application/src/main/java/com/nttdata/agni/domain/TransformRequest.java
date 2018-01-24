package com.nttdata.agni.domain;

import com.nttdata.agni.resources.utils.FHIRConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
/**
 * Copyright NTT Data
 * @author Harendra 
 */
@Data
public class TransformRequest {
	
    private String mapname =  FHIRConstants.DEFAULT_MAPPING_NAME;;

    /*
     * Payload
     */
    @NonNull
    private String value;
    /*
     * values = DEFAULT, MINIMAL
     */
    String mappingStrategy=FHIRConstants.MAPPING_STRATEGY_DEFAULT;
    /*
     * values = BUNDLE, INDIVIDUAL,TEST
     */
    String creationStrategy=FHIRConstants.CREATION_STRATEGY_BUNDLE;
    
    Boolean postToServer =  false;
    
    String serverBaseUrl = FHIRConstants.LOCAL_SERVER_BASEURL;
    
    private String bundleType=FHIRConstants.BUNDLE_TYPE_MESSAGE;
    
    public TransformRequest(){}
    
    public TransformRequest(String mapname, String value) {
        this.mapname = mapname;
        this.value = value;
        
    }
    
    public TransformRequest(String mapname, String value, String mappingStrategy, String creationStrategy) {
        this.mapname = mapname;
        this.value = value;
        this.mappingStrategy = mappingStrategy;
        this.creationStrategy=creationStrategy;
        
    }
    
    public TransformRequest(String mapname, String value, String mappingStrategy, Boolean postToServer, String serverBaseUrl) {
        this.mapname = mapname;
        this.value = value;
        this.mappingStrategy = mappingStrategy;
        this.postToServer=postToServer;
        this.serverBaseUrl=serverBaseUrl;
        
    }




}

