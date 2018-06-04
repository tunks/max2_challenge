package com.max2.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.max2.model.PersonColor;
import com.max2.repositories.PersonColorRepository;
import com.max2.support.ColorQueryOperation;
import com.max2.support.QueryProjection.ColorCount;
import com.max2.support.QueryProjection.ColorCountAndPersonList;
import com.max2.support.WriteOperation;


/***
 * PersonColor service handles query and write operations
 * 
 * @author ebrimatunkara
 * 
 */
@Service
public class PersonColorService implements ColorQueryOperation, WriteOperation<PersonColor> {
	 @Resource
     private PersonColorRepository personRepository;
	 
	 /**
	  *  Get color counts
	  *  
	  *  @return List: List of color counts
	  */
	 public List<ColorCount> getAllColorCount() {
		 return personRepository.getColorCount();
	 }
	 
	 /**
	  *  Get color counts and list of names of persons
	  *  
	  *  @return List: List of color counts and names of persons
	  */
	 public List<ColorCountAndPersonList> getAllColorCountAndPersons() {
		 return personRepository.getColorCountAndPersonsList();
	 }

	 /***
	  *  Save PersonColor object 
	  *  
	  *  @param object
	  *  @return PersonColor
	  */
	@Override
	public PersonColor save(PersonColor object) {
		return personRepository.save(object);
	}

	 /***
	  *  Delete PersonColor object
	  *  
	  *  @param object
	  *  @return PersonColor
	  */
	@Override
	public void delete(PersonColor object) {
		personRepository.delete(object);
	}
}
