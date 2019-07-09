package tr.com.jowl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.jowl.model.CourseVideos;
import tr.com.jowl.model.Task;
import tr.com.jowl.repository.CourseVideosRepository;
import tr.com.jowl.repository.TaskRepository;
import tr.com.jowl.service.CourseVideosService;

import javax.transaction.Transactional;
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
public class CourseVideosServiceImpl implements CourseVideosService {


    @Autowired
    private CourseVideosRepository courseVideosRepository;



	@Override
	public CourseVideos save(CourseVideos vid) {
        return courseVideosRepository.save(vid);

	}

	@Override
	public CourseVideos update(CourseVideos vid) {
        return courseVideosRepository.save(vid);

	}

	@Override
	public CourseVideos findById(int id) {
        return courseVideosRepository.findById(id).get();

	}

	@Override
	public Boolean delete(int id) {
		if (courseVideosRepository.existsById(id)) {
            courseVideosRepository.deleteById(id);
            return true;
        }
        return false;
	}

	@Override
	public List<CourseVideos> findAll() {
        return (List<CourseVideos>) courseVideosRepository.findAll();

	}
}
