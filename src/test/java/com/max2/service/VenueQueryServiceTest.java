package com.max2.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.max2.support.APIQueryOperation;
import com.max2.web.Max2Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Max2Application.class)
public class VenueQueryServiceTest {
	@Autowired
	@Qualifier("venueQueryService")
	private APIQueryOperation apiQueryOperation;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQuery() {
		Map<String,String> queryParams = new HashMap();
		queryParams.put("near","New York,NY");
		apiQueryOperation.query(queryParams);
	}

}
