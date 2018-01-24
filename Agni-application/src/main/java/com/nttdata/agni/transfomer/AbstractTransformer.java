/**
 * 
 */
package com.nttdata.agni.transfomer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nttdata.agni.domain.TransformRequest;

/**
 * Copyright NTT Data
 * core
 * @author Harendra Pandey
 *
 */
@Service
public class AbstractTransformer {
	
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
	public  String transform(TransformRequest transformRequest) {
		return null;
	}
}
