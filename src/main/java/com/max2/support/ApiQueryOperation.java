package com.max2.support;

/***
 * Base API Query operation
 * 
 * @author ebrimatunkara
 */
public interface ApiQueryOperation<Q,T> {
	  /**
	   * Performs search queries on the api with given parameters
	   * 
	   *  @param queryParams
	   *  @return T 
	   */
      T query(Q queryParams);
}
