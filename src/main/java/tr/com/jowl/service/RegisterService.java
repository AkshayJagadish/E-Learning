package tr.com.jowl.service;

import org.springframework.data.jpa.repository.Query;


import tr.com.jowl.model.registered_courses;

import java.util.List;

public interface RegisterService {
	
		registered_courses save(registered_courses reg);

	    Boolean delete(int id);

	    registered_courses update(registered_courses reg);

	    registered_courses findById(int id);
	    
	   // registered_courses findByUserId(int userid);

	    List<registered_courses> findAll();

	  

}
