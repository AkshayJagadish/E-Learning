package tr.com.jowl.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * The User Entity class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Entity
@Table(name = "instructor", schema = "tododb")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "instrcutor_name")
    private String username;

  

    public Instructor() {
    }

    public Instructor(String username) {
        this.setUsername(username);
        

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor inst = (Instructor) o;
        return id == inst.id &&
                Objects.equals(username, inst.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username);
    }
}
