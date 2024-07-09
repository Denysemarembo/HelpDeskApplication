package rw.gov.rra.helpdeskapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rw.gov.rra.helpdeskapplication.model.*;
import rw.gov.rra.helpdeskapplication.service.*;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class RequestController {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RequestService requestService;

    @GetMapping("/requestForm")
    public String showRequestForm(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("request", new Request());
        return "requestForm";
    }

    @PostMapping("/submitRequest")
    public String submitRequest(@ModelAttribute Request request) {
        System.out.println("Trace request: "+request);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //User assigned = userService.getUserById(Long.parseLong(request.getAssignedTo().toString()));
        //request.setDepart(assigned.getDepartment());
        request.setStatus("I");
        request.setRequestDate(new Date());
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

    @GetMapping("/department/{departmentId}/requests")
    @ResponseBody
    public List<Request> getRequestsByDepartment(@PathVariable Long departmentId) {
        return requestService.getRequestsByDepartment(departmentId);
    }
    //change from here

    @GetMapping("/requests/details/{id}")
    @PreAuthorize("hasAnyRole('SUPER_USER', 'HOD', 'ASSIGNED_USER')")
    public String getRequestDetails(@PathVariable Long id, Model model) {
        Request request = requestService.getRequestById(id);
        model.addAttribute("request", request);
        return "requestDetails";
    }


    @PostMapping("/{id}/comment")
    public Request addComment(@PathVariable Long id, @RequestBody String comment) {
        return requestService.addComment(id, comment);
    }
    @PostMapping("/requests/update")
    public String updateRequest(@RequestParam Long id,
                                @RequestParam String comment,
                                @RequestParam String status) {
        Request request = requestService.findById(id);
        if (request != null) {
            request.setComment(comment);
            request.setStatus(status);
            request.setApprovedDate(LocalDateTime.now());

            requestService.save(request);
        }
        return "redirect:/viewRequests";
    }

    @GetMapping("/requests/{id}")
    @ResponseBody
    public Request getRequestById(@PathVariable Long id) {
        return requestService.findById(id);
    }
    @GetMapping("/success")
    public String showSuccessPage() {
        return "success"; // returns success.html
    }
    @GetMapping("/getDepartment")
    @ResponseBody
    public ResponseEntity<String> getDepartment(@RequestParam String assignedTo) {
        String department = userService.findDepartmentByAssignedTo(assignedTo);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
    @DeleteMapping("/requests/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        try {
            requestService.deleteRequestById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //change until here
}
