package root.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entites.UserDetails;
import root.demo.repositories.UserRepository;

@Service
public class UserDetailsService {

	@Autowired
	UserRepository userDetailsRepository;

	public UserDetails getById(Long id) {
		return userDetailsRepository.findById(id).orElse(null);
	}

	public List<UserDetails> getAll() {
		return userDetailsRepository.findAll();
	}
	
	public ArrayList<UserDetails> getAllReviewers() {
		List<UserDetails> allUsers = userDetailsRepository.findAll();
		ArrayList<UserDetails> reviewerUsers = new ArrayList<UserDetails>();
		for(UserDetails user:allUsers) {
			if(user.getReviewer().equals(true)) {
				reviewerUsers.add(user);
			}
		}
		return reviewerUsers;
	}

	public UserDetails createUser(UserDetails userDetails) {
		System.out.println("create User");

		if (userDetails.getUserId() != null) {
			return null;
		}

		if (!checkUserValidity(userDetails)) {
			return null;
		}

		return userDetailsRepository.save(userDetails);
	}

	public UserDetails updateUser(UserDetails newUserDetails, Long id) {

		if (id == null) {
			return null;
		}

		UserDetails userDetails = getById(id);
		if (userDetails == null) {
			return null;
		}

		if (!checkUserValidity(newUserDetails)) {
			return null;
		}

		userDetails.setCity(newUserDetails.getCity());
		userDetails.setCountry(newUserDetails.getCountry());
		userDetails.setEmail(newUserDetails.getEmail());
		userDetails.setfName(newUserDetails.getfName());
		userDetails.setlName(newUserDetails.getlName());

		return userDetailsRepository.save(userDetails);
	}

	private boolean checkUserValidity(UserDetails userDetails) {
		if (userDetails.getfName() == null || userDetails.getfName().equals("")) {
			return false;
		}

		if (userDetails.getlName() == null || userDetails.getlName().equals("")) {
			return false;
		}

		if (userDetails.getCity() == null || userDetails.getCity().equals("")) {
			return false;
		}

		if (userDetails.getCountry() == null || userDetails.getCountry().equals("")) {
			return false;
		}

		if (userDetails.getEmail() == null || userDetails.getEmail().equals("")) {
			return false;
		}

		return true;
	}

	public void deleteUser(Long id) {
		if (id != null) {
			userDetailsRepository.deleteById(id);
		}
	}

}
