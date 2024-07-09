package rw.gov.rra.helpdeskapplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.gov.rra.helpdeskapplication.model.Department;
import rw.gov.rra.helpdeskapplication.model.User;
import rw.gov.rra.helpdeskapplication.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
//    @GetMapping("/users/{userId}/department")
//    public ResponseEntity<List<Department>> getUserDepartments(@PathVariable Long userId) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            List<Department> departments = departmentRepository.findByAssignedTo(user);
//            return ResponseEntity.ok(departments);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/getDepartment")
    @ResponseBody
    public String getDepartment(@RequestParam("assignedTo") String assignedTo) {
        User user = userService.findByUsername(assignedTo);
        if (user != null) {
            Department department = user.getDepartment();
            if (department != null) {
                return department.getName();  // Ensure the department name is returned
            }
        }
        return "";
    }

}
