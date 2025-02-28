package Group3.JobsMadeEasy.authentication.role.controller;


import Group3.JobsMadeEasy.authentication.role.dao.IRoleDao;
import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static Group3.JobsMadeEasy.authentication.role.controller.RoleControllerConstant.*;

/**
 * @description: It will handle all the roles related request.
 */
@Controller
public class RoleController {
    private final Role role;
    public RoleController(IRoleDao roleDao){
        this.role = new Role(roleDao);
    }

    /**
     * @param role: role model properties
     * @return It will return true/false if role entry has been created in db.
     * @throws JobsMadeEasyException
     */
    @PostMapping("/add_role")
    public boolean createRole(@ModelAttribute Role role) throws JobsMadeEasyException {
        return this.role.createRole(role);
    }

    /**
     * @param id: int role_id
     * @return It will return Role of the same id.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @GetMapping("/get_role_by_id/{id}")
    public Optional<Role> getRoleById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        return role.getRoleById(id);
    }

    /**
     * @return It will return list of Role.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @GetMapping("/get_all_roles")
    public List<Role> getAllRoles() throws JobsMadeEasyException, SQLException {
        return role.getAllRoles();
    }

    /**
     *
     * @param id
     * @return it will return true/false if role with same id has been deleted from db.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @DeleteMapping("/delete_role_by_id/{id}")
    public boolean deleteRoleById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        return role.deleteRoleById(id);
    }

    /**
     * @param model: model from thymeleaf ui form
     * @return It will return ui page to show list data.
     */
    @GetMapping("/view_all_roles")
    public String viewAllRoles(Model model){
        List<Role> roles;
        try {
            roles = getAllRoles();
        } catch (JobsMadeEasyException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("roles", roles);
        return VIEW_ALL_ROLES;
    }

    /**
     * @param model: model from thymeleaf ui form
     * @param id: id selected by user from thymeleaf ui
     * @return It will return ui page to show list data.
     */
    @GetMapping("/view_role_by_id/{id}")
    public String viewUserById(Model model, @PathVariable int id) throws SQLException, JobsMadeEasyException {

        Optional<Role> roles = getRoleById(id);
        model.addAttribute("roles", roles);
        return VIEW_ROLE_BY_ID;
    }

    /**
     * @param model: model from thymeleaf ui form
     * @return It will return ui page to delete data by id.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @GetMapping("/delete_role_by_id")
    public String deleteRoleByRoleId(Model model) throws JobsMadeEasyException, SQLException {
        List<Role> role = getAllRoles();
        model.addAttribute("role", role);
        return DELETE_ROLE_BY_ID;
    }

}
