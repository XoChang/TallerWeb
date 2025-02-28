package Group3.JobsMadeEasy.availability.model;

import Group3.JobsMadeEasy.availability.dao.AvailabilityDaoImpTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvailabilityUserNameTest {
    AvailabilityDaoImpTest availabilityDaoImpTest = new AvailabilityDaoImpTest();
    AvailabilityUserName availabilityUserName=new AvailabilityUserName(availabilityDaoImpTest);
    @Test
    public void tesAvailabilityUserNameClass() {
        AvailabilityUserName availabilityUserName = Mockito.spy(new AvailabilityUserName());
        availabilityUserName.getFirstName();
        availabilityUserName.getLastName();
        availabilityUserName.getAvailableDays();
        availabilityUserName.getAvailableHours();
        availabilityUserName.getUserId();

        availabilityUserName.setFirstName("Pranay");
        availabilityUserName.setLastName("Raycha");
        availabilityUserName.setAvailableDays(1);
        availabilityUserName.setAvailableHours("Monday 5");
        availabilityUserName.setUserId(1);

        assertEquals(availabilityUserName.getFirstName(), "Pranay");
        assertEquals(availabilityUserName.getLastName(), "Raycha");
        assertEquals(availabilityUserName.getAvailableDays(), 1);
        assertEquals(availabilityUserName.getAvailableHours(), "Monday 5");
        assertEquals(availabilityUserName.getUserId(), 1);
    }
}
