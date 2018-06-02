package com.max2.web.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.max2.parser.MockData;
import com.max2.web.Max2Application;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Max2Application.class)
@WebAppConfiguration
public class PersonColorControllerTest {
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;

	private String url = "/max2/api/v1/persons/colors";

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
		       .andExpect(x->{
	    	         System.out.println("Parser response: "+ x.getResponse().getContentAsString());
		       });
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
        MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", MockData.VALID_DATA.get(0).getBytes());
        String uploadUrl =  url.concat("/upload");
        mockMvc.perform(multipart(uploadUrl)
               .file(file))
               .andExpect(status().is2xxSuccessful());
	}
}
