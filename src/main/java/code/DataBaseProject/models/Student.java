package code.DataBaseProject.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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
import lombok.ToString;

@Entity
@Table(name = "student")
@Data
@ToString
@NoArgsConstructor(force = true)
@EqualsAndHashCode(exclude = { "courses" })
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "Student.findStudentByName", query = "Select s from Student s where s.studentName =?1")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@Column(name = "student_name", unique = true)
	private String studentName;

	@Column(name = "roll_no")
	private String rollNo;

	@CreationTimestamp
	@Column(name = "craeted_on", nullable = true, updatable = true)
	private Date createdOn;
 
	@CreatedBy
	@Column(name = "created_by", nullable = true, updatable = true)
	private String createdBy;

	@UpdateTimestamp
	@Column(name = "last_modified_on", nullable = true, updatable = true)
	private Date lastModifiedOn;

	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student")
	private List<Course> courses = new ArrayList<Course>();

}
