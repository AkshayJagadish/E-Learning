package tr.com.jowl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.jowl.model.profilepic;
import tr.com.jowl.model.Task;
import tr.com.jowl.repository.profilepicRepository;
import tr.com.jowl.repository.TaskRepository;
import tr.com.jowl.service.CourseVideosService;
import tr.com.jowl.service.profilepicService;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

/**
 * The TaskServiceImpl class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Service
@Transactional
public class profilepicServiceImpl implements profilepicService {


    @Autowired
    private profilepicRepository ppRepository;



	@Override
	public profilepic save(profilepic pp) {
        return ppRepository.save(pp);

	}

	@Override
	public profilepic update(profilepic pp) {
        return ppRepository.save(pp);

	}

	@Override
	public profilepic findById(int id) {
        return ppRepository.findById(id).get();

	}

	@Override
	public Boolean delete(int id) {
		if (ppRepository.existsById(id)) {
            ppRepository.deleteById(id);
            return true;
        }
        return false;
	}

	@Override
	public List<profilepic> findAll() {
        return (List<profilepic>) ppRepository.findAll();

	}

	
	
	
}
