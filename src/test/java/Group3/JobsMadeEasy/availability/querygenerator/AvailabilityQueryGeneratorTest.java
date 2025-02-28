package Group3.JobsMadeEasy.availability.querygenerator;

import Group3.JobsMadeEasy.availability.model.Availability;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AvailabilityQueryGeneratorTest {
    @Test
    public void createAvailabilityTest() {
        Availability availability = new Availability(1,5, "monday 4", 4);
        AvailabilityQueryGeneratorImp availabilityDaoImp = AvailabilityQueryGeneratorImp.getInstance();
        String queryGenerated = availabilityDaoImp.createAvailability(availability);
        String queryExpected = "INSERT INTO availability (availabilityId,availableDays,availableHours,userId)"+
                "VALUES (\"1\",\"5\",\"monday 4\",\"4\");";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }

    @Test
    public void viewAvailabilityByIdTest() {
        int userId = 1;
        AvailabilityQueryGeneratorImp availabilityDaoImp = AvailabilityQueryGeneratorImp.getInstance();
        String queryGenerated = availabilityDaoImp.viewAvailabilityById(userId);
        String queryExpected = "SELECT * FROM availability WHERE userId = " + userId + ";";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }

    @Test
    public void viewAllAvailabilityTest() {
        AvailabilityQueryGeneratorImp availabilityDaoImp = AvailabilityQueryGeneratorImp.getInstance();
        String queryGenerated = availabilityDaoImp.viewAllAvailability();
        String queryExpected = "SELECT u.userId, u.firstName, u.lastName, a.availableDays, a.availableHours FROM user u INNER JOIN availability a ON u.userId=a.userId;";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }

    @Test
    public void deleteAvailabilityByIdTest(){
        int availabilityId = 1;
        AvailabilityQueryGeneratorImp availabilityDaoImp = AvailabilityQueryGeneratorImp.getInstance();
        String queryGenerated = availabilityDaoImp.deleteAvailabilityById(availabilityId);
        String queryExpected = "DELETE FROM availability WHERE availabilityId = " + availabilityId + ";";
        Assertions.assertEquals(queryExpected,queryGenerated,"query match");
    }
}


