package tr.com.jowl.service.impl;

import tr.com.jowl.model.Task;
import tr.com.jowl.model.User;
import tr.com.jowl.model.registered_courses;
import tr.com.jowl.repository.RegisteredRepository;
import tr.com.jowl.repository.UserRepository;
import tr.com.jowl.service.RegisterService;
import tr.com.jowl.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisteredRepository regRepository;

	@Override
	public registered_courses save(registered_courses reg) {
		return regRepository.save(reg);
	}
	

	@Override
	public Boolean delete(int id) {
		 if (regRepository.existsById(id)) {
	            regRepository.deleteById(id);
	            return true;
	        }
	        return false;
	}

	@Override
	public registered_courses update(registered_courses reg) {
		return regRepository.save(reg);
	}

	@Override
	public registered_courses findById(int id) {
		return regRepository.findById(id).get();
	}

	@Override
	public List<registered_courses> findAll() {
		return (List<registered_courses>) regRepository.findAll();
	}


	

   
}

