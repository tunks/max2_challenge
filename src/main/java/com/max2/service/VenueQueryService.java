package com.max2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.max2.support.ApiQueryOperation;
import com.max2.support.QueryProjection.VenueResults;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

/**
 * Venue query service -- peform external api query on Foursquare API
 * 
 * @author ebrimatunkara
 **/
@Service("venueQueryService")
public class VenueQueryService implements ApiQueryOperation<Map, VenueResults>, InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(VenueQueryService.class);

	@Value("${external.api.endpoint}")
	private String endpointUrl;

	@Value("${external.api.version}")
	private String apiVersion;

	@Value("${client_id}")
	private String clientId;

	@Value("${client_secret}")
	private String clientSecret;

	private FoursquareApi foursquareApi;
	
	/**
	 *  Performs search query on foursquare API
	 *  
	 *  @param Map query parameters
	 *  @return VenueResult 
	 **/
	@Override
	public VenueResults query(Map queryParams) {
		try {
			Result<VenuesSearchResult> searchResults = foursquareApi.venuesSearch(queryParams);
			if (searchResults.getMeta().getCode() == HttpStatus.OK.value()) {
				List<String> places = new ArrayList();
				VenueResults result = new QueryVenueResult(places);
				for (CompactVenue venue : searchResults.getResult().getVenues()) {
					places.add(venue.getName());
				}
				logger.info("searchResults: " + searchResults);
				logger.info("venus: " + result.toString());
				return result;	
			} 

		} catch (FoursquareApiException e) {
			logger.error("Foursearch api error: "+e.getMessage());
		}
		return null;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		try {
		 foursquareApi = new FoursquareApi(clientId, clientSecret, null);
		 foursquareApi.setVersion(apiVersion);		
		}
		catch(Exception e ) {
			
		}
	}
	
	private class QueryVenueResult implements VenueResults {
		List<String> places;

		public QueryVenueResult(List<String> places) {
			this.places = places;
		}

		@Override
		public List<String> getPlaces() {
			return places;
		}

		@Override
		public String toString() {
			return "QueryVenueResult [places=" + places + "]";
		}
	}

}
