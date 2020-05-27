package code.DataBaseProject.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import code.DataBaseProject.Exception.FunctionalException;
import code.DataBaseProject.Response.SuccessRestResponse;
import code.DataBaseProject.models.Student;
import code.DataBaseProject.service.StudentSetupService;

@RestController
@RequestMapping(value = "/students")
public class StudentSetupController {
	private static Logger logger = LoggerFactory.getLogger(UserSetupController.class);

	@Autowired
	private StudentSetupService studentSetupService;

	@RequestMapping(method = RequestMethod.POST, value = "/save/student", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SuccessRestResponse> saveStudent(@RequestBody Student student) throws FunctionalException {
		SuccessRestResponse response = new SuccessRestResponse();

		logger.info("Student save process initiated");
		Student students = studentSetupService.checkIfUserIsUnique(student.getRollNo());
		if (students != null) {
			response.setSuccess(false);
			response.setMessage("Student With Roll No " + student.getRollNo() + " " + "already exists");
			response.setTimestamp(System.currentTimeMillis());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		studentSetupService.createEntity(new Student(),student);
		response.setSuccess(true);
		response.setMessage("Student Successfully Saved");
		response.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update/student/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<SuccessRestResponse> updateStudent(@PathVariable ("id") String id,@RequestBody Student student) throws FunctionalException{
		
		SuccessRestResponse response = new SuccessRestResponse();

		logger.info("Student update process initiated");
		Student studObject = studentSetupService.checkIfUserIsPresent(id);
		if (studObject == null) {
			response.setSuccess(false);
			response.setMessage("Student With ID  " + id + " " + "does not exists");
			response.setTimestamp(System.currentTimeMillis());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		}
		else {
			studentSetupService.updateEntity(student,studObject,id);
			response.setSuccess(true);
			response.setMessage("Student Successfully Updated");
			response.setTimestamp(System.currentTimeMillis());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/student/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<SuccessRestResponse> deleteStudent(@PathVariable ("id") String id) throws FunctionalException{
		
		SuccessRestResponse response = new SuccessRestResponse();

		logger.info("Student delete process initiated");
		Student studObject = studentSetupService.checkIfUserIsPresent(id);
		if (studObject == null) {
			response.setSuccess(false);
			response.setMessage("Student With ID  " + id + " " + "does not exists");
			response.setTimestamp(System.currentTimeMillis());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		}
		else {
			studentSetupService.deleteEntity(studObject);
			response.setSuccess(true);
			response.setMessage("Student Successfully deleted");
			response.setTimestamp(System.currentTimeMillis());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);
		}
		
	}
	
}
