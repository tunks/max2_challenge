package com.max2.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.max2.starter.Max2Application;
import com.max2.support.ApiQueryOperation;
import com.max2.support.QueryProjection.VenueResults;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Max2Application.class)
@ActiveProfiles("test")
public class VenueQueryServiceTest {
	@Autowired
	@Qualifier("venueQueryService")
	private ApiQueryOperation<Map,VenueResults> apiQueryOperation;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test API query
	 */
	@Test
	public void testQuery() {
		Map<String,String> queryParams = new HashMap();
		queryParams.put("near","New York,NY");
		VenueResults results = apiQueryOperation.query(queryParams);
		Assert.assertNotNull(results);
	}

}
