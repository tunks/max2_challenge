package com.max2.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.max2.model.PersonColor;
import com.max2.support.QueryProjection.*;

/**
 * PersonColor repository to access and modify person entity on the db (data access object)
 * 
 * @author ebrimatunkara
 **/
public interface PersonColorRepository extends JpaRepository<PersonColor, Long> {
	/**
	 * Query operation to group unique by colors and fetch color count  
	 * 
	 * @return List of ColorCountAndPerson
	 **/
	@Query(value = "SELECT color, COUNT(color) as count "
			     + "FROM person_color GROUP BY color ORDER BY count DESC", nativeQuery = true)
	List<ColorCount> getColorCount();
	
	/**
	 * Query operation to group unique by colors and fetch color count and person first_name and last_name entry list 
	 * 
	 * @return List of ColorCountAndPerson
	 **/
	@Query(value = "SELECT color, COUNT(color) as count," + 
			" CONCAT(GROUP_CONCAT(CONCAT(first_name,' ',last_name) SEPARATOR ',') ) names " + 
			"FROM person_color GROUP BY color ORDER BY count DESC", nativeQuery = true)
	List<ColorCountAndPersonList> getColorCountAndPersonsList();

}
