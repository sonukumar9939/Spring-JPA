package Code.DataBaseProject.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@NotBlank(message = "Username Cannot be empty")
	private String username;

	@NotBlank
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "created_on", nullable = true, updatable = true)
	private Date createdOn;
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "last_modified_on", nullable = true, updatable = true)
	private Date lastModifiedOn;
	
	@CreatedBy
	@Column(name = "created_by", nullable = true, updatable = true)
	private String createdBy;

	@Fetch(FetchMode.SELECT)
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true, mappedBy = "user")
	@NotNull
	private User_Details userDetails;

}