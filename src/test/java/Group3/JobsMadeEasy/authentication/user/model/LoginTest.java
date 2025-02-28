package Group3.JobsMadeEasy.authentication.user.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    public LoginTest() {
        String email = "test@gmail.com";
        String password = "1234";
        Login test = new Login(email, password);
        assertEquals(email, test.getEmailId());
        assertEquals(password, test.getPassword());
    }

    @Test
    public void testUserClass() {
        Login login = Mockito.spy(new Login());
        login.getEmailId();
        login.getPassword();
        login.setEmailId("test@gmail.com");
        login.setPassword("test@123");
        assertEquals(login.getEmailId(), "test@gmail.com");
        assertEquals(login.getPassword(), "test@123");
    }
}

