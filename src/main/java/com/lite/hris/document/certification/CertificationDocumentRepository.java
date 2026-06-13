package com.lite.hris.document.certification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationDocumentRepository extends JpaRepository<CertificationDocument, Long> {
}
