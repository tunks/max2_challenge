package com.max2.support;


/***
 * Base write operation
 */
public interface WriteOperation<T> {
	public T save(T object);    
	public void delete(T object);
}
