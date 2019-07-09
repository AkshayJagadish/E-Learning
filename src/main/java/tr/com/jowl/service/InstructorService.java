package tr.com.jowl.service;

import tr.com.jowl.model.Instructor;

import java.util.Collection;

/**
 * The UserService interface
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
public interface InstructorService {

	Instructor save(Instructor inst);

    Boolean delete(int id);

    Instructor update(Instructor inst);

    Instructor findById(int id);

    Instructor findByUserName(String instructor_name);


    Collection<Instructor> findAll();
}
