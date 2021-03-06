package code.DataBaseProject.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor(force = true)
@EqualsAndHashCode
@RequiredArgsConstructor()
@ToString
public class Items {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "item_name", nullable = false, updatable = true)
	private final  String itemName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cart_id",nullable = false, referencedColumnName = "id")
	@JsonIgnore
	private final Cart cart;

}
