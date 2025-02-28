package Group3.JobsMadeEasy.authentication.user.controller.registration;

import Group3.JobsMadeEasy.authentication.user.dao.login.IUserLoginDao;
import Group3.JobsMadeEasy.authentication.user.dao.registration.IUserRegistrationDao;
import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static Group3.JobsMadeEasy.authentication.user.controller.registration.UserRegistrationControllerConstant.*;

/**
 * @description: It will handle all the registration related request.
 */
@Controller
public class UserRegistrationController {

    private final User user;

    public UserRegistrationController(IUserLoginDao userLoginDao, IUserRegistrationDao userRegistrationDao){
        this.user = new User(userLoginDao,userRegistrationDao);
    }

    /**
     *
     * @return index home page
     */
    @GetMapping("/index")
    public String home() {
        return INDEX;
    }

    /**
     *
     * @param model
     * @return register page
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return REGISTER;
    }

    /**
     *
     * @param user
     * @return redirect user after completing registration
     * @throws JobsMadeEasyException
     */
    @PostMapping("/auth/register")
    public String createUser(@ModelAttribute User user) throws JobsMadeEasyException {
        return this.user.createUser(user);
    }

    /**
     *
     * @param id
     * @return return user of same id
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @GetMapping("/get_user_by_id/{id}")
    public Optional<User> getUserById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        return user.getUserById(id);
    }

    /**
     *
     * @return list of users
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @GetMapping("/get_all_users")
    public List<User> getAllUsers() throws JobsMadeEasyException, SQLException {
        return user.getAllUsers();
    }

    /**
     *
     * @param id
     * @return true or false according to delete operation success or failure
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @DeleteMapping("/delete_user_by_id/{id}")
    public boolean deleteUserById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        return user.deleteUserById(id);
    }

    /**
     *
     * @param model
     * @return redirect page to view all users data
     */
    @GetMapping("/view_all_users")
    public String viewAllUsers(Model model) {
        List<User> users;
        try {
            users = getAllUsers();
        } catch (JobsMadeEasyException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("users", users);
        return VIEW_ALL_USERS;
    }

    /**
     *
     * @param model
     * @param id
     * @return redirect page to view user data by id
     * @throws SQLException
     * @throws JobsMadeEasyException
     */
    @GetMapping("/view_user_by_id/{id}")
    public String viewUserById(Model model, @PathVariable int id) throws SQLException, JobsMadeEasyException {

        Optional<User> users = getUserById(id);
        model.addAttribute("users", users);
        return VIEW_USER_BY_ID;
    }

    /**
     *
     * @param model
     * @return redirect page of delete user by id
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @GetMapping("/delete_user_by_id")
    public String deleteUserByUserId(Model model) throws JobsMadeEasyException, SQLException {
        List<User> user = getAllUsers();
        model.addAttribute("user", user);
        return DELETE_USER_BY_ID;
    }
}
