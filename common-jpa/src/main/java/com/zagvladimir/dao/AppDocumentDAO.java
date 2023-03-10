package com.zagvladimir.dao;

import com.zagvladimir.entity.AppDocument;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppDocumentDAO extends JpaRepository<AppDocument, Long> {
}
