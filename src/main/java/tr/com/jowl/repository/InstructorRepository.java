package tr.com.jowl.repository;

import tr.com.jowl.model.Instructor;
import org.springframework.data.repository.CrudRepository;

/**
 * The UserRepository class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
public interface InstructorRepository extends CrudRepository<Instructor, Integer> {

    Instructor findByUsername(String instructor_name);

   
}