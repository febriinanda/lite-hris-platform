package com.lite.hris.approval.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalGroupRepository extends JpaRepository<ApprovalGroup, Long> {
        }
