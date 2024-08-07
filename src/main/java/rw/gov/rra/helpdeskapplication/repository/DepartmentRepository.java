package rw.gov.rra.helpdeskapplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import rw.gov.rra.helpdeskapplication.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
