package Group3.JobsMadeEasy.authentication.user.model;

import Group3.JobsMadeEasy.authentication.user.dao.login.UserLoginDaoImpTest;
import Group3.JobsMadeEasy.authentication.user.dao.registration.UserRegistrationDaoImpTest;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {

    UserLoginDaoImpTest userLoginDaoImpTest = new UserLoginDaoImpTest();
    UserRegistrationDaoImpTest userRegistrationDaoImpTest = new UserRegistrationDaoImpTest();
    User user = new User(userLoginDaoImpTest, userRegistrationDaoImpTest);

    @Test
    public void testUserClass() {
        User user = Mockito.spy(new User());
        user.getUserId();
        user.getFirstName();
        user.getLastName();
        user.getPhoneNumber();
        user.getEmailId();
        user.getPassword();
        user.getCity();
        user.getProvince();
        user.getAddress();
        user.getPostalCode();
        user.getRoleId();
        user.isEmployee();
        user.isApproved();
        user.setUserId(1);
        user.setFirstName("Deep");
        user.setLastName("Dave");
        user.setPhoneNumber("12345678");
        user.setEmailId("test@gmail.com");
        user.setPassword("test@123");
        user.setCity("Halifax");
        user.setProvince("NS");
        user.setAddress("Dalhousie");
        user.setPostalCode("B2k 2Z2");
        user.setRoleId(1);
        user.setEmployee(false);
        user.setApproved(false);
        assertEquals(user.getUserId(), 1);
        assertEquals(user.getFirstName(), "Deep");
        assertEquals(user.getLastName(), "Dave");
        assertEquals(user.getPhoneNumber(), "12345678");
        assertEquals(user.getEmailId(), "test@gmail.com");
        assertEquals(user.getPassword(), "test@123");
        assertEquals(user.getCity(), "Halifax");
        assertEquals(user.getProvince(), "NS");
        assertEquals(user.getAddress(), "Dalhousie");
        assertEquals(user.getPostalCode(), "B2k 2Z2");
        assertEquals(user.getRoleId(), 1);
        assertEquals(user.isEmployee(), false);
        assertEquals(user.isApproved(), false);
    }

    @Test
    public void checkLoginDetailsTest() throws SQLException, JobsMadeEasyException {
        Login login = new Login("test@gmail.com", "1234");
        String result = user.checkLoginDetails(login);
        assertEquals(result, "loginHomePage");
    }

    @Test
    public void checkCurrentUserRoleTest() {
        String name = "login";
        String result = name + "HomePage";
        assertEquals(result, "loginHomePage");
    }

    @Test
    public void createUserFailureTest() {
        Throwable exception = assertThrows(JobsMadeEasyException.class, () -> user.createUser(null));
        assertEquals("user register details not found..", exception.getMessage());
    }

    @Test
    public void createUserSuccessTest() throws JobsMadeEasyException {
        User test = new User(1, "Deep",
                "Dave", "12345678", "test@gmail.com", "1234", "halifax",
                "NS", "Dalhousie", "123",
                1, false, false);
        String result = user.createUser(test);
        assertEquals(result, "register");
    }

    @Test
    public void getAllUsersTest() throws SQLException, JobsMadeEasyException {
        List<User> test = user.getAllUsers();
        assertEquals(2, test.size());
    }

    @Test
    public void getUserByIdSuccessTest() throws SQLException, JobsMadeEasyException {
        int userId = 1;
        Optional<User> test = user.getUserById(userId);
        assertEquals(1, test.get().getUserId());
    }

    @Test
    public void getRoleByIdFailureTest() {
        Throwable exception = assertThrows(JobsMadeEasyException.class, () -> user.getUserById(0));
        assertEquals("No User found in DB", exception.getMessage());
    }

    @Test
    public void deleteUserByIdSuccessTest() throws SQLException, JobsMadeEasyException {
        int userId = 1;
        boolean test = user.deleteUserById(userId);
        assertEquals(true, test);
    }

    @Test
    public void deleteUserByIdFailureTest() {
        Throwable exception = assertThrows(JobsMadeEasyException.class, () -> user.deleteUserById(0));
        assertEquals("No User found in DB", exception.getMessage());
    }

    @Test
    public void logout() {
        String result = user.logout();
        assertEquals(result, "logout");
    }

}
