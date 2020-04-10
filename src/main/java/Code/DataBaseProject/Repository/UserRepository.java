package Code.DataBaseProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Code.DataBaseProject.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "Select * from users a where a.id = :id", nativeQuery = true)
	User findUserById(@Param("id") int id);

	@Query(value="Select * from users a where a.username = :userName",nativeQuery = true)
	List<User> findUsersByName(@Param("userName")String userName);

}
