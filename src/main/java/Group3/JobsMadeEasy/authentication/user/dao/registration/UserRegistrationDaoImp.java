package Group3.JobsMadeEasy.authentication.user.dao.registration;

import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.authentication.user.querygenerator.registration.IUserRegistrationQueryGenerator;
import Group3.JobsMadeEasy.database.dao.DatabaseSetup;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import static Group3.JobsMadeEasy.authentication.user.controller.registration.UserRegistrationControllerConstant.INDEX;
import static Group3.JobsMadeEasy.authentication.user.controller.registration.UserRegistrationControllerConstant.REGISTER;

/**
 * @description: It will handle all the database layer queries for the user .
 */
@Component
public class UserRegistrationDaoImp implements IUserRegistrationDao {
    private final IUserRegistrationQueryGenerator userRegistrationQueryGenerator;
    private final DatabaseSetup databaseSetup;
    private final Connection connection;
    private final Statement statement;
    private final HttpSession session;

    public UserRegistrationDaoImp(IUserRegistrationQueryGenerator userRegistrationQueryGenerator,
                                  DatabaseSetup databaseSetup, HttpSession session) throws SQLException, IOException,
            ClassNotFoundException {
        this.userRegistrationQueryGenerator = userRegistrationQueryGenerator;
        this.databaseSetup = databaseSetup;
        this.connection = databaseSetup.getConnectionObject();
        this.statement = connection.createStatement();
        this.session = session;
    }

    /**
     *
     * @param user
     * @return redirect user after completing register
     * @throws JobsMadeEasyException
     */
    @Override
    public String createUser(User user) throws JobsMadeEasyException {
        try {
            String createUserQuery = userRegistrationQueryGenerator.createUser(user);
            int updatedRows = statement.executeUpdate(createUserQuery, Statement.RETURN_GENERATED_KEYS);
            if (updatedRows > 0) {
                return INDEX;
            }
        } catch (
                SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
        }
        return REGISTER;
    }

    /**
     *
     * @param id
     * @return return user of same id
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @Override
    public Optional<User> getUserById(int id) throws SQLException, JobsMadeEasyException {
        ResultSet rs = null;
        try {
            String getUserByIdQuery = userRegistrationQueryGenerator.getUserById(id);
            rs = statement.executeQuery(getUserByIdQuery);
            if (rs.next()) {
                return Optional.of(new User(
                        rs.getInt("userId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("phoneNumber"),
                        rs.getString("emailId"),
                        rs.getString("password"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("address"),
                        rs.getString("postalCode"),
                        rs.getInt("roleId"),
                        rs.getBoolean("isEmployee"),
                        rs.getBoolean("isApproved")
                ));
            }
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
            rs.close();
        }
        return null;
    }

    /**
     *
     * @return list of users
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @Override
    public List<User> getUsers() throws JobsMadeEasyException, SQLException {
        ResultSet rs = null;
        try {
            String getUsersQuery = userRegistrationQueryGenerator.getUsers();
            rs = statement.executeQuery(getUsersQuery);
            List<User> users = new LinkedList<>();
            while (rs.next()) {
                int i = rs.getInt("userId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNumber = rs.getString("phoneNumber");
                String emailId = rs.getString("emailId");
                String password = rs.getString("password");
                String city = rs.getString("city");
                String province = rs.getString("province");
                String address = rs.getString("address");
                String postalCode = rs.getString("postalCode");
                int roleId = rs.getInt("roleId");
                boolean isEmployee = rs.getBoolean("isEmployee");
                boolean isApproved = rs.getBoolean("isApproved");
                User user = new User(i, firstName, lastName, phoneNumber, emailId, password, city, province, address,
                        postalCode, roleId, isEmployee, isApproved);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
            rs.close();
        }
    }

    /**
     *
     * @param id
     * @return true or false according to delete operation success or failure
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @Override
    public boolean deleteUserById(int id) throws JobsMadeEasyException {
        try {
            String deleteUserByIdQuery = userRegistrationQueryGenerator.deleteUserById(id);
            statement.executeUpdate(deleteUserByIdQuery);
            return true;
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
        }
    }
}
