package Group3.JobsMadeEasy.authentication.role.model;

import Group3.JobsMadeEasy.authentication.role.dao.IRoleDao;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @description: It will handle all the properties of role and business logic for it.
 */
@Component
public class Role {
    private int roleId;
    private String roleName;
    private IRoleDao roleDao;
    public Role(){}
    public Role(IRoleDao roleDao){
        this.roleDao = roleDao;
    }
    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @param role: role model properties
     * @return It will return true/false if role entry has been created in db.
     * @throws JobsMadeEasyException
     */
    public boolean createRole(Role role) throws JobsMadeEasyException {
        if (role == null) {
            throw new JobsMadeEasyException("role details not found..");
        }
        return roleDao.createRole(role);
    }

    /**
     * @param id: int role_id
     * @return It will return Role of the same id.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    public Optional<Role> getRoleById(int id) throws SQLException, JobsMadeEasyException {
        if (id == 0) {
            throw new JobsMadeEasyException("No role found in DB");
        }
        return roleDao.getRole(id);
    }

    /**
     * @return It will return list of Role.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    public List<Role> getAllRoles() throws SQLException, JobsMadeEasyException {
        return roleDao.viewAllRoles();
    }

    /**
     *
     * @param id
     * @return it will return true/false if role with same id has been deleted from db.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    public boolean deleteRoleById(int id) throws SQLException, JobsMadeEasyException {
        if (this.getRoleById(id).isPresent())
        {
            return roleDao.deleteRoleById(id);
        }else
        {
            throw new JobsMadeEasyException("No role found in DB");
        }
    }
}
