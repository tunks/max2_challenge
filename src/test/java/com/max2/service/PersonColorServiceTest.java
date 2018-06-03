package com.max2.service;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.max2.model.PersonColor;
import com.max2.parser.MockData;
import com.max2.parser.ParserResponse;
import com.max2.parser.reader.DataReaderException;
import com.max2.starter.Max2Application;
import com.max2.support.ColorQueryOperation;
import com.max2.support.DataReaderOperation;
import com.max2.support.QueryProjection.ColorCount;
import com.max2.support.QueryProjection.ColorCountAndPersonList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Max2Application.class)
@ActiveProfiles("test")
public class PersonColorServiceTest {
	
	@Autowired
	private PersonColorService personColorService;
	
	private PersonColor personColor;
	
	@Before
	public void setUp() throws Exception {
		personColor = new PersonColor();
		personColor.setFirstName("Ebrima");
		personColor.setLastName("Tunkara");
	}

	@After
	public void tearDown() throws Exception {
		if(personColor.getId() !=null) {
		   personColorService.delete(personColor);	
		}
	}

	@Test
	public void testGetAllColorCount() throws DataReaderException, IOException {
		List<ColorCount> result = personColorService.getAllColorCount();
		Assert.assertNotNull(result);
		
	}
	
	
	@Test
	public void testGetAllColorCountAndPersons() {
		List<ColorCountAndPersonList> result = personColorService.getAllColorCountAndPersons();
		Assert.assertNotNull(result);
	}

	@Test
	public void testSave() {
		PersonColor result = personColorService.save(personColor);
		Assert.assertNotNull(result);
	}
}
