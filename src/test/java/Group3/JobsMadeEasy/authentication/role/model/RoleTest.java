package Group3.JobsMadeEasy.authentication.role.model;

import Group3.JobsMadeEasy.authentication.role.dao.RoleDaoImpTest;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoleTest {

    RoleDaoImpTest roleDaoImpTest = new RoleDaoImpTest();
    Role role = new Role(roleDaoImpTest);

    @Test
    public void testRoleClass() {
        Role role = Mockito.spy(new Role());
        role.getRoleId();
        role.getRoleName();
        role.setRoleId(1);
        role.setRoleName("ADMIN");
        assertEquals(role.getRoleId(), 1);
        assertEquals(role.getRoleName(), "ADMIN");
    }

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createRoleSuccessTest() throws JobsMadeEasyException {
        Role test = new Role(3, "employee");
        boolean result = role.createRole(test);
        assertEquals(result, true);
    }

    @Test()
    public void createRoleFailureTest() {
        Throwable exception = assertThrows(JobsMadeEasyException.class, () -> role.createRole(null));
        assertEquals("role details not found..", exception.getMessage());

    }

    @Test
    public void getAllRolesTest() throws SQLException, JobsMadeEasyException {
        List<Role> test = role.getAllRoles();
        assertEquals(2, test.size());
    }

    @Test
    public void getRoleByIdSuccessTest() throws SQLException, JobsMadeEasyException {
        int roleId = 1;
        Optional<Role> test = role.getRoleById(roleId);
        assertEquals(1, test.get().getRoleId());
    }

    @Test
    public void getRoleByIdFailureTest() {
        Throwable exception = assertThrows(JobsMadeEasyException.class, () -> role.getRoleById(0));
        assertEquals("No role found in DB", exception.getMessage());
    }

    @Test
    public void deleteRoleByIdSuccessTest() throws SQLException, JobsMadeEasyException {
        int roleId = 1;
        boolean test = role.deleteRoleById(roleId);
        assertEquals(true, test);
    }

    @Test
    public void deleteRoleByIdFailureTest() {
        Throwable exception = assertThrows(JobsMadeEasyException.class, () -> role.deleteRoleById(0));
        assertEquals("No role found in DB", exception.getMessage());
    }
}
