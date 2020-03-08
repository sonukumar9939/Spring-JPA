package Code.DataBaseProject.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Code.DataBaseProject.Exception.FunctionalException;
import Code.DataBaseProject.Repository.UserDetailsRepository;
import Code.DataBaseProject.Repository.UserRepository;
import Code.DataBaseProject.Response.SuccessRestResponse;
import Code.DataBaseProject.models.User;

@RestController
@RequestMapping(value = "/user")
public class UserSetupController {

	private static Logger logger = LoggerFactory.getLogger(UserSetupController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@RequestMapping(value = "getUser/{id}", method = RequestMethod.GET)
	public ResponseEntity<SuccessRestResponse> getUser(@PathVariable("id") int id) throws FunctionalException {
		SuccessRestResponse response = new SuccessRestResponse();
		Object user = userRepository.findById(id);
		if (Optional.empty() == user) {
			response.setSuccess(false);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.NOT_FOUND);

		} else {
			response.setSuccess(true);
			response.setData(user);
			return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessRestResponse> saveUser(@Valid @RequestBody User user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());

		user.setPassword(encodedPassword);
		user.getUserDetails().setUser(user);
		userRepository.save(user);
		SuccessRestResponse response = new SuccessRestResponse();
		response.setSuccess(true);
		response.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<SuccessRestResponse>(response, HttpStatus.OK);
	}

}
