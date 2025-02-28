package Group3.JobsMadeEasy.authentication.role.dao;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.authentication.role.querygenerator.IRoleQueryGenerator;
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

/**
 * @description: It will handle all the database layer queries for the role.
 */
@Component
public class RoleDaoImp implements IRoleDao {

    private final IRoleQueryGenerator roleQueryGenerator;
    private final DatabaseSetup databaseSetup;
    private final Connection connection;
    private final Statement statement;
    private final HttpSession session;

    public RoleDaoImp(IRoleQueryGenerator roleQueryGenerator, DatabaseSetup databaseSetup, HttpSession session) throws SQLException, IOException, ClassNotFoundException {
        this.roleQueryGenerator = roleQueryGenerator;
        this.databaseSetup = databaseSetup;
        this.connection = databaseSetup.getConnectionObject();
        this.statement = connection.createStatement();
        this.session = session;
    }

    /**
     * @param role: role model properties
     * @return It will return true/false if role entry has been created in db.
     * @throws JobsMadeEasyException
     */
    @Override
    public boolean createRole(Role role) throws JobsMadeEasyException {
        role.setRoleId(role.getRoleId());
        role.setRoleName(role.getRoleName());
        try {
            String createRoleQuery = roleQueryGenerator.createRole(role);
            int updatedRows = statement.executeUpdate(createRoleQuery, Statement.RETURN_GENERATED_KEYS);
            if (updatedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
        }
        return true;
    }

    /**
     * @param id: int role_id
     * @return It will return Role of the same id.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @Override
    public Optional<Role> getRole(int id) throws JobsMadeEasyException, SQLException {
        ResultSet rs = null;
        try {
            String getRoleByIdQuery = roleQueryGenerator.getRole(id);
            rs = statement.executeQuery(getRoleByIdQuery);
            if (rs.next()) {
                return Optional.of(new Role(
                        rs.getInt("roleId"),
                        rs.getString("roleName")
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
     * @return It will return list of Role.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @Override
    public List<Role> viewAllRoles() throws JobsMadeEasyException, SQLException {
        ResultSet rs = null;
        try {
            String getRolesQuery = roleQueryGenerator.viewAllRoles();
            rs = statement.executeQuery(getRolesQuery);
            List<Role> roles = new LinkedList<>();
            while (rs.next()) {
                int i = rs.getInt("roleId");
                String roleName = rs.getString("roleName");
                Role role = new Role(i, roleName);
                roles.add(role);
            }
            return roles;
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
     * @return it will return true/false if role with same id has been deleted from db.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @Override
    public boolean deleteRoleById(int id) throws JobsMadeEasyException {
        try {
            String deleteRoleByIdQuery = roleQueryGenerator.deleteRoleById(id);
            statement.executeUpdate(deleteRoleByIdQuery);
            return true;
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
        }
    }
}
