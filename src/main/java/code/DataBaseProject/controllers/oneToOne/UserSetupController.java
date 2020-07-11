package code.DataBaseProject.controllers.oneToOne;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import code.DataBaseProject.Exception.FunctionalException;
import code.DataBaseProject.Response.SuccessRestResponse;
import code.DataBaseProject.models.User;
import code.DataBaseProject.service.UserSetupService;

/**
 * @author sonu
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserSetupController {

	private static Logger logger = LoggerFactory.getLogger(UserSetupController.class);

	@Autowired
	private UserSetupService userSetupService;

	/**
	 * @param id
	 * @return
	 * @throws FunctionalException
	 */
	@RequestMapping(value = "getUser/{id}", method = RequestMethod.GET)
	public ResponseEntity<SuccessRestResponse> getUser(@PathVariable("id") int id) throws FunctionalException {
		SuccessRestResponse response = new SuccessRestResponse();
		logger.info("Get User Details Process Started");
		User user = userSetupService.findById(id);
		if (user == null) {
			response.setSuccess(false);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);

		} else {
			response.setSuccess(true);
			response.setDate(LocalDateTime.now());
			response.setData(user);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);
		}

	}

	/**
	 * @param userName
	 * @param createdBy
	 * @return
	 * @throws FunctionalException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getUsers/{userName}")
	public ResponseEntity<SuccessRestResponse> getUserByUserNameAndCreator(@PathVariable("userName") String userName,
			@RequestParam String createdBy, @RequestParam String criteria) throws FunctionalException {
		SuccessRestResponse response = new SuccessRestResponse();
		logger.info("Get User Details Process Started");
		List<User> users = null;
		if (criteria.equalsIgnoreCase("true")) {
			users = userSetupService.findUsersByNameAndCreatorByCriteriaBuilder(userName, createdBy);
		} else {
			users = userSetupService.findUsersByNameAndCreator(userName, createdBy);
		}
		if (users.isEmpty()) {
			response.setMessage("No User Exists With Given Criteria");
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		} else {
			response.setMessage("Results Fetched Successfully");
			response.setSuccess(true);
			response.setDate(LocalDateTime.now());
			response.setData(users);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);
		}
	}

	/**
	 * @param userName
	 * @return
	 * @throws FunctionalException
	 * @throws ParseException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getUsers")
	public ResponseEntity<SuccessRestResponse> getUserByLikeOperator(@RequestParam String userName,
			@RequestParam String criteria) throws FunctionalException, ParseException {
		SuccessRestResponse response = new SuccessRestResponse();
		logger.info("Get User Details Process Started");
		List<User> users = null;
		if (criteria.equalsIgnoreCase("true")) {
			users = userSetupService.findByGivenCaractersUsingCriteria(userName);
		} else {
			users = userSetupService.findByGivenCaracters(userName);
		}

		if (users.isEmpty()) {
			response.setMessage("No User Exists With Given Criteria");
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		} else {
			response.setMessage("Results Fetched Successfully");
			response.setSuccess(true);
			response.setDate(LocalDateTime.now());
			response.setData(users);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);
		}
	}

	/**
	 * @param userName
	 * @return
	 * @throws FunctionalException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SuccessRestResponse> getUserByUserName(@RequestParam String userName)
			throws FunctionalException {
		SuccessRestResponse response = new SuccessRestResponse();
		List<User> users = userSetupService.findUsersByName(userName);
		if (CollectionUtils.isEmpty(users)) {
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		} else {
			response.setSuccess(true);
			response.setDate(LocalDateTime.now());
			response.setData(users);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);
		}

	}

	/**
	 * @param user
	 * @return
	 * @throws FunctionalException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SuccessRestResponse> saveUser(@Valid @RequestBody User user) throws FunctionalException {

		logger.info("Save User Process Started");
		user.getUserDetails().setUser(user);
		userSetupService.saveUserDetails(user);
		SuccessRestResponse response = new SuccessRestResponse();
		response.setSuccess(true);
		response.setDate(LocalDateTime.now());
		response.setMessage("User Successfully Saved");
		return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
	public ResponseEntity<SuccessRestResponse> updateUserById(@PathVariable("id") int id, @Valid @RequestBody User user)
			throws FunctionalException {
		logger.info("Update Process Initiated");
		SuccessRestResponse response = new SuccessRestResponse();
		User userDetails = userSetupService.findById(id);
		if (userDetails == null) {
			logger.info("No such user exists with id :" + " " + id);
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);

		} else {
			userSetupService.updateUserDetails(userSetupService.updateEntity(userDetails, user));
			response.setSuccess(true);
			response.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);
		}

	}

	/**
	 * @param id
	 * @return
	 * @throws FunctionalException
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<SuccessRestResponse> deleteEntity(@PathVariable("id") int id) throws FunctionalException {
		SuccessRestResponse response = new SuccessRestResponse();
		logger.info("Entity Deletion process initiated");

		User user = userSetupService.findById(id);
		if (user == null) {
			response.setMessage("there does not exists any entity with id " + id);
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);
		} else {
			userSetupService.deleteEntity(user);
			response.setMessage("User Successufuly Deleted with id : " + id);
			response.setSuccess(false);
			response.setDate(LocalDateTime.now());
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NO_CONTENT);
		}
	}

}
