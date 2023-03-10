package com.zagvladimir.dao;

import com.zagvladimir.entity.BinaryContent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BinaryContentDAO extends JpaRepository<BinaryContent, Long> {
}
