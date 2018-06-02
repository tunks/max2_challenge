package com.max2.support;

import java.util.List;
import com.max2.support.QueryProjection.ColorCount;
import com.max2.support.QueryProjection.ColorCountAndPersonList;

/**
 * Base Color query operation 
 *   
 *   @author ebrimatunkara
 */
public interface ColorQueryOperation {
	 /**
	  * Get all color counts
	  * 
	  *  @return List of ColorCount
	  **/
	 public List<ColorCount> getAllColorCount();
	 /**
	  * Get all color counts and persons list
	  * 
	  *  @return List of ColorCountAndPersonList
	  **/
	 public List<ColorCountAndPersonList> getAllColorCountAndPersons() ;
}
