package Code.DataBaseProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Code.DataBaseProject.models.Student;

public interface StudentRepositiory extends JpaRepository<Student, Integer>{

	@Query(value = "Select * from students s where s.roll_no =:rollNo",nativeQuery = true)
	Student findStudentByRollNo(@Param("rollNo")String rollNo);

	@Query(value = "Select * from students s where s.id=:id", nativeQuery = true)
	Student findUserById(@Param("id")String id);
	
	@Query(value = "Delete from students  where id =:id", nativeQuery = true)
	Object deleteEntityById(@Param("id") String id);

}
