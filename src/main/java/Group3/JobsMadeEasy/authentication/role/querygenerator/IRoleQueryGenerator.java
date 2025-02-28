package Group3.JobsMadeEasy.authentication.role.querygenerator;

import Group3.JobsMadeEasy.authentication.role.model.Role;

public interface IRoleQueryGenerator {

    String createRole(Role role);
    String getRole(int id);
    String viewAllRoles();
    String deleteRoleById(int id);
}
