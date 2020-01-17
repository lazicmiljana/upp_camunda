package root.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendMailService implements JavaDelegate{
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub		  
	//	this.sendMessage(execution.getVariable("email").toString(), "hello", "Welcome! Thank you for using our services. Please confirm registration via http://localhost:4200/activate");			
	}
	
	@Async
	public void sendMessage(String to, String subject, String message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);


		javaMailSender.send(simpleMailMessage);
	}
	

}
