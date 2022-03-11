package com.starseed.boundless;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//@SpringBootTest
public class smtpSendTest {

    @Test
    void sendMail(){
        String recipient = "monad@eu-cast.com";
        String code = "abc_hello_:" + LocalDateTime.now();

        // 1. 발신자의 메일 계정과 비밀번호 설정
        final String user = "support@eu-cast.com";
        final String password = "eucast1234!";

        // 2. Property에 SMTP 서버 정보 설정
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtps.hiworks.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtps.hiworks.com");

        // 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        // 4. Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성한다.
        // 5. Transport 클래스를 사용하여 작성한 메세지를 전달한다.

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(user));

            // 수신자 메일 주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Subject
            message.setSubject("PLAYDDIT verification code");

            // Text
            message.setText("Welcome to playddit. your code is ["+code+"]");

            Transport.send(message);    // send message

            System.out.println("send mail OK");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        //https://shanepark.tistory.com/26
        //출처: https://miniweb4u.tistory.com/45 [IT정보사전]
    }

}
