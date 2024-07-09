package rw.gov.rra.helpdeskapplication.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
        return "dashboard";
    }

    @GetMapping("/dashboard/requestForm")
    public String requestForm() {
        return "requestForm";
    }

    @GetMapping("/submitRequest")
    public String submitRequest() {
        return "submitRequest";
    }

    @GetMapping("/requestFormss")
    public String postRequests() {
        return "requestForm";
    }

    @GetMapping("/logout")
    public String logout(){
        return "login";
    }
}

