package Code.DataBaseProject.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user_details")
@Data
@ToString
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class User_Details 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String firstName;

	private String lastName;

	private String emailId;

	private Integer mobileNo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "UserId",nullable = true,referencedColumnName = "id")
	@JsonIgnore
	private User user;


}