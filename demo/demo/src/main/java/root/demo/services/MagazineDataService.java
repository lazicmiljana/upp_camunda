package root.demo.services;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entites.Magazine;
import root.demo.services.MagazineService;

@Service
public class MagazineDataService implements JavaDelegate{
	
	@Autowired
	MagazineService magazineService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Obrada unijetih podata MagazinDataService");
				
		
	}

	
}
