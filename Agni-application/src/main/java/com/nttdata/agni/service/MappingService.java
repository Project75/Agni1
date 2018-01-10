package com.nttdata.agni.service;


import com.nttdata.agni.domain.MappingList;
import com.nttdata.agni.dao.jpa.MappingListRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/*
 * Sample service to demonstrate what the API would use to get things done
 */
@Service
public class MappingService {

    private static final Logger log = LoggerFactory.getLogger(MappingService.class);



    @Autowired
    private MappingListRepository mappingListRepository; 
 
    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public MappingService() {
    }

    

    
    public void createMapping(String mapname, List<MappingList> mappingList) {
    	// mappingItem2Repository.save(mappingList);
    	for (MappingList entity : mappingList) {
    		entity.setMapname(mapname);
    		mappingListRepository.save(entity);
        }
        
    }


    public List<MappingList> getMapping(String mapname) {
        return mappingListRepository.findByMapname(mapname);
    }
    
	public List<MappingList> findByMapname(String mapname) {
		// TODO Auto-generated method stub
		return mappingListRepository.findByMapname(mapname);
	}

	public List<MappingList> updateMapping(List<MappingList> mappingList) {
        return mappingListRepository.save(mappingList);
    }

    public void deleteMapping(String name) {
        mappingListRepository.deleteByMapname(name);
    }
    
    public void deleteMapping(List<MappingList> mappingList) {
        mappingListRepository.delete(mappingList);
    }

    //http://goo.gl/7fxvVf
    public Page<MappingList> getAllMappings(Integer page, Integer size) {
        Page pageOfMappings = mappingListRepository.findAll(new PageRequest(page, size));
        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("nttdata.MappingService.getAll.largePayload");
        }
        return pageOfMappings;
    }





    
}


