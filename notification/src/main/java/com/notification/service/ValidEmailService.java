package com.notification.service;

import com.notification.dto.SoldDto;
import com.notification.dto.UserDto;
import com.notification.entities.Email;
import com.notification.integration.SoldIntegration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class ValidEmailService {

    @Autowired
    SoldIntegration soldIntegration;

    @Autowired
    private JavaMailSender javaMailSender;

    public void create(SoldDto soldDto){
        log.info("send payment create soldDto");
        this.soldIntegration.createPayment(soldDto);
    }

    public void sendEmailToValidEmail(UserDto userDto){
        log.info("send sendEmailToValidEmail soldDto");
        log.info("send sendEmailToValidEmail soldDto :{}", userDto.getEmail());



    }

    public void sendEmailTest(){
        try {
            log.info("send sendEmailTest soldDto");

            Email email = Email.builder()
                    .from("dayvson.red@gmail.com")
                    .to("dayvson.red@gmail.com")
                    .text("do dayvson")
                    .subject("teste local")
                    .build();
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

            mimeMessageHelper.setFrom(email.getFrom());
            mimeMessageHelper.setTo(email.getTo());
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setText(email.getText());

            javaMailSender.send(message);
        }catch (Exception e){
         log.error(e.getMessage());
         throw new RuntimeException("ERROR SEND EMAIL");
        }
    }
}
