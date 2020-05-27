package code.DataBaseProject.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import code.DataBaseProject.models.Subjects;

public interface SubjectsRepository extends JpaRepository<Subjects, Integer> {
	
	@Query(value = "Select * from subjects s where  s.subject_id =:id " ,nativeQuery = true)
	Set<Subjects> findSubjectsByStudentId(@Param("id") Integer id);

}
