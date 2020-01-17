package root.demo.handlers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class ProcessingHandler implements ExecutionListener{

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("processing handler");
	}

}
