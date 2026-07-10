package com.lite.hris.approval.flow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalFlowItemRepository extends JpaRepository<ApprovalFlowItem, Long> {
    List<ApprovalFlowItem> findByHeader(ApprovalFlow header);
}
