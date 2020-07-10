package code.DataBaseProject.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import code.DataBaseProject.models.Items;

public interface ItemsRepository extends JpaRepository<Items, Integer> {
	
	@Query(value = "Select * from items s where  s.cart_id =:id " ,nativeQuery = true)
	Set<Items> findItemsByItemId(@Param("id") Integer id);

}
