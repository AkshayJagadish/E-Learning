package tr.com.jowl.service.impl;

import tr.com.jowl.model.Instructor;
import tr.com.jowl.repository.InstructorRepository;
import tr.com.jowl.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * The UserServiceImpl class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

   
    
    @Autowired
    private InstructorRepository instructorRepository;

	@Override
	public Instructor save(Instructor inst) {
		return instructorRepository.save(inst);
	}

	@Override
	public Instructor update(Instructor inst) {
		return instructorRepository.save(inst);
		
	}

	@Override
	public Instructor findById(int id) {
		return instructorRepository.findById(id).get();
	}

	@Override
	public Instructor findByUserName(String instructor_name) {
		return instructorRepository.findByUsername(instructor_name);
	}

	@Override
	public Boolean delete(int id) {
		  if (instructorRepository.existsById(id)) {
			  instructorRepository.deleteById(id);
	            return true;
	        }
	        return false;
	}

	@Override
	public Collection<Instructor> findAll() {
		Iterable<Instructor> itr = instructorRepository.findAll();
        return (Collection<Instructor>) itr;
	}
}
