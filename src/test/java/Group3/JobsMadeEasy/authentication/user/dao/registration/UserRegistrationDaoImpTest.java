package Group3.JobsMadeEasy.authentication.user.dao.registration;

import Group3.JobsMadeEasy.authentication.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class UserRegistrationDaoImpTest implements IUserRegistrationDao {

    private final List<User> mockDB;

    public UserRegistrationDaoImpTest() {
        mockDB = new ArrayList<>();
        mockDB.add(new User(1, "Deep",
                "Dave", "12345678", "test@gmail.com", "1234", "halifax",
                "NS", "Dalhousie", "123",
                1, false, false));
        mockDB.add(new User(2, "Deep",
                "Dave", "12345678", "test@gmail.com", "1234", "halifax",
                "NS", "Dalhousie", "123",
                1, false, false));
    }

    @Override
    public String createUser(User user) {
        this.mockDB.add(user);
        ListIterator<User> iterator = mockDB.listIterator();
        while (iterator.hasNext()) {
            User currentRole = iterator.next();
            if (currentRole.getUserId() == user.getUserId()) {
                return "register";
            }
        }
        return "";
    }

    @Override
    public Optional<User> getUserById(int id) {
        ListIterator<User> iterator = mockDB.listIterator();
        while (iterator.hasNext()) {
            User currentRole = iterator.next();
            if (currentRole.getUserId() == id) {
                return Optional.of(currentRole);
            }
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return mockDB;
    }

    @Override
    public boolean deleteUserById(int id) {
        ListIterator<User> iterator = mockDB.listIterator();
        while (iterator.hasNext()) {
            User currentRole = iterator.next();
            if (currentRole.getUserId() == id) {
                return true;
            }
        }
        return false;
    }
}
