package root.demo.services;

import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entites.UserDetails;

@Service
public class CheckingDataService implements JavaDelegate {
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("zapoceo service task");

		Map<String, Object> formVariables = execution.getVariables();
		System.out.println(formVariables);
		UserDetails userDetails = new UserDetails();
		userDetails.setEmail(formVariables.get("email").toString());
		userDetails.setfName(formVariables.get("firstName").toString());
		userDetails.setlName(formVariables.get("lastName").toString());
		userDetails.setCity(formVariables.get("city").toString());
		userDetails.setCountry(formVariables.get("country").toString());

		runtimeService.setVariable(execution.getId(), "validData", checkValidity(userDetails));

	}

	private boolean checkValidity(UserDetails userDetails) {

		if (userDetails.getfName() == null || userDetails.getfName().trim().equals("")) {
			return false;
		}

		if (userDetails.getlName() == null || userDetails.getlName().trim().equals("")) {
			return false;
		}

		if (userDetails.getCity() == null || userDetails.getCity().trim().equals("")) {
			return false;
		}

		if (userDetails.getCountry() == null || userDetails.getCountry().trim().equals("")) {
			return false;
		}

		if (userDetails.getEmail() == null || userDetails.getEmail().trim().equals("")) {
			return false;
		}

		return true;
	}

}
