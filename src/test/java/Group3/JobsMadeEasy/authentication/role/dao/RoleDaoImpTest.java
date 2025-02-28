package Group3.JobsMadeEasy.authentication.role.dao;

import Group3.JobsMadeEasy.authentication.role.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class RoleDaoImpTest implements IRoleDao {

    private final List<Role> mockDB;

    public RoleDaoImpTest() {
        mockDB = new ArrayList<>();
        mockDB.add(new Role(1, "admin"));
        mockDB.add(new Role(2, "hr"));
    }

    @Override
    public boolean createRole(Role role) {
        this.mockDB.add(role);
        ListIterator<Role> iterator = mockDB.listIterator();
        while (iterator.hasNext()) {
            Role currentRole = iterator.next();
            if (currentRole.getRoleId() == role.getRoleId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Role> getRole(int id) {
        ListIterator<Role> iterator = mockDB.listIterator();
        while (iterator.hasNext()) {
            Role currentRole = iterator.next();
            if (currentRole.getRoleId() == id) {
                return Optional.of(currentRole);
            }
        }
        return null;
    }

    @Override
    public List<Role> viewAllRoles() {
        return mockDB;
    }

    @Override
    public boolean deleteRoleById(int id) {
        ListIterator<Role> iterator = mockDB.listIterator();
        while (iterator.hasNext()) {
            Role currentRole = iterator.next();
            if (currentRole.getRoleId() == id) {
                return true;
            }
        }
        return false;
    }
}
