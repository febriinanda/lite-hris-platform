package com.lite.hris.approval.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalGroupItemRepository extends JpaRepository<ApprovalGroupItem, Long> {
    List<ApprovalGroupItem> findByHeader(ApprovalGroup group);
}
