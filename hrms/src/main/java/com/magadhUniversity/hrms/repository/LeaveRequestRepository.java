package com.magadhUniversity.hrms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.magadhUniversity.hrms.model.LeaveRequest;

@Repository
public interface LeaveRequestRepository extends CrudRepository<LeaveRequest, Long> {
    // Additional query methods can be defined here if needed
}
