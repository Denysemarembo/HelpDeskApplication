package rw.gov.rra.helpdeskapplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import rw.gov.rra.helpdeskapplication.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	//Role findByName( String name)

}
