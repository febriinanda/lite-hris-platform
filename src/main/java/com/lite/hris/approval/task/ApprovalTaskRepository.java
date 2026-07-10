package com.lite.hris.approval.task;

import com.lite.hris.request.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalTaskRepository extends JpaRepository<ApprovalTask, Long> {
    List<ApprovalTask> findByRequestTypeAndRequestId(RequestType type, long requestId);
}
