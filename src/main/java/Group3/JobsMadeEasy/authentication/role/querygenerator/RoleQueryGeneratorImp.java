package Group3.JobsMadeEasy.authentication.role.querygenerator;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import org.springframework.stereotype.Component;
import static Group3.JobsMadeEasy.authentication.role.querygenerator.RoleConstant.*;

/**
 * @description: It will generate all the sql queries for role operations.
 */
@Component
public class RoleQueryGeneratorImp implements IRoleQueryGenerator {

    private static RoleQueryGeneratorImp obj;

    private RoleQueryGeneratorImp() {
    }

    public static RoleQueryGeneratorImp getInstance() {
        if (obj == null) {
            obj = new RoleQueryGeneratorImp();
        }
        return obj;
    }

    /**
     * @param role: role model properties
     * @return It will return insert query for the role.
     */
    @Override
    public String createRole(Role role) {
        return "INSERT INTO "+ ROLE_TABLE+
                " (" + ROLE_ID_COLUMN + "," +
                ROLE_NAME_COLUMN+ ")" +
                "VALUES (" +
                "\"" + role.getRoleId() + "\"," +
                "\"" + role.getRoleName() + "\"" +
                ");";
    }

    /**
     * @param id: int role_id
     * @return It will return get by id query for role.
     */
    @Override
    public String getRole(int id) {
        return "SELECT * FROM "+ROLE_TABLE+" WHERE "+ROLE_ID_COLUMN+" = " + id + ";";
    }

    /**
     * @return It will return list query to get all roles.
     */
    @Override
    public String viewAllRoles() {
        return "SELECT * FROM " + ROLE_TABLE + ";";

    }

    /**
     * @param id
     * @return It will return delete by id query for role.
     */
    @Override
    public String deleteRoleById(int id) {
        return "DELETE FROM "+ROLE_TABLE+" WHERE "+ROLE_ID_COLUMN+" = " + id + ";";
    }
}
