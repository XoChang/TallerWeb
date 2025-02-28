package Group3.JobsMadeEasy.authentication.user.dao.login;

import Group3.JobsMadeEasy.authentication.user.model.Login;
import Group3.JobsMadeEasy.authentication.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class UserLoginDaoImpTest implements IUserLoginDao {

    private final List<User> mockDB;

    public UserLoginDaoImpTest() {
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
    public String checkLoginDetails(Login login) {
        ListIterator<User> iterator = mockDB.listIterator();
        while (iterator.hasNext()) {
            User currentRole = iterator.next();
            if (currentRole.getEmailId() == login.getEmailId() && currentRole.getPassword().equals(login.getPassword())) {
                return "login";
            }
        }
        return "";
    }

    @Override
    public String logout() {
        return "logout";
    }
}
