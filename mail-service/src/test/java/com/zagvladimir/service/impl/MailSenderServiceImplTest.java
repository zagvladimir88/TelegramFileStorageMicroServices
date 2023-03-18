package com.zagvladimir.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.zagvladimir.dto.MailParams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(MockitoExtension.class)
class MailSenderServiceImplTest {

    @Mock
    private JavaMailSender javaMailSender;
    @InjectMocks
    private MailSenderServiceImpl mailSenderService;

    @Test
    void send_shouldSendMail() {
        MailParams mailParams = new MailParams();
        mailParams.setId("1234");
        mailParams.setEmailTo("user@example.com");

        mailSenderService.send(mailParams);

        verify(javaMailSender).send(any(SimpleMailMessage.class));
    }
}