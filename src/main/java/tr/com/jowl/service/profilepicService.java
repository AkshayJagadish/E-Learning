package tr.com.jowl.service;

import org.springframework.data.jpa.repository.Query;
import tr.com.jowl.model.profilepic;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;


/**
 * The TaskService interface
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
public interface profilepicService {

	profilepic save(profilepic pp);

    Boolean delete(int id);

    profilepic update(profilepic pp);

    profilepic findById(int id);
    
    
    List<profilepic> findAll();




}