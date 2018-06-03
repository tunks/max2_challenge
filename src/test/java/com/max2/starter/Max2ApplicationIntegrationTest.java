package com.max2.starter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.jsonpath.JsonPath;
import com.max2.model.PersonColor;
import com.max2.parser.MockData;
import com.max2.parser.ParserResponse;
import com.max2.parser.ParserResponseImpl;
import com.max2.parser.reader.MockAbstractDataReader;
import com.max2.repositories.PersonColorRepository;
import com.max2.support.DataUtil;
import com.max2.support.QueryProjection.ColorCountAndPersonList;


/**
 *  Simple integration testing 
 **/
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(properties= {},
                webEnvironment= WebEnvironment.RANDOM_PORT)
public class Max2ApplicationIntegrationTest extends MockAbstractDataReader {
	@Resource
    private PersonColorRepository personColorRepository;
	@Autowired
	private TestRestTemplate restTemplate;

	private String url = MockData.PERSON_COLOR_ENDPOINT_URL;
	 
	private Map<String,Integer> colorCountMap = new HashMap();
	private Map<String,List<String>> personColorCountMap = new HashMap();


	@Before
	public void setUp() throws Exception {
		super.setUp();
		cleanupPersonColorRecordsInTestDB();
		readFromFile();
		mapPersonColorResults();
		
	}

	@After
	public void tearDown() throws Exception {
		cleanupPersonColorRecordsInTestDB();
     }

	/**
	 * 
	 **/
	@Test
	public void test() throws Exception {
		/**
		 * Post data to rest endpoint
		 * 
		 **/
		String data = String.join(System.lineSeparator(),MockData.VALID_DATA);
		ResponseEntity<ParserResponseEntity> parserResponseEntity = restTemplate.postForEntity(url, data,ParserResponseEntity.class);
     	Assert.assertEquals(parserResponseEntity.getBody().getNumberOfSucccess(),MockData.VALID_DATA.size());
     	
        /**
         * Fetch color count and list of person names 
         * 
         **/
		Map<String,String> params = Collections.singletonMap("names", "true");
	    String requestUrl = url.concat("?names={names}");
		ResponseEntity<PersonColorCountEntity[]> responseEntity = restTemplate.getForEntity(requestUrl,PersonColorCountEntity[].class,params);
		/**
		 * Assert response entity 
		 */
		for(PersonColorCountEntity entity: responseEntity.getBody()) {
	     	Assert.assertEquals(colorCountMap.get(entity.getColor()).intValue(),entity.getCount());
	     	Assert.assertTrue(entity.getNames().containsAll(personColorCountMap.get(entity.getColor())));
	     	Assert.assertTrue(personColorCountMap.get(entity.getColor()).containsAll(entity.getNames()));
		}		
	}
	
	private void mapPersonColorResults() {
		  String color;
		  List<String> names;
		  for(PersonColor personColor: this.personResults) {
			  color = personColor.getColor();
			  colorCountMap.put(color,colorCountMap.getOrDefault(color,0)+1);
			  names = personColorCountMap.getOrDefault(color, new ArrayList<String>());
			  names.add(String.join(" ", personColor.getFirstName(),personColor.getLastName()));
			  personColorCountMap.put(color,names);
		  }
	}
	
	private void cleanupPersonColorRecordsInTestDB() {
		personColorRepository.deleteAll();
	}
	
	public static class PersonColorCountEntity{
	    private String color;
	    private long count;
	    private List<String> names;
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public long getCount() {
			return count;
		}
		public void setCount(long count) {
			this.count = count;
		}
		public List<String> getNames() {
			return names;
		}
		public void setNames(List<String> names) {
			this.names = names;
		}
		@Override
		public String toString() {
			return "PersonColorCountEntity [color=" + color + ", count=" + count + ", names=" + names + "]";
		}
		
	}
	
	public static class  ParserResponseEntity implements ParserResponse{
         private long numberOfErrors ;
         private long numberOfSucccess;

		public void setNumberOfSucccess(long numberOfSucccess) {
			this.numberOfSucccess = numberOfSucccess;
		}

		public void setNumberOfErrors(long numberOfErrors) {
			this.numberOfErrors = numberOfErrors;
		}

		@Override
		public long getNumberOfErrors() {
			return numberOfErrors;
		}

		@Override
		public long getNumberOfSucccess() {
			return numberOfSucccess;
		}

		@Override
		public String toString() {
			return "ParserResponseEntity [numberOfErrors=" + numberOfErrors + ", numberOfSuccess=" + numberOfSucccess
					+ "]";
		}
		
	}
	

}
