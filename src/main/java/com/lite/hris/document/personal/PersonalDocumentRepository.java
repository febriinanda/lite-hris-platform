package com.lite.hris.document.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDocumentRepository extends JpaRepository<PersonalDocument, Long> {
}
