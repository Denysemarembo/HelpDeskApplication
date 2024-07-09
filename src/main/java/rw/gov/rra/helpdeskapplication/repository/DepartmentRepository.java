package rw.gov.rra.helpdeskapplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.gov.rra.helpdeskapplication.model.Department;
import rw.gov.rra.helpdeskapplication.model.User;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
//
//    @Query(value = "SELECT * FROM department d WHERE d.assigned_to_id = :userId", nativeQuery = true)
//    List<Department> findByAssignedTo(@Param("userId") Long userId);
//

}
