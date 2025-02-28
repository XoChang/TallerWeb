package Group3.JobsMadeEasy.availability.querygenerator;

import Group3.JobsMadeEasy.availability.model.Availability;
import org.springframework.stereotype.Component;

import static Group3.JobsMadeEasy.authentication.user.querygenerator.registration.UserRegistrationConstant.USER_ID_COLUMN;
import static Group3.JobsMadeEasy.authentication.user.querygenerator.registration.UserRegistrationConstant.USER_TABLE;
import static Group3.JobsMadeEasy.availability.querygenerator.AvailabilityConstant.*;

/**
 * @desctiption: this class generates queries that needs b executed.
 */
@Component
public class AvailabilityQueryGeneratorImp implements IAvailabilityQueryGenerator {

    private static AvailabilityQueryGeneratorImp obj;



    private AvailabilityQueryGeneratorImp() {
    }

    public static AvailabilityQueryGeneratorImp getInstance() {
        if (obj == null) {
            obj = new AvailabilityQueryGeneratorImp();
        }
        return obj;
    }

    /**
     *
     * @param availability: it is model availability which contains value for field
     * @return: it will return generated string query.
     */
    @Override
    public String createAvailability(Availability availability) {
        return "INSERT INTO " + AVAILABILITY_TABLE +
                " (" + AVAILABILITY_ID_COLUMN + "," +
                AVAILABLE_DAYS_COLUMN + "," +
                AVAILABLE_HOURS_COLUMN + "," +
                USER_ID_COLUMN + ")" +
                "VALUES (" +
                "\"" + availability.getAvailabilityId() + "\"," +
                "\"" + availability.getAvailableDays() + "\"," +
                "\"" + availability.getAvailableHours() + "\"," +
                "\"" + availability.getUserId() + "\"" +
                ");";
    }

    /**
     *
     * @return: it will return string query to display all availabilities along with users first name and last name.
     */
    @Override
    public String viewAllAvailability() {
        return "SELECT u.userId, u.firstName, u.lastName, a.availableDays, a.availableHours FROM " + USER_TABLE +
                " u INNER JOIN " + AVAILABILITY_TABLE + " a ON u.userId=a.userId;";

    }

    /**
     *
     * @param id: it is user id of current logged-in user
     * @return: it will return string query to view availability of logged-in user
     */

    @Override
    public String viewAvailabilityById(int id) {
        return "SELECT * FROM " + AVAILABILITY_TABLE + " WHERE " + USER_ID_COLUMN + " = " + id + ";";
    }

    /**
     *
     * @param id: it is user id of current logged-in user
     * @return:  it will return string query to delete availability of specific user.
     */

    @Override
    public String deleteAvailabilityById(int id) {
        return "DELETE FROM " + AVAILABILITY_TABLE + " WHERE " + AVAILABILITY_ID_COLUMN + " = " + id + ";";

    }
}
