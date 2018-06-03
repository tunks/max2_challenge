package com.max2.support;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;

/**
 * Custom query projection interfaces
 * 
 *  @author ebrimatunkara
 **/
public class QueryProjection {
	/**
	 * Color count projection contains unique colors and counts 
	 */
	public interface ColorCount {
		String getColor();

		Long getCount();
	}
	/**
	 * Color count projection contains unique colors , counts  and  list of names of persons
	 * 
	 */
	public interface ColorCountAndPersonList {
		String getColor();
		
		Long getCount();
		
		@Value("#{target.names.split(',')}")
        List<String> getNames();
	}
	
	/**
	 * Venue results projection contains the list of names of places from the FourSquare api data
	 * 
	 */
	public interface VenueResults{
		List<String> getPlaces();
	}
}
