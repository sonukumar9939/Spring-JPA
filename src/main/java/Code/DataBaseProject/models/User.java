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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
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

}