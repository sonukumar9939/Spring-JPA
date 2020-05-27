package code.DataBaseProject.models;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "students")
@Data
@ToString
@NoArgsConstructor(force = true)
@EqualsAndHashCode( exclude={"subjects"})
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "roll_no")
	private String rollNo;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "craeted_on", nullable = true, updatable = true)
	private Date createdOn;

	@CreatedBy
	@Column(name = "created_by", nullable = true, updatable = true)
	private String createdBy;

	@LastModifiedDate
	@Column(name = "last_modified_on", nullable = true, updatable = true)
	private String lastModifiedOn;
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval= true,mappedBy= "student")
	private Set<Subjects> subjects = new LinkedHashSet<Subjects>();

}
