package Code.DataBaseProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Code.DataBaseProject.Repository.UserDetailsRepository;
import Code.DataBaseProject.Repository.UserRepository;
import Code.DataBaseProject.models.User;

@Service
public class UserSetupService {
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	public void saveUserDetails(User user) {
		userRepository.save(user);
		
	}
	public User findById(int id) {
		return userRepository.findUserById(id);
	}
	public User saveEntity(User userDetails, User user) {
		userDetails.setPassword(user.getPassword());
		userDetails.setUsername(user.getUsername());
		userDetails.getUserDetails().setEmailId(user.getUserDetails().getEmailId());
		userDetails.getUserDetails().setFirstName(user.getUserDetails().getFirstName());
		userDetails.getUserDetails().setLastName(user.getUserDetails().getLastName());
		userDetails.getUserDetails().setMobileNo(user.getUserDetails().getMobileNo());
		userDetails.getUserDetails().setUser(userDetails);
		return userDetails;
	}
	public List<User> findUsersByName(String userName) {
		return userRepository.findUsersByName(userName);
	}

}
