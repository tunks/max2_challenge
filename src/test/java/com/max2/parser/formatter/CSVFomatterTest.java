package com.max2.parser.formatter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.max2.model.PersonColor;
import com.max2.parser.DefaultDataHandler;
import com.max2.parser.MockData;
import com.max2.parser.ParserFactoryImpl;

public class CSVFomatterTest {
	private Formatter<PersonColor, String> formatter;

	@Before
	public void setUp() throws Exception {
		formatter = new ParserFactoryImpl(new DefaultDataHandler(PersonColor.class)).getFormatterInstance();
	}

	@After
	public void tearDown() throws Exception {

	}

	/**
	 * Test invalid data
	 * 
	 */
	@Test
	public void testFormatInValidData() {
		PersonColor object;
		for (String data : MockData.INVALID_DATA) {
			object = formatter.format(data);
			Assert.assertNull("Invalid data, object should be null " + data, object);
		}
	}

	/**
	 * Test valid data
	 * 
	 */
	@Test
	public void testFormatValidData() {
		PersonColor object;
		for (String data : MockData.VALID_DATA) {
			object = formatter.format(data);
			Assert.assertNotNull("Valid data, object should be not null " + data, object);
		}
	}

	/**
	 * Test combine valid and invalid data
	 * 
	 */
	@Test
	public void testFormatCombineInvalidAndValidData() {
		PersonColor object;
		List<String> combineData = new ArrayList(MockData.INVALID_DATA);
		combineData.addAll(MockData.VALID_DATA);
		List<PersonColor> objects = new ArrayList();
		for (String data : combineData) {
			object = formatter.format(data);
			if (object != null) {
				objects.add(object);
			}
		}

		Assert.assertTrue(objects.size() == MockData.VALID_DATA.size());
	}
}
