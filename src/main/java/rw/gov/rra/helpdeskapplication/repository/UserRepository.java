package rw.gov.rra.helpdeskapplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import rw.gov.rra.helpdeskapplication.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 User findByUsername(String username);

}



