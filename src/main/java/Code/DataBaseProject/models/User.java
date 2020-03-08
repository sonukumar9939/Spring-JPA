package Code.DataBaseProject.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "users")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@NotBlank(message = "Username Cannot be empty")
	private String username;

	@NotBlank
	private String password;

	@Fetch(FetchMode.SELECT)
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true, mappedBy = "user")
	@NotNull
	private User_Details userDetails;

	public User() {

	}

	

	public User(int id, @NotBlank String username, @NotBlank String password, @NotNull User_Details userDetails) {
		super();
		Id = id;
		this.username = username;
		this.password = password;
		this.userDetails = userDetails;
	}



	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User_Details getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(User_Details userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", username=" + username + ", password=" + password + ", userDetails=" + userDetails
				+ "]";
	}

}