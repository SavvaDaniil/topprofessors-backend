package com.topprofessors.Observer;

import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.topprofessors.Entity.Course;
import com.topprofessors.Entity.User;

@Component
public class UserObserver {

	@Value("${smtp.host}")
	String smtpHost;
	
	@Value("${smtp.port}")
	int smtpPort;
	
	@Value("${smtp.username}")
	String smtpUsername;
	
	@Value("${smtp.password}")
	String smtpPassword;
	
	public void sendMailToUserThatCourseIsDone(User user, Course course) {
		if(user == null) {
			System.out.println("Не указан user");
			return;
		}
		if(course == null) {
			System.out.println("Не указан course");
			return;
		}
		
		String textMessage = "<html><head><meta charset=\"utf-8\"></head>"
		+ "<body><center><p>Поздравляем Вас, Вы успешно прошли курс \""+course.getName()+"\"!</p></center>\r\n"
		+ "<br />\r\n"
		+ "<p>\r\n"
		+ "	Вы успешно прошли обучение по программе повышения квалификации. "
		+ "Удостоверение установленного образца будет направлено Вам по почте в течение 30 дней.<br />"
		+ "По всем вопросам, связанным с обучением, пишите на электронную почту goldnames@professorstoday.org\r\n"
		+ "	<br /><br />\r\n"
		+ "</p>\r\n"
		+ "</body>\r\n"
		+ "</html>\r\n";
		
		Runnable taskSendMail = new Runnable() {
			public void run() {
				sendMailToUser(user, "Успешная сдача курса на сайте topprofessors.ru", textMessage);
			}
		};
		
		Thread threadTaskSendMail = new Thread(taskSendMail);
		threadTaskSendMail.start();
	}
	
	public void sendMailForgetNewCode(User user, String newCode) {

		String textMessage = "<html><head><meta charset=\"utf-8\"></head>"
		+ "<body>\r\n"
		+ "<br />\r\n"
		+ "<p>\r\n"
		+ "Код для восстановление пароля: <b>"+newCode+"</b> \r\n"
		+ "	<br /><br />\r\n"
		+ "</p>\r\n"
		+ "</body>\r\n"
		+ "</html>\r\n";
		
		Runnable taskSendMail = new Runnable() {
			public void run() {
				sendMailToUser(user, "Восстановление пароля", textMessage);
			}
		};
		
		Thread threadTaskSendMail = new Thread(taskSendMail);
		threadTaskSendMail.start();
	}
	
	public void sendMailNewPassword(User user, String newPassword) {

		String textMessage = "<html><head><meta charset=\"utf-8\"></head>"
		+ "<body><center><p>Новый пароль</p></center>\r\n"
		+ "<br />\r\n"
		+ "<p>\r\n"
		+ "Новый автоматически сгенерированный пароль: <b>"+newPassword+"</b> \r\n"
		+ "	<br /><br />\r\n"
		+ "</p>\r\n"
		+ "</body>\r\n"
		+ "</html>\r\n";
		
		Runnable taskSendMail = new Runnable() {
			public void run() {
				sendMailToUser(user, "Новый пароль", textMessage);
			}
		};
		
		Thread threadTaskSendMail = new Thread(taskSendMail);
		threadTaskSendMail.start();
	}
	
	public boolean sendMailToUser(User user, String subject, String textMessage) {
		if(user == null) {
			System.out.println("Не указан user");
			return false;
		}

		
        // Recipient's email ID needs to be mentioned.
        String to = user.getUsername();

        // Sender's email ID needs to be mentioned
        String from = smtpUsername;

        // Assuming you are sending email from through gmails smtp
        //String host = "mail.topprofessors.ru";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.trust", smtpHost);
        

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUsername, smtpPassword);
            }
        });

        
        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            //message.setText("This is actual message");
            message.setContent(textMessage, "text/html; charset=utf-8");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            
            
            System.out.println("Sent message successfully....");
            return true;
        } catch (AuthenticationFailedException mex) {
	        System.out.println("AuthenticationFailedException");
            mex.printStackTrace();
        } catch (MessagingException mex) {
	        System.out.println("MessagingException");
            mex.printStackTrace();
        } catch(Exception e) {
	        System.out.println("Exception");
			e.printStackTrace();
		}
		
        return false;
	}
}
