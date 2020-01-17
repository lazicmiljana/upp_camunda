package root.demo.handlers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class UserConfirmationHandler implements TaskListener{

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		
		System.out.println("user confirmation listener complete");
		
		 DelegateExecution execution = delegateTask.getExecution();
		 System.out.println(execution.getVariables());
		
	}
	

}
