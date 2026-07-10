package com.lite.hris.approval.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalTaskRepository extends JpaRepository<ApprovalTask, Long> {
}
