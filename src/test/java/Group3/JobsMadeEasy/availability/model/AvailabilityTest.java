package Group3.JobsMadeEasy.availability.model;

import Group3.JobsMadeEasy.availability.dao.AvailabilityDaoImpTest;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AvailabilityTest {
    AvailabilityDaoImpTest availabilityDaoImpTest = new AvailabilityDaoImpTest();

    Availability availability = new Availability(availabilityDaoImpTest);
    @Test
    public void tesAvailabilityClass() {
        Availability availability = Mockito.spy(new Availability());
        availability.getAvailabilityId();
        availability.getAvailableDays();
        availability.getAvailableHours();
        availability.getUserId();

        availability.setAvailabilityId(1);
        availability.setAvailableDays(5);
        availability.setAvailableHours("monday 4");
        availability.setUserId(5);
        assertEquals(availability.getAvailabilityId(), 1);
        assertEquals(availability.getAvailableDays(), 5);
        assertEquals(availability.getAvailableHours(), "monday 4");
        assertEquals(availability.getUserId(), 5);

    }

    @Test
    public void createAvailabilityFailureTest(){
        Throwable exception = assertThrows(JobsMadeEasyException.class, () -> availability.createAvailability(null, 0));
        assertEquals("no such availability found", exception.getMessage());
    }

    @Test
    public void createAvailabilitySuccessTest() throws JobsMadeEasyException {
        Availability test = new Availability(1,2, "Monday 4", 5);

        String result = availability.createAvailability(test, 5);
        assertEquals(result,"availabilityHomePage");
    }

    @Test
    public void getAllAvailabilityTest() throws SQLException, JobsMadeEasyException {
        List<AvailabilityUserName> test = availability.getAllAvailability();
        assertEquals(1,test.size());
    }

    @Test
    public void getUserByIdSuccessTest() throws SQLException, JobsMadeEasyException {
        int userId = 5;
        List<Availability> test  = availability.getMyAvailability(userId);
        assertEquals(1,test.size());
    }

    @Test
    public void getAvailabilityByIdFailureTest() {
        Throwable exception = assertThrows(JobsMadeEasyException.class, () -> availability.getMyAvailability(0));
        assertEquals("No Availability found in DB", exception.getMessage());
    }

    @Test
    public void deleteAvailabilityByIdSuccessTest() throws SQLException, JobsMadeEasyException {
        int availabilityId = 1;
        boolean test = availability.deleteAvailabilityById(availabilityId);
        assertEquals(true,test);
    }

    @Test
    public void deleteUserByIdFailureTest() {
        Throwable exception = assertThrows(JobsMadeEasyException.class, () -> availability.deleteAvailabilityById(0));
        assertEquals(    "No Availability found in DB", exception.getMessage());
    }


}

