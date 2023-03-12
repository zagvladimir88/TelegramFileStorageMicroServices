package com.zagvladimir.service.impl;

import com.zagvladimir.dao.AppUserDAO;
import com.zagvladimir.entity.AppUser;
import com.zagvladimir.utils.CryptoTool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserActivationServiceImplTest {

    @Mock
    private AppUserDAO appUserDAO;

    @Mock
    private CryptoTool cryptoTool;

    @InjectMocks
    private UserActivationServiceImpl userActivationService;



    @Test
    void testActivationPositive_shouldActivateUserAndSetIsActiveToTrue() {
        String cryptoUserId = "cryptoUserId";
        Long userId = 1L;
        AppUser user = new AppUser();
        user.setIsActive(false);

        when(cryptoTool.idOf(cryptoUserId)).thenReturn(userId);
        when(appUserDAO.findAppUserById(userId)).thenReturn(Optional.of(user));

        boolean result = userActivationService.activation(cryptoUserId);

        assertTrue(result);
        assertTrue(user.getIsActive());
        verify(appUserDAO,times(1)).findAppUserById(userId);
        verify(cryptoTool,times(1)).idOf(cryptoUserId);
    }

    @Test
    void testActivationNegative_shouldNotActivateUserIfUserNotFound() {
        String cryptoUserId = "cryptoUserId";

        when(cryptoTool.idOf(anyString())).thenReturn(1L);
        when(appUserDAO.findAppUserById(any())).thenReturn(Optional.empty());

        boolean result = userActivationService.activation(cryptoUserId);

        assertFalse(result);
    }
}