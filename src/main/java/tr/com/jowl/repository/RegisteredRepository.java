package tr.com.jowl.repository;


import tr.com.jowl.model.registered_courses;
import org.springframework.data.repository.CrudRepository;

public interface RegisteredRepository extends CrudRepository<registered_courses, Integer> {

	//registered_courses findByuser_id(int user_id);
    
}
