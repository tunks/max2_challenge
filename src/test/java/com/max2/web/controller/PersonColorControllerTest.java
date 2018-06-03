package com.max2.web.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import com.max2.parser.MockData;
import com.max2.starter.Max2Application;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(properties= {"spring.jpa.hibernate.ddl-auto=create-drop"}, classes = Max2Application.class)
@WebAppConfiguration
public class PersonColorControllerTest {
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	private String url = MockData.PERSON_COLOR_ENDPOINT_URL;

	
	@Before
	public void setUp() throws Exception {
		mockMvc = webAppContextSetup(context).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testsavePersonColorEntries() throws Exception {
		mockMvc.perform(post(url)
			   .contentType(MediaType.TEXT_PLAIN_VALUE)
			   .content(String.join(System.lineSeparator(),MockData.INVALID_DATA)))
		       .andExpect(status().is2xxSuccessful())
		       .andExpect(jsonPath("$.numberOfErrors", is(MockData.INVALID_DATA.size())));
	}
	
	@Test
	public void testGetAllPersonColorCount() throws Exception {
		mockMvc.perform(get(url)
			   .contentType(MediaType.APPLICATION_JSON))
		       .andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testGetAllPersonColorCountWithNamesIncluded() throws Exception {
		mockMvc.perform(get(url)
			   .param("names", "true")
			   .contentType(MediaType.APPLICATION_JSON))
		       .andExpect(status().is2xxSuccessful())
		       .andExpect(x->{
		    	       System.out.println("response "+ x.getResponse().getContentAsString());    
		       });
	}

	@Test
	public void testUpload() throws Exception {
		String data =  String.join(System.lineSeparator(),MockData.VALID_DATA);
        MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", data.getBytes());
        String uploadUrl =  url.concat("/upload");
        mockMvc.perform(multipart(uploadUrl)
               .file(file))
               .andExpect(status().is2xxSuccessful())
		       .andExpect(jsonPath("$.numberOfSucccess", is(MockData.VALID_DATA.size())));

	}
}
