package rw.gov.rra.helpdeskapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentFormController {
    @GetMapping("/create-department-form")
    public String showCreateRoleForm() {
        return "department";
    }
}
