package com.notification.service;

import com.notification.dto.SoldDto;
import com.notification.dto.UserDto;
import com.notification.entities.Email;
import com.notification.integration.SoldIntegration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@Slf4j
@Service
public class ValidEmailService {

    @Autowired
    SoldIntegration soldIntegration;

    @Autowired
    private JavaMailSender javaMailSender;

    private static final String TEMPLATE_EMAIL_VALID_HTML = "/template/email_valid.html";
    private static final Pattern LINK_TARGET = Pattern.compile("-link###link-");

    @Value("${link.host.email}")
    private String hostLink;

    public void create(SoldDto soldDto){
        log.info("send payment create soldDto");
        this.soldIntegration.createPayment(soldDto);
    }

    public void sendEmailToValidEmail(UserDto userDto){
        try {
            log.info("send sendEmailToValidEmail soldDto");
            log.info("send sendEmailToValidEmail soldDto :{}", userDto.getEmail());

            String html = IOUtils.toString(new ClassPathResource(TEMPLATE_EMAIL_VALID_HTML).getInputStream(), String.valueOf(StandardCharsets.UTF_8));
            String linkValidEmail =  hostLink + "/" + userDto.getId() + "/" + userDto.getUserDetails().getEmailValidCode();
            html = LINK_TARGET.matcher(html).replaceAll(StringEscapeUtils.escapeHtml(linkValidEmail));

            Email email = Email.builder()
                    .from("dayvson.red@gmail.com")
                    .to(userDto.getEmail())
                    .text(html)
                    .subject("Bem vindo ao SellersShift - "+ userDto.getName())
                    .build();

            this.sendEmail(email);

        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("ERROR SEND EMAIL");
        }
    }

    public void sendEmailTest(){
        try {
            String html = IOUtils.toString(new ClassPathResource(TEMPLATE_EMAIL_VALID_HTML).getInputStream(), String.valueOf(StandardCharsets.UTF_8));
            String linkValidEmail =  hostLink + "/1111/5555";
            html = LINK_TARGET.matcher(html).replaceAll(StringEscapeUtils.escapeHtml(linkValidEmail));

            Email email = Email.builder()
                    .from("dayvson.red@gmail.com")
                    .to("dayvson.red@gmail.com")
                    .text(html)
                    .subject("Bem vindo ao SellersShift Dayvson final")
                    .build();

            this.sendEmail(email);
            log.info("send email test");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("ERROR SEND EMAIL");
        }
    }

    public void sendEmail(Email email){
        try {
            log.info("send sendEmailTest soldDto");
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

            mimeMessageHelper.setFrom(email.getFrom());
            mimeMessageHelper.setTo(email.getTo());
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setText(email.getText(), true);

            javaMailSender.send(message);
        }catch (Exception e){
         log.error(e.getMessage());
         throw new RuntimeException("ERROR SEND EMAIL");
        }
    }
}
