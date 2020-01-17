package root.demo.repositories;
import org.springframework.stereotype.Repository;

import root.demo.entites.ScienceField;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ScienceFieldRepository extends JpaRepository<ScienceField, Long>{

	
}
