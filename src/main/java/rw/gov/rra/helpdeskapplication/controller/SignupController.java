package rw.gov.rra.helpdeskapplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rw.gov.rra.helpdeskapplication.model.Department;
import rw.gov.rra.helpdeskapplication.model.Role;
import rw.gov.rra.helpdeskapplication.model.User;
import rw.gov.rra.helpdeskapplication.service.DepartmentService;
import rw.gov.rra.helpdeskapplication.service.RoleService;
import rw.gov.rra.helpdeskapplication.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String showSignupForm(Model model) {
        List<Role> roles = roleService.getAllRoles();
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("roles", roles);
        model.addAttribute("departments", departments);
        return "signup";
    }

    @PostMapping("/login")
    public String signup(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("departmentId") Long departmentId,
                         @RequestParam("roleId") Long roleId) {
    	
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Department department = departmentService.getDepartmentById(departmentId);
        Role role = roleService.getRoleById(roleId);
        user.setDepartment(department);
        user.setRole(role);

        userService.createUser(user);

        return "/login";

    }
}
