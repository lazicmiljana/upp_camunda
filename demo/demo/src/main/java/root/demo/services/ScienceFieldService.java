package root.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entites.ScienceField;
import root.demo.repositories.ScienceFieldRepository;

@Service
public class ScienceFieldService {

	@Autowired
	ScienceFieldRepository scienceFieldRepository;

//	  public ScienceField getById(String id) {
//	        return scienceFieldRepository.findById(id).orElse(null);
//	    }

	public List<ScienceField> getAll() {
		return scienceFieldRepository.findAll();
	}

	public ScienceField createScienceField(ScienceField scienceField) {

		System.out.println("creating science field");

		if (scienceField.getName() == null || scienceField.getName().equals("")) {
			return null;
		}

		return scienceFieldRepository.save(scienceField);
	}

//	    public ScienceField updateScienceField(ScienceField newScienceField, String id) {
//
//	        if (id == null) {
//	            return null;
//	        }
//
//        ScienceField scienceField = getById(id);
//        if (scienceField == null) {
//	            return null;
//	        }
//
//	        if (scienceField.getName() == null || scienceField.getName().equals("")) {
//	            return null;
//	        }
//
//	        scienceField.setName(newScienceField.getName());
//	        return scienceFieldRepository.save(scienceField);
//
//	    }

//	    public void deleteScienceField(String id) {
//	        if (id != null) {
//	            scienceFieldRepository.deleteByScienceFieldId(id);
//	        }
//	    }

}
