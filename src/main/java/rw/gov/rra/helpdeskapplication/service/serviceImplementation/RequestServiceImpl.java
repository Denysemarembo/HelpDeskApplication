package rw.gov.rra.helpdeskapplication.service.serviceImplementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gov.rra.helpdeskapplication.model.Department;
import rw.gov.rra.helpdeskapplication.model.Request;
import rw.gov.rra.helpdeskapplication.repository.*;
import rw.gov.rra.helpdeskapplication.service.RequestService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

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

    @Autowired
    private RequestRepository requestRepository;

    public Request findById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    public Request save(Request request) {
        return repo.save(request);
    }
    public void deleteRequestById(Long id) {
        if (!requestRepository.existsById(id)) {
            throw new NoSuchElementException("Request not found");
        }
        requestRepository.deleteById(id);
    }

    //change until here


}
