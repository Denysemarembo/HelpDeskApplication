package rw.gov.rra.helpdeskapplication.service.serviceImplementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rw.gov.rra.helpdeskapplication.model.Request;
import rw.gov.rra.helpdeskapplication.model.User;
import rw.gov.rra.helpdeskapplication.repository.UserRepository;
import rw.gov.rra.helpdeskapplication.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return repo.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public String findDepartmentByAssignedTo(String assignedTo) {
        User user = repo.findByUsername(assignedTo);
        return user != null && user.getDepartment() != null ? user.getDepartment().getName() : "";
    }

    @Override
    public User findByUsername(String assignedTo) {
        return null;
    }
    public User findById(Long id) {
        return repo.findById(id).orElse(null);
    }

}
