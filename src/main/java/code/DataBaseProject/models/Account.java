package code.DataBaseProject.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor(force = true)
@EqualsAndHashCode
public class Account  {
	
	@EmbeddedId
	private AccountId accountId;
}
