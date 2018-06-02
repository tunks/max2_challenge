package com.max2.repositories;

import java.util.List;
import javax.annotation.Resource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.max2.model.PersonColor;
import com.max2.support.QueryProjection.ColorCount;
import com.max2.support.QueryProjection.ColorCountAndPersonList;
import com.max2.web.Max2Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Max2Application.class)
@ActiveProfiles("dev")
public class PersonColorRepositoryTest {
	@Resource
    private PersonColorRepository personColorRepository;
	private PersonColor person;
	
	@Before
	public void setUp() throws Exception {
	   person = new PersonColor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		person.setFirstName("Dan");
		person.setLastName("Kerry");
		person.setColor("Yello");
		PersonColor result = personColorRepository.save(person);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testGetColorCount() {
		System.out.println("color count");
		List<ColorCount> dtoList = personColorRepository.getColorCount();
		Assert.assertNotNull(dtoList);
		for(ColorCount dto: dtoList) {
			System.out.println(dto.getColor() +" | "+dto.getCount());
		}	
	}
	
	@Test
	public void testGetColorCountAndPersonsList() {
		System.out.println("color count and person");
		List<ColorCountAndPersonList> dtoList = personColorRepository.getColorCountAndPersonsList();
		Assert.assertNotNull(dtoList);
		for(ColorCountAndPersonList dto: dtoList) {
			System.out.println(dto.getColor() +" | "+dto.getCount() +" | "+ dto.getNames());
		}
		
	}

}
