package com.lite.hris.approval.flow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalFlowRepository extends JpaRepository<ApprovalFlow, Long> {
}
