package rw.gov.rra.helpdeskapplication.service;


import org.springframework.stereotype.Service;
import rw.gov.rra.helpdeskapplication.model.Department;
import rw.gov.rra.helpdeskapplication.model.Request;

import java.util.List;

@Service
public interface RequestService {
    Request createRequest(Request request);

    Request getRequestById(Long requestId);
    List<Request> findByDepartId(Long id);
    List<Request> getAllRequests();
    List<Request> getRequestsByDepartment(Long departmentId);
    Request addComment(Long requestId, String comment);
    Request findById(Long id);
    Request save(Request request);
    void deleteRequestById(Long requestId);

    List<Request> getRequestsAssignedToUser(String username);

    //change upto here

}
