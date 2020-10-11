package code.DataBaseProject.controllers.ManyToMany;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import code.DataBaseProject.Exception.FunctionalException;
import code.DataBaseProject.Response.SuccessRestResponse;
import code.DataBaseProject.models.Course;
import code.DataBaseProject.models.Student;
import code.DataBaseProject.models.StudentCourseContext;
import code.DataBaseProject.service.StudentCourseService;

@RestController
@RequestMapping(value = "/student")
public class StudentSetupController {

	@Autowired
	private StudentCourseService studentCourseService;

	private static Logger LOGGER = LoggerFactory.getLogger(StudentSetupController.class);

	
	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "save")
	public ResponseEntity<SuccessRestResponse> saveStudentAndCourseDetails(
			@RequestBody StudentCourseContext studentCourseContext) throws FunctionalException {

		LOGGER.info("Student And Course Save Process initiated");
		SuccessRestResponse successRestResponse = new SuccessRestResponse();

		studentCourseService.createEntity(new Student(), new Course(),
				new JSONObject(studentCourseContext.getStudentContext()),
				new JSONObject(studentCourseContext.getCourseContext()));

		successRestResponse.setSuccess(true);
		successRestResponse.setMessage("Student And Cart Details Successfully Saved");
		successRestResponse.setDate(LocalDateTime.now());
		return new ResponseEntity<SuccessRestResponse>(successRestResponse, HttpStatus.OK);

	}

	/**
	 * @param subjectName
	 * @return
	 * @throws FunctionalException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/list/{subjectName}")
	public ResponseEntity<SuccessRestResponse> fetchStudentNamesBySubject(
			@PathVariable("subjectName") String subjectName) throws FunctionalException {
		LOGGER.info("Student And Course Get  Process initiated");
		SuccessRestResponse successRestResponse = new SuccessRestResponse();
		List<String> students = studentCourseService.getStudentsBySubjectsTaken(subjectName);
		if (CollectionUtils.isEmpty(students)) {
			successRestResponse.setMessage("No User has chosen subject : " + subjectName);
			successRestResponse.setSuccess(false);
			successRestResponse.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(successRestResponse, HttpStatus.NOT_FOUND);
		} else {
			successRestResponse.setMessage("Student count with Subject : " + subjectName + " : " + students.size());
			successRestResponse.setSuccess(false);
			successRestResponse.setDate(LocalDateTime.now());
			successRestResponse.setData(students);
			return new ResponseEntity<SuccessRestResponse>(successRestResponse, HttpStatus.OK);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "listUsers")
	public ResponseEntity<SuccessRestResponse> getStudentsEnrolledBetweenSpecifiedDate(@RequestParam String startDate,
			@RequestParam String endDate) throws ParseException {

		SuccessRestResponse successRestResponse = new SuccessRestResponse();
		List<Student> students = studentCourseService.getStudentsBetweenSpecifieddate(startDate, endDate);
		if (CollectionUtils.isEmpty(students)) {
			successRestResponse.setMessage(
					"No User Between Specified dates  : " + "startDate " + startDate + " enddate :" + endDate);
			successRestResponse.setSuccess(false);
			successRestResponse.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(successRestResponse, HttpStatus.NOT_FOUND);
		} else {
			successRestResponse.setMessage("Student Between Specified dates  : " + "startDate " + startDate
					+ " enddate :" + endDate + "studentCount : " + students.size());
			successRestResponse.setSuccess(false);
			successRestResponse.setDate(LocalDateTime.now());
			successRestResponse.setData(students);
			return new ResponseEntity<SuccessRestResponse>(successRestResponse, HttpStatus.OK);
		}

	}

	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "course/prefered")
	public ResponseEntity<SuccessRestResponse> coursePreference() {
		LOGGER.info("Student And Course Get  Process initiated");
		SuccessRestResponse successRestResponse = new SuccessRestResponse();
		List<Course> students = studentCourseService.getSubjectsBuPreference();
		if (CollectionUtils.isEmpty(students)) {
			successRestResponse.setMessage("No Preference Of Subjects : ");
			successRestResponse.setSuccess(false);
			successRestResponse.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(successRestResponse, HttpStatus.NOT_FOUND);
		} else {
			successRestResponse.setMessage("Preference of Subjects By Students : ");
			successRestResponse.setSuccess(false);
			successRestResponse.setDate(LocalDateTime.now());
			successRestResponse.setData(students);
			return new ResponseEntity<SuccessRestResponse>(successRestResponse, HttpStatus.OK);
		}

	}
	
}
