package Group3.JobsMadeEasy.authentication.role.dao;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IRoleDao {
    boolean createRole(Role role) throws JobsMadeEasyException;
    Optional<Role> getRole(int id) throws JobsMadeEasyException, SQLException;
    List<Role> viewAllRoles() throws JobsMadeEasyException, SQLException;
    boolean deleteRoleById(int id) throws JobsMadeEasyException, SQLException;
}
