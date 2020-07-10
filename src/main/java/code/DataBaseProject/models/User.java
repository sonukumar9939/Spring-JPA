package code.DataBaseProject.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({
		@NamedQuery(name = "User.findUsersByNameAndCreator", query = "Select u FROM User u  where u.username= ?1 And u.createdBy=?2"),
		@NamedQuery(name = "User.findUsersByCreatedDate", query = "Select u FROM User u  where u.createdOn= ?1")
	})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@NotBlank(message = "Username Cannot be empty")
	private String username;

	@NotBlank
	private String password;

	@CreationTimestamp
	@Column(name = "created_on", nullable = true, updatable = true)
	private LocalDateTime createdOn;

	@UpdateTimestamp
	@Column(name = "last_modified_on", nullable = true, updatable = true)
	private LocalDateTime lastModifiedOn;

	@CreatedBy
	@Column(name = "created_by", nullable = true, updatable = true)
	private String createdBy;

	@Fetch(FetchMode.SELECT)
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
	@NotNull
	private User_Details userDetails = new User_Details();

	@Override
	public String toString() {
		return "User [Id=" + Id + ", username=" + username + ", password=" + password + ", createdOn=" + createdOn
				+ ", lastModifiedOn=" + lastModifiedOn + ", createdBy=" + createdBy + ", userDetails=" + userDetails
				+ "]";
	}

}