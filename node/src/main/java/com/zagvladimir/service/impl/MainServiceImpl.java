package com.zagvladimir.service.impl;

import com.zagvladimir.dao.RawDataDao;
import com.zagvladimir.entity.RawData;
import com.zagvladimir.service.MainService;
import com.zagvladimir.service.ProduceService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class MainServiceImpl implements MainService {
    private final RawDataDao rawDataDao;
    private final ProduceService produceService;

    public MainServiceImpl(RawDataDao rawDataDao, ProduceService produceService) {
        this.rawDataDao = rawDataDao;
        this.produceService = produceService;
    }

    @Override
    public void processTextMessage(Update update) {
        saveRawData(update);

        var message = update.getMessage();
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText("Hello from Node");
        produceService.produceAnswer(sendMessage);
    }

    private void saveRawData(Update update) {
        RawData rawData = RawData.builder()
                .event(update)
                .build();
        rawDataDao.save(rawData);
    }
}
