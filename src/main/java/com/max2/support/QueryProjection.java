package com.max2.support;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

/**
 * Custom query projection interfaces
 * 
 *  @author ebrimatunkara
 **/
public class QueryProjection {
	//JSONArray a;
	public interface ColorCount {
		String getColor();

		Long getCount();
	}
	
	public interface ColorCountAndPersonList {
		String getColor();
		
		Long getCount();
		
		@Value("#{target.names.split(',')}")
        List<String> getNames();
	}
	
	
	public interface VenueResults{
		List<String> getPlaces();
	}
}
