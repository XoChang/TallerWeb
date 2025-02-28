package Group3.JobsMadeEasy.mail.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailDataTest {
    @Test
    public void testEmailDataTestClass() {
        EmailData emailDataTest = Mockito.spy(new EmailData());
        emailDataTest.getData();
        emailDataTest.setData("test");
        assertEquals(emailDataTest.getData(), "test");
    }
}
