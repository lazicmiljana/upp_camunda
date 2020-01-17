package root.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import root.demo.entites.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long>{
	
	Optional<Magazine> findFirstByName(String name);
	

}
