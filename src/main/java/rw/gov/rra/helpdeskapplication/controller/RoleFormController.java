package rw.gov.rra.helpdeskapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleFormController {
    @GetMapping("/create-role-form")
    public String showCreateRoleForm() {
        return "role";
    }
}
