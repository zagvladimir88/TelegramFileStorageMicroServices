package com.zagvladimir.service.impl;

import com.zagvladimir.dao.AppUserDao;
import com.zagvladimir.dao.RawDataDao;
import com.zagvladimir.entity.AppUser;
import com.zagvladimir.entity.RawData;
import com.zagvladimir.entity.enums.UserState;
import com.zagvladimir.service.MainService;
import com.zagvladimir.service.ProduceService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class MainServiceImpl implements MainService {
    private final RawDataDao rawDataDao;
    private final ProduceService produceService;
    private final AppUserDao appUserDao;

    public MainServiceImpl(RawDataDao rawDataDao, ProduceService produceService, AppUserDao appUserDao) {
        this.rawDataDao = rawDataDao;
        this.produceService = produceService;
        this.appUserDao = appUserDao;
    }

    @Override
    public void processTextMessage(Update update) {
        saveRawData(update);
        var textMessage = update.getMessage();
        var telegramUser = textMessage.getFrom();
        var appUser = findOrSaveAppUser(telegramUser);

        var message = update.getMessage();
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Hello from Node");
        produceService.produceAnswer(sendMessage);
    }

    private AppUser findOrSaveAppUser(User telegramUser){
        AppUser persistentAppUser = appUserDao.findAppUserByTelegramUserId(telegramUser.getId());

        if(persistentAppUser == null){
            AppUser transientAppUser = AppUser.builder()
                    .telegramUserId(telegramUser.getId())
                    .username(telegramUser.getUserName())
                    .firstName(telegramUser.getFirstName())
                    .lastName(telegramUser.getLastName())
                    //TODO  изменить значение по умолчани после добавления регистрации
                    .isActive(true)
                    .state(UserState.BASIC_STATE)
                    .build();
            return appUserDao.save(transientAppUser);
        }
        return persistentAppUser;
    }

    private void saveRawData(Update update) {
        RawData rawData = RawData.builder()
                .event(update)
                .build();
        rawDataDao.save(rawData);
    }
}
