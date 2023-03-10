package com.zagvladimir.service;

import com.zagvladimir.entity.AppDocument;
import com.zagvladimir.entity.AppPhoto;
import com.zagvladimir.service.enums.LinkType;
import org.telegram.telegrambots.meta.api.objects.Message;


public interface FileService {
    AppDocument processDoc(Message telegramMessage);
    AppPhoto processPhoto(Message telegramMessage);
    String generateLink(Long docId, LinkType linkType);
}
