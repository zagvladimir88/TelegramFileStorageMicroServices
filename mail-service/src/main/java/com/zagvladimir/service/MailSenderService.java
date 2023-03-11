package com.zagvladimir.service;

import com.zagvladimir.dto.MailParams;

public interface MailSenderService {
    void send(MailParams mailParams);
}
