package Group3.JobsMadeEasy.authentication.user.dao.registration;

import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IUserRegistrationDao {
    String createUser(User user) throws JobsMadeEasyException;
    Optional<User> getUserById(int id) throws SQLException, JobsMadeEasyException;
    List<User> getUsers() throws JobsMadeEasyException, SQLException;
    boolean deleteUserById(int id) throws JobsMadeEasyException;
}
