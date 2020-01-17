package root.demo.services;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entites.Magazine;

@Service
public class MagazinePersistanceService implements JavaDelegate{
	
	@Autowired
	MagazineService magazineService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("magazine persistance service");
		Map<String, Object> formVariables = execution.getVariables();
		
		Magazine magazine = new Magazine();
		magazine.setName(formVariables.get("name").toString());
		magazine.setPayingType(formVariables.get("payingType").toString());	
		magazineService.createMagazine(magazine);
		
		execution.removeVariables();	
		
	}
	
	

}
