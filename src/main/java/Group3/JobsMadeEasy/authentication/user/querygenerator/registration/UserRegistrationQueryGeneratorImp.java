package Group3.JobsMadeEasy.authentication.user.querygenerator.registration;

import Group3.JobsMadeEasy.authentication.user.model.User;
import org.springframework.stereotype.Component;
import static Group3.JobsMadeEasy.authentication.user.querygenerator.registration.UserRegistrationConstant.*;

/**
 * @description: It will generate all the sql queries for user operations.
 */
@Component
public class UserRegistrationQueryGeneratorImp implements IUserRegistrationQueryGenerator {

    private static UserRegistrationQueryGeneratorImp obj;

    private UserRegistrationQueryGeneratorImp() {
    }

    public static UserRegistrationQueryGeneratorImp getInstance() {
        if (obj == null) {
            obj = new UserRegistrationQueryGeneratorImp();
        }
        return obj;
    }

    /**
     * @param user: role model properties
     * @return It will return insert query for the user.
     */
    @Override
    public String createUser(User user) {
        return "INSERT INTO "+ USER_TABLE +
                " (" +USER_ID_COLUMN+ "," + USER_FIRST_NAME_COLUMN + "," +
                 USER_LAST_NAME_COLUMN +"," + USER_PHONE_NUMBER_COLUMN + "," +
                USER_EMAIL_ID_COLUMN + "," + USER_PASSWORD_COLUMN + "," + USER_CITY_COLUMN + "," +
                USER_PROVINCE_COLUMN + "," + USER_ADDRESS_COLUMN + "," + USER_POSTAL_CODE_COLUMN + "," +
                USER_ROLE_ID_COLUMN + "," + USER_IS_EMPLOYEE_COLUMN + "," + USER_IS_APPROVED_COLUMN + ")" +
                " VALUES (" +
                "\"" + user.getUserId() + "\"," +
                "\"" + user.getFirstName() + "\"," +
                "\"" + user.getLastName() + "\"," +
                "\"" + user.getPhoneNumber() + "\"," +
                "\"" + user.getEmailId() + "\"," +
                "\"" + user.getPassword() + "\"," +
                "\"" + user.getCity() + "\"," +
                "\"" + user.getProvince() + "\"," +
                "\"" + user.getAddress() + "\"," +
                "\"" + user.getPostalCode() + "\"," +
                "\"" + user.getRoleId() + "\"," +
                 user.isEmployee() + "," +
                 user.isApproved()  +
                ");";
    }

    /**
     * @param id: int user_id
     * @return It will return get by id query for user.
     */
    @Override
    public String getUserById(int id) {
        return "SELECT * FROM "+USER_TABLE+" WHERE "+USER_ID_COLUMN+" = " + id + ";";
    }

    /**
     * @return It will return list query to get all users.
     */
    @Override
    public String getUsers() {
        return "SELECT * FROM " + USER_TABLE +";";
    }

    /**
     * @param id
     * @return It will return delete by id query for user.
     */
    @Override
    public String deleteUserById(int id) {
        return "DELETE FROM "+USER_TABLE+" WHERE "+USER_ID_COLUMN+" = " + id + ";";
    }
}
