package rw.gov.rra.helpdeskapplication.service;



import rw.gov.rra.helpdeskapplication.model.Request;
import rw.gov.rra.helpdeskapplication.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long userId);
    List<User> getAllUsers();
    String findDepartmentByAssignedTo(String assignedTo);


    User findByUsername(String assignedTo);
    User findById(Long id);
}

