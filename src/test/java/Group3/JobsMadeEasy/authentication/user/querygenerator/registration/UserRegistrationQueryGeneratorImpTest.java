package Group3.JobsMadeEasy.authentication.user.querygenerator.registration;

import Group3.JobsMadeEasy.authentication.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserRegistrationQueryGeneratorImpTest {

    @Test
    public void createUserTest() {
        User user = new User(1, "Deep", "Dave", "789678456", "deep@dal.ca", "test@123", "halifax", "NS", "Dalhousie",
                "B2k 2Z2", 1, false, false);
        UserRegistrationQueryGeneratorImp userRegistrationDaoImp = UserRegistrationQueryGeneratorImp.getInstance();
        String queryGenerated = userRegistrationDaoImp.createUser(user);
        String queryExpected = "INSERT INTO user (userId,firstName,lastName,phoneNumber,emailId,password,city,province,address,postalCode,roleId,isEmployee,isApproved) VALUES " +
                "(\"1\",\"Deep\",\"Dave\",\"789678456\",\"deep@dal.ca\",\"test@123\",\"halifax\",\"NS\",\"Dalhousie\",\"B2k 2Z2\",\"1\",false,false);";
        Assertions.assertEquals(queryExpected, queryGenerated, "query not match");
    }

    @Test
    public void getUserByIdTest() {
        int userId = 1;
        UserRegistrationQueryGeneratorImp userRegistrationDaoImp = UserRegistrationQueryGeneratorImp.getInstance();
        String queryGenerated = userRegistrationDaoImp.getUserById(userId);
        String queryExpected = "SELECT * FROM user WHERE userId = 1;";
        Assertions.assertEquals(queryExpected, queryGenerated, "query not match");
    }

    @Test
    public void viewAllUsersTest() {
        UserRegistrationQueryGeneratorImp userRegistrationDaoImp = UserRegistrationQueryGeneratorImp.getInstance();
        String queryGenerated = userRegistrationDaoImp.getUsers();
        String queryExpected = "SELECT * FROM user;";
        Assertions.assertEquals(queryExpected, queryGenerated, "query not match");
    }

    @Test
    public void deleteRoleByIdTest() {
        int userId = 1;
        UserRegistrationQueryGeneratorImp userRegistrationDaoImp = UserRegistrationQueryGeneratorImp.getInstance();
        String queryGenerated = userRegistrationDaoImp.deleteUserById(userId);
        String queryExpected = "DELETE FROM user WHERE userId = 1;";
        Assertions.assertEquals(queryExpected, queryGenerated, "query not match");
    }
}
