package root.demo.services;

import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.model.FormSubmissionDto;

@Service
public class TestService implements JavaDelegate{

	@Autowired
	IdentityService identityService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		 String var = "Pera";
	      var = var.toUpperCase();
	      execution.setVariable("input", var);
	      List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("registration");
	      System.out.println(registration);
	      User user = identityService.newUser("");
	      for (FormSubmissionDto formField : registration) {
			if(formField.getFieldId().equals("username")) {
				user.setId(formField.getFieldValue());
			}
			if(formField.getFieldId().equals("password")) {
				user.setPassword(formField.getFieldValue());
			}
			if(formField.getFieldId().equals("name")) {
				user.setFirstName(formField.getFieldValue());
			}
			if(formField.getFieldId().equals("surname")) {
				user.setLastName(formField.getFieldValue());
			}
			if(formField.getFieldId().equals("email")) {
				user.setEmail(formField.getFieldValue());
			}
	      }
	      identityService.saveUser(user);
	}
}
