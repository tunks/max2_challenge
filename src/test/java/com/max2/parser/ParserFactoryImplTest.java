package com.max2.parser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.max2.model.Person;
import com.max2.parser.BaseParserFactory.FormatterType;
import com.max2.parser.formatter.BaseFormatter;
import com.max2.parser.handle.DefaultDataHandle;

public class ParserFactoryImplTest {
	private BaseParserFactory  parserFactory;
	@Before
	public void setUp() throws Exception {
		parserFactory = new ParserFactoryImpl(new DefaultDataHandle(Person.class));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFormatterInstance() {
		BaseFormatter formatter= parserFactory.getFormatterInstance();
        Assert.assertNotNull(formatter);
        Assert.assertEquals(parserFactory.getFormatterInstance(), formatter);
	}
	
	@Test
	public void testNewFormatter() {
		BaseFormatter formatter= parserFactory.newFormatter();
        Assert.assertNotNull(formatter);
        Assert.assertNotEquals(parserFactory.newFormatter(), formatter);
	}
	
	@Test
	public void testNewFormatterWithType() {
		BaseFormatter formatter= parserFactory.newFormatter(FormatterType.CSV);
        Assert.assertNotNull(formatter);        
	}

	@Test
	public void testFormatterDataFormat() {
		BaseFormatter formatter= parserFactory.getFormatterInstance();
		Object object;
		 
		for(String d: MockData.data) {
			  object = formatter.format(d);
		 }
		
               
	}

}
