package com.zagvladimir.dao;

import com.zagvladimir.entity.RawData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawDataDAO extends JpaRepository<RawData,Long> {

}
