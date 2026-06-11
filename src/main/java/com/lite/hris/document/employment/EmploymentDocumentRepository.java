package com.lite.hris.document.employment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentDocumentRepository extends JpaRepository<EmploymentDocument, Long> {
}
