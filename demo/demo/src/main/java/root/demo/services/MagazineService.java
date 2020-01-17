package root.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.demo.entites.Magazine;
import root.demo.repositories.MagazineRepository;

@Service
public class MagazineService {

	@Autowired
	MagazineRepository magazineRepository;

	public List<Magazine> getAll() {
		return magazineRepository.findAll();
	}

	public Magazine createMagazine(Magazine magazine) {
		
		System.out.println("creating magazine");
		if(magazine.getIssn() != null) {
			return null;
		}

		if (!checkMagazineValidity(magazine)) {
			return null;
		}

		Magazine dbMagazine = magazineRepository.save(magazine);

		return dbMagazine;
	}

	private boolean checkMagazineValidity(Magazine magazine) {
		if (magazine.getName() == null || magazine.getName().equals("")) {
			return false;
		}

		if (magazine.getPayingType() == null) {
			return false;
		}

		return true;
	}

}
