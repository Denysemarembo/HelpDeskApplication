package rw.gov.rra.helpdeskapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rw.gov.rra.helpdeskapplication.model.*;
import rw.gov.rra.helpdeskapplication.repository.UserRepository;
import rw.gov.rra.helpdeskapplication.service.*;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class RequestController {
    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserRepository repo;

    @GetMapping("/requestForm")
    public String showRequestForm(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("request", new Request());
        return "requestForm";
    }

    @PostMapping("/submitRequest")
    public String submitRequest(@ModelAttribute Request request) {
        System.out.println("Trace request: " + request);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        request.setStatus("I");
        request.setRequestDate(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User requestor = userService.findByUsername(username);
        request.setRequestor(requestor);
        requestService.createRequest(request);
        return "redirect:/success";
    }


    @GetMapping("/viewRequests")
    public String viewRequests(@RequestParam(required = false) Long departmentId, Model model) {
        List<Request> requests;
        if (departmentId != null) {
            requests = requestService.getRequestsByDepartment(departmentId);
        } else {
            requests = requestService.getAllRequests();
        }
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("requests", requests);
        model.addAttribute("departments", departments);
        model.addAttribute("selectedDepartmentId", departmentId);
        return "viewRequest";
    }
    @GetMapping("/postRequests")
    public String viewPostRequests(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<Request> assignedRequests = requestService.getRequestsAssignedToUser(username);
        model.addAttribute("requests", assignedRequests);
        return "postRequests";
    }

    @GetMapping("/department/{departmentId}/requests")
    @ResponseBody
    public List<Request> getRequestsByDepartment(@PathVariable Long departmentId) {
        return requestService.getRequestsByDepartment(departmentId);
    }

    @PostMapping("/{id}/comment")
    public Request addComment(@PathVariable Long id, @RequestBody String comment) {
        return requestService.addComment(id, comment);
    }
    @PostMapping("/requests/update")
    public String updateRequest(@RequestParam Long id,
                                @RequestParam String comment,
                                @RequestParam String status,
                                @RequestParam(required = false) String redirect) {
        Request request = requestService.findById(id);
        if (request != null) {
            request.setComment(comment);
            request.setStatus(status);
            request.setApprovedDate(LocalDateTime.now());

            requestService.save(request);
        }
        if ("postRequests".equals(redirect)) {
            return "redirect:/postRequests";
        } else {
            return "redirect:/viewRequests";
        }
    }

    @GetMapping("/requests/{id}")
    @ResponseBody
    public Request getRequestById(@PathVariable Long id) {
        return requestService.findById(id);
    }
    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }
    @GetMapping("/getDepartment")
    @ResponseBody
    public ResponseEntity<String> getDepartment(@RequestParam String assignedTo) {
        String department = userService.findDepartmentByAssignedTo(assignedTo);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
    @DeleteMapping("/requests/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        logger.info("Received request to delete request with ID: " + id);
        requestService.deleteRequestById(id);
        return ResponseEntity.ok().build();
    }

}
