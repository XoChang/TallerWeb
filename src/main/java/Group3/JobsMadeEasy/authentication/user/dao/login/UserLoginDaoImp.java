package Group3.JobsMadeEasy.authentication.user.dao.login;

import Group3.JobsMadeEasy.authentication.role.querygenerator.IRoleQueryGenerator;
import Group3.JobsMadeEasy.authentication.user.model.Login;
import Group3.JobsMadeEasy.authentication.user.querygenerator.login.IUserLoginQueryGenerator;
import Group3.JobsMadeEasy.database.dao.DatabaseSetup;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @description: It will handle all the database layer queries for the login .
 */
@Component
public class UserLoginDaoImp implements IUserLoginDao{

    private final IUserLoginQueryGenerator userLoginQueryGenerator;
    private final IRoleQueryGenerator roleQueryGenerator;
    private final DatabaseSetup databaseSetup;
    private final Connection connection;
    private final Statement statement;
    private final HttpSession session;

    public UserLoginDaoImp(IUserLoginQueryGenerator userLoginQueryGenerator, IRoleQueryGenerator roleQueryGenerator, DatabaseSetup databaseSetup, HttpSession session) throws SQLException, IOException, ClassNotFoundException {
        this.userLoginQueryGenerator = userLoginQueryGenerator;
        this.roleQueryGenerator = roleQueryGenerator;
        this.databaseSetup = databaseSetup;
        this.connection = databaseSetup.getConnectionObject();
        this.statement = connection.createStatement();
        this.session = session;
    }

    /**
     *
     * @param login
     * @return it will check user's email and password for authentication
     * @throws SQLException
     * @throws JobsMadeEasyException
     */
    @Override
    public String checkLoginDetails(Login login) throws JobsMadeEasyException, SQLException {
        ResultSet rs = null, resultSet;
        login.setEmailId(login.getEmailId());
        login.setPassword(login.getPassword());
        try {
            String checkLoginDetailsQuery = userLoginQueryGenerator.checkLoginDetails(login);
            rs = statement.executeQuery(checkLoginDetailsQuery);
            if(rs.next()){
                session.setAttribute("currentId", rs.getString("userId"));
                session.setAttribute("currentName", rs.getString("firstName"));
                String getRoleByIdQuery = roleQueryGenerator.getRole(rs.getInt("roleId"));
                resultSet = statement.executeQuery(getRoleByIdQuery);
                if(resultSet.next()){
                    session.setAttribute("role",resultSet.getString("roleName"));
                }
                return (String) session.getAttribute("role");
            }
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        }finally {
            databaseSetup.closeDatabaseConnection();
            rs.close();
        }
        return null;
    }

    /**
     *
     * @return it will perform logout and clean all the session values
     */
    @Override
    public String logout() {
        session.removeAttribute("role");
        session.removeAttribute("currentId");
        session.removeAttribute("currentName");
        return "index";
    }

}
