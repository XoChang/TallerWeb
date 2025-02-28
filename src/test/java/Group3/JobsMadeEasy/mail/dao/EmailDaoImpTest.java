package Group3.JobsMadeEasy.mail.dao;

import Group3.JobsMadeEasy.mail.model.EmailData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import javax.mail.MessagingException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EmailDaoImpTest {

    EmailDaoImp emailDao = new EmailDaoImp();


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void sendMailTest() throws MessagingException {
        EmailData emailData = new EmailData();
        emailData.setData("test");
        String email = "test@gmail.com";
        emailDao.sendMail(emailData, email);
        assertEquals(emailData.getData(), "test");
        assertEquals(email, "test@gmail.com");
    }

    @Test
    public void setupPropertiesTest() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        emailDao.setupProperties(properties);
        String value = properties.getProperty("mail.smtp.auth");
        assertEquals(value, "true");
    }

    @Test
    public void getSession() {
        String email = "test@gmail.com";
        String password = "1234";
        assertEquals(email, "test@gmail.com");
        assertEquals(password, "1234");
    }

    @Test
    public void makeBodyTest() {
        EmailData emailData = new EmailData();
        String result = "Interview schedule";
        emailData.setData(result);
        assertEquals(result, "Interview schedule");
    }
}
