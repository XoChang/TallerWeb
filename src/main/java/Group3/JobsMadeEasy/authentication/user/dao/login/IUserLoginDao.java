package Group3.JobsMadeEasy.authentication.user.dao.login;

import Group3.JobsMadeEasy.authentication.user.model.Login;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;

import java.sql.SQLException;

public interface IUserLoginDao {
    String checkLoginDetails(Login login) throws JobsMadeEasyException, SQLException;

    String logout();

}
