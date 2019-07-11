package tr.com.jowl.model;

import javax.persistence.*;

import com.mysql.jdbc.Blob;

import java.util.Objects;

@Entity
@Table(name = "profilepic", schema = "tododb")
public class profilepic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "userId")
    private int userId;
    

	@Column(name = "ppstr")
    private String ppstr;
    
    @Column(name = "ppfile")
    @Lob
    private  byte[] ppfile;

	public String getPpstr() {
		return ppstr;
	}




	public void setPpstr(String ppstr) {
		this.ppstr = ppstr;
	}




	public void setPpfile(byte[] ppfile) {
		this.ppfile = ppfile;
	}


	 public byte[] getPpfile() {
			return ppfile;
		}




	public profilepic() {
    }


                   

    public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getUserId() {
		return userId;
	}




	public void setUserId(int userId) {
		this.userId = userId;
	}




	




	/*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                role == user.role &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(password_2, user.password_2) &&
                Objects.equals(email, user.email);
        		
    }*/

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, ppfile);
    }
}

