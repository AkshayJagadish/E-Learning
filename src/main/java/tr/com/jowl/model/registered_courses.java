package tr.com.jowl.model;

import org.springframework.format.annotation.DateTimeFormat;
import tr.com.jowl.utils.TaskCategories;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity()
@Table(name = "registered_courses", schema = "tododb")
public class registered_courses {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
	
	 @Column(name = "user_id")
	 private int user_id;

	 @Column(name = "course_id")
	 public int course_id;
	 
	 @Column(name = "username")
	 public String username;
	 
	 @Column(name = "course_name")
	 public String course_name;
	 
	 public registered_courses() {}
	 
	 public registered_courses(int user_id, int course_id, String username, String course_name) {
	        this.setUser_id(user_id);
	        this.setCourse_id(course_id);
	        this.setUsername(username);
	        this.setCourse_name(course_name);

	    }
	 	

	    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

		@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        registered_courses reg = (registered_courses) o;
	        return id == reg.id &&
	                user_id == reg.user_id &&
	                course_id == reg.course_id &&
	                Objects.equals(username, reg.username) &&
	        		Objects.equals(course_name, reg.course_name);
	    }

	    @Override
	    public int hashCode() {

	        return Objects.hash(id, user_id, course_id, username, course_name);
	    }



}
