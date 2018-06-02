package com.max2.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.max2.support.ColorQueryOperation;
import com.max2.support.QueryProjection.ColorCount;
import com.max2.support.QueryProjection.ColorCountAndPersonList;
import com.max2.web.Max2Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Max2Application.class)
public class PersonColorServiceTest {
	
	@Autowired
	private ColorQueryOperation personColorService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllColorCount() {
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
		//TODO
	}
	

	@Test
	public void testDelete() {
		//TODO
	}
}
