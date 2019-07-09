package tr.com.jowl.service;

import org.springframework.data.jpa.repository.Query;
import tr.com.jowl.model.CourseVideos;

import java.util.List;

/**
 * The TaskService interface
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
public interface CourseVideosService {

	CourseVideos save(CourseVideos task);

    Boolean delete(int id);

    CourseVideos update(CourseVideos task);

    CourseVideos findById(int id);

    List<CourseVideos> findAll();




}

