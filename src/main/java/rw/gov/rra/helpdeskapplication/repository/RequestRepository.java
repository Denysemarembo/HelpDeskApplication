package rw.gov.rra.helpdeskapplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import rw.gov.rra.helpdeskapplication.model.Request;

import java.util.List;


public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByDepartId(Long id);
    List<Request> findByAssignedToUsername(String username);


}