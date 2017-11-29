package com.nttdata.agni.microservices;



import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * Client controller, fetches Account info from the microservice via
 * {@link WebMappingService}.
 * 
 * @author 
 */
@Controller
public class WebMappingController {

	@Autowired
	protected WebMappingService mappingService;

	protected Logger logger = Logger.getLogger(WebMappingController.class
			.getName());

	public WebMappingController(WebMappingService mappingService) {
		this.mappingService = mappingService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("mapname", "searchText");
	}

	@RequestMapping("/mapping")
	public String goHome() {
		return "index";
	}

	

	@RequestMapping("/mapping/{text}")
	public String mapnameSearch(Model model, @PathVariable("text") String name) {
		logger.info("web-service bymapname() invoked: " + name);

		List<MappingList> mapping = mappingService.byMapnameContains(name);
		logger.info("web-service bymapname() found: " + mapping);
		model.addAttribute("search", name);
		if (mapping != null)
			model.addAttribute("mappings", mapping);
		return "mappings";
	}

	@RequestMapping(value = "/mapping/search", method = RequestMethod.GET)
	public String searchForm(Model model) {
		model.addAttribute("searchCriteria", new SearchCriteria());
		return "mappingSearch";
	}

	@RequestMapping(value = "/mapping/dosearch")
	public String doSearch(Model model, SearchCriteria criteria,
			BindingResult result) {
		logger.info("web-service search() invoked: " + criteria);

		criteria.validate(result);

		if (result.hasErrors())
			return "mappingSearch";

		String mapname = criteria.getMapname();
		if (StringUtils.hasText(mapname)) {
			return mapnameSearch(model, mapname);
		} else {
			String searchText = criteria.getSearchText();
			return mapnameSearch(model, searchText);
		}
	}

	
}
