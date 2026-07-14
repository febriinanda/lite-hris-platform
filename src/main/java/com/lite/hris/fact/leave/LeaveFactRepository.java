package com.lite.hris.fact.leave;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveFactRepository extends JpaRepository<LeaveFact, Long> {
}
