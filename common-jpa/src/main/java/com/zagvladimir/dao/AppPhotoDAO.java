package com.zagvladimir.dao;

import com.zagvladimir.entity.AppPhoto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppPhotoDAO extends JpaRepository<AppPhoto, Long> {
}
