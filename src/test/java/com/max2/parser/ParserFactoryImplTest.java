package com.max2.parser;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.max2.model.PersonColor;
import com.max2.parser.ParserFactory.FormatterType;
import com.max2.parser.formatter.Formatter;

public class ParserFactoryImplTest {
	private ParserFactory  parserFactory;
	@Before
	public void setUp() throws Exception {
		parserFactory = new ParserFactoryImpl(new DefaultDataHandler(PersonColor.class));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFormatterInstance() {
		Formatter formatter= parserFactory.getFormatterInstance();
        Assert.assertNotNull(formatter);
        Assert.assertEquals(parserFactory.getFormatterInstance(), formatter);
	}
	
	@Test
	public void testNewFormatter() {
		Formatter formatter= parserFactory.newFormatter();
        Assert.assertNotNull(formatter);
        Assert.assertNotEquals(parserFactory.newFormatter(), formatter);
	}
	
	@Test
	public void testNewFormatterWithType() {
		Formatter formatter= parserFactory.newFormatter(FormatterType.CSV);
        Assert.assertNotNull(formatter);        
	}
}
