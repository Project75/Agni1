package com.nttdata.agni.microservices;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





/**
 * A RESTFul controller for accessing mapping information.
 * 
 * @author Paul Chapman
 */
@RestController
public class MappingController {

	protected Logger logger = Logger.getLogger(MappingController.class
			.getName());
	protected MappingListRepository mappingRepository;

	/**
	 * Create an instance plugging in the respository of Mappings.
	 * 
	 * @param mappingRepository
	 *            An mapping repository implementation.
	 */
	@Autowired
	public MappingController(MappingListRepository mappingRepository) {
		this.mappingRepository = mappingRepository;

		logger.info("MappingRepository says system has "
				+ mappingRepository.count() + " mappings");
	}
	

	/**
	 * Fetch mappings with the specified name. A partial case-insensitive match
	 * is supported. So <code>http://.../mappings/owner/a</code> will find any
	 * mappings with upper or lower case 'a' in their name.
	 * 
	 * @param partialName
	 * @return A non-null, non-empty set of mappings.
	 * @throws MappingNotFoundException
	 *             If there are no matches at all.
	 */
	@RequestMapping("/mapping/{name}")
	public List<MappingList> byOwner(@PathVariable("name") String partialName) {
		logger.info("mappings-service byOwner() invoked: "
				+ mappingRepository.getClass().getName() + " for "
				+ partialName);

		List<MappingList> mappings = mappingRepository.findByMapname(partialName);
				
		logger.info("mappings-service byOwner() found: " + mappings);

		if (mappings == null || mappings.size() == 0)
			throw new ResourceNotFoundException(partialName);
		else {
			return mappings;
		}
	}
	
	@RequestMapping("/mapping")
	public List<MappingList> getAll() {
		logger.info("mappings-service getAll() invoked: "
				+ mappingRepository.getClass().getName() + " for All"
				);

		List<MappingList> mappings = mappingRepository.findAll();
				
		logger.info("mappings-service byOwner() found: " + mappings);

		if (mappings == null || mappings.size() == 0)
			throw new ResourceNotFoundException("EMPTY");
		else {
			return mappings;
		}
	}
}
