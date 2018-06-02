package com.max2.web.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import com.max2.web.Max2Application;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Max2Application.class)
@WebAppConfiguration
public class VenueControllerTest {
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;

	private String url = "/max2/api/v1/venues";

	@Before
	public void setUp() throws Exception {
		mockMvc = webAppContextSetup(context).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSearch() throws Exception {
		mockMvc.perform(get(url)
				             .contentType(MediaType.APPLICATION_JSON))
		                     .andExpect(status().is2xxSuccessful());
	}

}
