package root.demo.handlers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class EndProcessHandler implements ExecutionListener{

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("proces se zavrsio");
		
	}

}
