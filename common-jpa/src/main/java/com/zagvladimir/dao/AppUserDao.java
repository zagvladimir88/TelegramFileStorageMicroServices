package com.zagvladimir.dao;

import com.zagvladimir.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserDao extends JpaRepository<AppUser,Long> {
    AppUser findAppUserByTelegramUserId(Long id);
}
