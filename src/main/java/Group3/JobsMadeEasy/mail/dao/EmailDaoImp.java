package Group3.JobsMadeEasy.mail.dao;

import Group3.JobsMadeEasy.mail.model.EmailData;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

/**
 * @description: It will handle all the database layer queries for the mail .
 */
@Component
public class EmailDaoImp extends EmailDaoConstant implements IEmailDao{

    private String SENDER_MAIL = SENDER_EMAIL;
    private String PASSWORD = SENDER_PASSWORD;

    /**
     *
     * @param emailData
     * @param mail
     * @throws MessagingException
     */
    @Override
    public void sendMail(EmailData emailData, String mail) throws MessagingException{
        Properties properties = new Properties();
        setupProperties(properties);
        Session session = getSession(properties,SENDER_MAIL,PASSWORD);
        makeBody(session,emailData,mail);
    }

    /**
     *
     * @param properties
     */
    public void setupProperties(Properties properties){
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }

    /**
     *
     * @param properties
     * @param senderMail
     * @param password
     * @return session object for smtp protocol
     */
    public Session getSession(Properties properties,String senderMail, String password){
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderMail, password);
            }
        });
        return session;
    }

    /**
     *
     * @param session
     * @param emailData
     * @param mail
     * @throws MessagingException
     */
    public void makeBody(Session session,EmailData emailData,String mail) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(mail +
                "", false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
        msg.setSubject("Congratulations!!");
        msg.setContent(emailData.getData(), "text/html");
        msg.setSentDate(new Date());
        Transport.send(msg);
    }
}
