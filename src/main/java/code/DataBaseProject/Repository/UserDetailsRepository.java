package code.DataBaseProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import code.DataBaseProject.models.User_Details;

public interface UserDetailsRepository  extends JpaRepository<User_Details, Integer>{

}
