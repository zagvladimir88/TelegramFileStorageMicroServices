package com.zagvladimir.service.impl;

import com.zagvladimir.dao.AppUserDAO;
import com.zagvladimir.service.UserActivationService;
import com.zagvladimir.utils.CryptoTool;
import org.springframework.stereotype.Service;

@Service
public class UserActivationServiceImpl implements UserActivationService {
    private final AppUserDAO appUserDAO;
    private final CryptoTool cryptoTool;

    public UserActivationServiceImpl(AppUserDAO appUserDAO, CryptoTool cryptoTool) {
        this.appUserDAO = appUserDAO;
        this.cryptoTool = cryptoTool;
    }

    @Override
    public boolean activation(String cryptoUserId) {
        var userId = cryptoTool.idOf(cryptoUserId);
        var userForActivate = appUserDAO.findAppUserById(userId);
        if(userForActivate.isPresent()) {
            var user = userForActivate.get();
            user.setIsActive(true);
            appUserDAO.save(user);
            return true;
        }
        return false;
    }
}
