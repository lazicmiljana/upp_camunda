package root.demo.services;

import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.services.UserDetailsService;
import root.demo.entites.UserDetails;

@Service
public class NewReviewerUserService implements JavaDelegate {

	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("new reviewer user is created with name " + execution.getVariable("username"));
		// TODO Auto-generated method stub
		Map<String, Object> formVariables = execution.getVariables();
		UserDetails userDetails = new UserDetails();
		
		userDetails.setEmail(formVariables.get("email").toString());
		userDetails.setfName(formVariables.get("firstName").toString());
		userDetails.setlName(formVariables.get("lastName").toString());
		userDetails.setCity(formVariables.get("city").toString());
		userDetails.setCountry(formVariables.get("country").toString());
		userDetails.setReviewer(true);

		userDetailsService.createUser(userDetails);	
		runtimeService.removeVariables(execution.getId(), formVariables.keySet());

	}

}
