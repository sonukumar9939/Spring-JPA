package Code.DataBaseProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Code.DataBaseProject.models.User_Details;

public interface UserDetailsRepository  extends JpaRepository<User_Details, Integer>{

}
