package root.demo.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import root.demo.entites.UserDetails;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long>{
	
	Optional<UserDetails> findFirstByEmail(String email);
	

}
