package Group3.JobsMadeEasy.mail.dao;

import Group3.JobsMadeEasy.mail.model.EmailData;
import javax.mail.MessagingException;
import java.io.IOException;

public interface IEmailDao {
    void sendMail(EmailData emailData,String mail) throws MessagingException, IOException;
}
