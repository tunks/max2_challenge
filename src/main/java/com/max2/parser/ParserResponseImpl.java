package com.max2.parser;

import java.util.concurrent.atomic.AtomicLong;

/**
 *  Parser response implementation
 *  
 *   @author ebrimatunkara
 **/
public class ParserResponseImpl implements ParserResponse{
	/***
	 * Atomic counters to keep track of error(invalid) and success(valid) line counts on data formatting
	 *  
	 **/
	private  AtomicLong errors = new AtomicLong();
	private  AtomicLong success = new AtomicLong();

	public AtomicLong errors() {
		return errors;
	}

	public AtomicLong success() {
		return success;
	}

	@Override
	public Long getNumberOfErrors() {
		return errors().get();
	}

	@Override
	public Long getNumberOfSucccess() {
		return  success().get();
	}

}
