package tr.com.jowl.model;

import org.springframework.format.annotation.DateTimeFormat;
import tr.com.jowl.utils.TaskCategories;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The Task Entity class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Entity()
@Table(name = "CourseVideos", schema = "tododb")
public class CourseVideos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "course_id")
    private int CourseId;

    @Column(name = "course_name")
    private String CourseName;

    @Column(name = "Instructor_name")
    private String instructor;

    @Column(name = "YoutubeVid_ID")
    private String youID;
    
    @Column(name = "VideoDescription")
    private String youDes;
    
    


    public String getYouDes() {
		return youDes;
	}

	public void setYouDes(String youDes) {
		this.youDes = youDes;
	}

	public CourseVideos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
		return CourseId;
	}

	public void setCourseId(int courseId) {
		CourseId = courseId;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getYouID() {
		return youID;
	}

	public void setYouID(String youID) {
		this.youID = youID;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseVideos cv = (CourseVideos) o;
        return id == cv.id &&
                CourseId == cv.CourseId &&
                Objects.equals(CourseName, cv.CourseName) &&
                Objects.equals(instructor, cv.instructor) &&
                youID == cv.youID;
                
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, CourseId, CourseName, instructor, youID);
    }
}

	