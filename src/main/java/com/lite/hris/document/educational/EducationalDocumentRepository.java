package com.lite.hris.document.educational;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationalDocumentRepository extends JpaRepository<EducationalDocument, Long> {
}
