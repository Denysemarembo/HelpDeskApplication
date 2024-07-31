package rw.gov.rra.helpdeskapplication.service.serviceImplementation;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gov.rra.helpdeskapplication.model.Department;
import rw.gov.rra.helpdeskapplication.model.Request;
import rw.gov.rra.helpdeskapplication.repository.*;
import rw.gov.rra.helpdeskapplication.service.RequestService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class RequestServiceImpl implements RequestService {
    private static final Logger logger = LoggerFactory.getLogger(RequestService.class);


    @Autowired
    private RequestRepository repo;

    @Override
    public Request createRequest(Request request) {
        return repo.save(request);
    }

    @Override
    public Request getRequestById(Long requestId) {
        return repo.findById(requestId).orElse(null);
    }

    @Override
    public List<Request> getAllRequests() {
        return repo.findAll();
    }

      @Override
      public List<Request> findByDepartId(Long departmentId) {
        return repo.findByDepartId(departmentId);
      }


    public  List<Request> getRequestsByDepartment(Long id){
        return repo.findByDepartId(id);
    }
    //change from here

    public Request addComment(Long requestId, String comment) {
        Optional<Request> request = repo.findById(requestId);
        if (request.isPresent()) {
            Request req = request.get();
            req.addComment(comment);
            return repo.save(req);
        }
        throw new RuntimeException("Request not found");
    }

//    @Autowired
//    private RequestRepository requestRepository;
//
    public Request findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Request save(Request request) {
        return repo.save(request);
    }
    public void deleteRequestById(Long id) {
        logger.info("Deleting request with ID: " + id);
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Request not found");
        } else {
            repo.deleteById(id);

        }
    }
    public List<Request> getRequestsAssignedToUser(String username) {
        return repo.findByAssignedToUsername(username);
    }

    //change until here


}
