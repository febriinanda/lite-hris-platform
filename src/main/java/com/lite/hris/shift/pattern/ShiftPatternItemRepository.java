package com.lite.hris.shift.pattern;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftPatternItemRepository extends JpaRepository<ShiftPatternItem, Long> {
}
