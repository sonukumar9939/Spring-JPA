package code.DataBaseProject.models;

import java.time.LocalDateTime;
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
@Table(name = "cart")
@Data
@ToString
@NoArgsConstructor(force = true)
@EqualsAndHashCode( exclude={"items"})
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	@Column(name = "cart_holder")
	private String cartHolder;

	@Column(name = "cart_session")
	private String cartSession;

	@CreationTimestamp
	@Column(name = "craeted_on", nullable = true, updatable = true)
	private LocalDateTime createdOn;

	@CreatedBy
	@Column(name = "created_by", nullable = true, updatable = true)
	private String createdBy;

	@UpdateTimestamp
	@Column(name = "last_modified_on", nullable = true, updatable = true)
	private LocalDateTime lastModifiedOn;
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval= true,mappedBy= "cart")
	private Set<Items> items = new LinkedHashSet<Items>();

}
