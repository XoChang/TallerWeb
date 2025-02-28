package Group3.JobsMadeEasy.authentication.user.querygenerator.login;

import Group3.JobsMadeEasy.authentication.user.model.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserLoginQueryGeneratorTest {

    @Test
    public void checkLoginDetailsTest() {
        String email = "A@gmail.com";
        String password = "1234";
        Login login = new Login(email, password);
        UserLoginQueryGeneratorImp userLoginDaoImp = UserLoginQueryGeneratorImp.getInstance();
        String queryGenerated = userLoginDaoImp.checkLoginDetails(login);
        String queryExpected = "SELECT * FROM user INNER JOIN role ON user.roleId = role.roleId WHERE emailId = " +
                "\"" + email + "\"" +
                " AND password = " + "\"" + password + "\"" + ";";
        Assertions.assertEquals(queryExpected, queryGenerated, "query match");
    }
}
