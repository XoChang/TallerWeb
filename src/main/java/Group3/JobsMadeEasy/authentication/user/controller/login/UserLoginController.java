package Group3.JobsMadeEasy.authentication.user.controller.login;

import Group3.JobsMadeEasy.authentication.user.dao.login.IUserLoginDao;
import Group3.JobsMadeEasy.authentication.user.dao.registration.IUserRegistrationDao;
import Group3.JobsMadeEasy.authentication.user.model.Login;
import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

import static Group3.JobsMadeEasy.authentication.user.controller.login.UserLoginControllerConstant.LOGIN;

/**
 * @description: It will handle all the login related request.
 */
@Controller
public class UserLoginController {

    private final User user;

    public UserLoginController(IUserLoginDao userLoginDao, IUserRegistrationDao userRegistrationDao) {
        this.user = new User(userLoginDao,userRegistrationDao);
    }

    /**
     *
     * @param model
     * @return return login page
     */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        Login login = new Login();
        model.addAttribute("login",login);
        return LOGIN;
    }

    /**
     *
     * @param login
     * @return it will check user's email and password for authentication and redirect on different page
     * @throws SQLException
     * @throws JobsMadeEasyException
     */
    @PostMapping("/auth/login")
    public String loginUser(@ModelAttribute Login login) throws SQLException, JobsMadeEasyException {
        return user.checkLoginDetails(login);
    }

    /**
     *
     * @return it will perform logout and clean all the session values
     */
    @GetMapping("/logout")
    public String logout(){
        return user.logout();
    }
}
