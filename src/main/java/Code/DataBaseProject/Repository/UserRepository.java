package Code.DataBaseProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Code.DataBaseProject.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
}
