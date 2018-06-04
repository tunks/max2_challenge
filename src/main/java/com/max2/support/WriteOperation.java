package com.max2.support;


/***
 * Base write operation
 * 
 * @author ebrimatunkara
 */
public interface WriteOperation<T> {
	/**
	 * Write action to save object 
	 * 
	 * @param object
	 * @return saved object  
	 **/
	public T save(T object);    
	/**
	 * Write action to delete object
	 * 
	 * @param object
	 */
	public void delete(T object);
}
