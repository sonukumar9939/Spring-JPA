package code.DataBaseProject.models;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "countries")
@Data
@ToString
@NoArgsConstructor(force = true)
@Cacheable
public class Countries {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String countryName;
	
	@NotBlank
	private String countryCode;
	
	@NotNull
	private long population;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date  stablishedDate;

}
