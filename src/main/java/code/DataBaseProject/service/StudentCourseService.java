package code.DataBaseProject.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import code.DataBaseProject.Exception.FunctionalException;
import code.DataBaseProject.models.Course;
import code.DataBaseProject.models.Student;

@Service
public class StudentCourseService {

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional()
	public void createEntity(Student student, Course course, JSONObject studentdetails, JSONObject coursedetails)
			throws FunctionalException {

		student.setRollNo(studentdetails.getString("rollNo"));
		student.setStudentName(studentdetails.getString("studentName"));
		course.setCourseName(coursedetails.getString("courseName"));

		entityManager.persist(student);
		entityManager.persist(course);

		course.getStudent().add(student);
		student.getCourses().add(course);

		entityManager.persist(student);

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<String> getStudentsBySubjectsTaken(String subjectName) {
		Query query = entityManager.createNativeQuery(
				"SELECT student.student_name FROM student_course, student, course WHERE (student_course.student_id=student.id AND student_course.course_id=course.id) "
						+ "AND course.course_name=?1");
		query.setParameter(1, subjectName);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Student> getStudentsBetweenSpecifieddate(String startDate, String endDate) throws ParseException {

		DateFormat format = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss.SSSSSS");
		Date start_date = format.parse(startDate);
		Date end_date = format.parse(startDate);
		Query query = entityManager.createQuery("Select s from  Student s where s.createdOn> ?1 AND s.createdOn< ?2");
		query.setParameter(1, start_date, TemporalType.TIMESTAMP);
		query.setParameter(2, end_date, TemporalType.TIMESTAMP);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Course> getSubjectsBuPreference() {
		Query query = entityManager.createQuery("Select c from Course c group by c.student HAVING Count(c.student) >3");
		List<Course> courses= query.getResultList();
		return courses;
	}

}
