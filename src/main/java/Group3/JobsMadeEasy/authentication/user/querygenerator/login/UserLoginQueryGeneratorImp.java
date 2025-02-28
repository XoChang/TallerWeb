package Group3.JobsMadeEasy.authentication.user.querygenerator.login;

import Group3.JobsMadeEasy.authentication.user.model.Login;
import org.springframework.stereotype.Component;

import static Group3.JobsMadeEasy.authentication.role.querygenerator.RoleConstant.ROLE_ID_COLUMN;
import static Group3.JobsMadeEasy.authentication.user.querygenerator.login.UserLoginConstant.*;

/**
 * @description: It will generate all the sql queries for login operations.
 */
@Component
public class UserLoginQueryGeneratorImp implements IUserLoginQueryGenerator {

    private static UserLoginQueryGeneratorImp obj;

    private UserLoginQueryGeneratorImp() {
    }

    public static UserLoginQueryGeneratorImp getInstance() {
        if (obj == null) {
            obj = new UserLoginQueryGeneratorImp();
        }
        return obj;
    }

    /**
     * @param login: login model properties
     * @return It will return login query for the user.
     */
    @Override
    public String checkLoginDetails(Login login) {
        return "SELECT * FROM "+ USER_TABLE  + " INNER JOIN " + ROLE_TABLE + " ON " + USER_TABLE + "." +
                ROLE_ID_COLUMN + " = " + ROLE_TABLE + "." + ROLE_ID_COLUMN +
                " WHERE "+ USER_EMAIL_ID_COLUMN + " = " + "\""+ login.getEmailId()
                +"\"" + " AND " + USER_PASSWORD_COLUMN + " = " + "\"" + login.getPassword()
                +"\";";
    }
}
