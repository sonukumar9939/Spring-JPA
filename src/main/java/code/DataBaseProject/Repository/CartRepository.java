package code.DataBaseProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import code.DataBaseProject.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	@Query(value = "Select * from cart s where (s.cart_session =:session AND is_active= 1)", nativeQuery = true)
	Cart checkIfSessionAlreadyExists(@Param("session") String session);

	@Query(value = "Select * from cart s where (s.id=:id AND is_active= 1)", nativeQuery = true)
	Cart findCartById(@Param("id") int id);

	@Query(value = "SELECT item_name FROM cart stud INNER  JOIN  items sub ON  stud.id= sub.cart_id AND (stud.cart_session=:cart AND is_active= 1)", nativeQuery = true)
	List<String> findItemsInCart(@Param("cart") String cart);

}
