package tr.com.jowl.repository;

import tr.com.jowl.model.CourseVideos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The TaskRepository class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
public interface CourseVideosRepository extends CrudRepository<CourseVideos, Integer> {
}