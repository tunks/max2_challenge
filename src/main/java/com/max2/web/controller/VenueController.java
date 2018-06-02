package com.max2.web.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.max2.support.APIQueryOperation;
import com.max2.support.QueryProjection.VenueResults;

/**
 * Venue controller -- searches for venue names on external API services
 * 
 * @author ebrimatunkara
 **/
@RestController
@RequestMapping("/max2/api/v1/venues")
public class VenueController {
	@Autowired
	@Qualifier("venueQueryService")
	private APIQueryOperation<Map,VenueResults> apiQueryOperation;
	
	/**
	 *  Search venue names and default location given as NY
	 *  
	 *   @param near
	 *   @return VenueResult , results list of venue names
	 * 
	 **/
	@RequestMapping(method=RequestMethod.GET)
    public VenueResults search(@RequestParam(name="near", defaultValue="New York,NY") String near) {
    	   return apiQueryOperation.query(Collections.singletonMap("near", near));
    }
}
