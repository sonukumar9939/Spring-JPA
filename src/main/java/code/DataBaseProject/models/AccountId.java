package code.DataBaseProject.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor(force = true)
@EqualsAndHashCode
public class AccountId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String accountNumber;
	
	private String accountType;

}
