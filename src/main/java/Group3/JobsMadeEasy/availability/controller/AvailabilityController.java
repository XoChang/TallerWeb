package Group3.JobsMadeEasy.availability.controller;

import Group3.JobsMadeEasy.availability.dao.IAvailabilityDao;
import Group3.JobsMadeEasy.availability.model.Availability;
import Group3.JobsMadeEasy.availability.model.AvailabilityUserName;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static Group3.JobsMadeEasy.availability.controller.AvailabilityControllerConstant.*;
/**
 * @description: It will handle all the Availability related requests.
 */
@Controller

public class AvailabilityController {
    private final Availability availability;
    private final AvailabilityUserName availabilityUserName;

    public AvailabilityController(IAvailabilityDao availabilityDao) {
        this.availability = new Availability(availabilityDao);
        this.availabilityUserName = new AvailabilityUserName(availabilityDao);
    }


    /**
     * @return: It will return the availability home page.
     */
    @GetMapping("/availability_home_page")
    public String showHrHomePage() {
        return AVAILABILITY_HOME_PAGE;
    }

    /**
     * @param model: model interface to pass model values to html page
     * @return: It will return the availability home page.
     */
    @GetMapping("/add_availability_form")
    public String showAddAvailabilityForm(Model model) {
        Availability availability = new Availability();
        model.addAttribute("availability", availability);
        return ADD_AVAILABILITY;
    }

    /**
     * @param availability: availability of user added in html form
     * @param id            : user id of logged in user
     * @throws JobsMadeEasyException
     * @return: It will return the availabilityHomePage of applicant.
     */
    @PostMapping("/save/availability/{id}")
    public String createAvailability(@ModelAttribute Availability availability, @PathVariable int id) throws
            JobsMadeEasyException {

        return this.availability.createAvailability(availability, id);
    }

    /**
     * @return It will return the details of user and availability.
     */

    @GetMapping("/get_all_availability")
    public List<AvailabilityUserName> getAllAvailability() throws JobsMadeEasyException, SQLException {
        return this.availability.getAllAvailability();
    }

    /**
     * @param id : user id of logged in user
     * @return It will return the details of user and availability.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */

    @GetMapping("/get_availability/{id}")
    public List<Availability> getMyAvailability(@PathVariable int id) throws JobsMadeEasyException, SQLException {

        return this.availability.getMyAvailability(id);
    }

    /**
     * @param id : user id of logged in user
     * @return It will return true or false.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */

    @DeleteMapping("/delete_availability/{id}")
    public boolean deleteAvailabilityById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        return this.availability.deleteAvailabilityById(id);

    }

    /**
     * @param model: model interface to pass model values to html page
     * @return It will return the details of user and availability.
     * @throws SQLException
     * @throws JobsMadeEasyException
     */

    @GetMapping("/view_all_availability")
    public String viewAllAvailability(Model model) throws SQLException, JobsMadeEasyException {
        List<AvailabilityUserName> availabilities = null;
        try {
            availabilities = getAllAvailability();
        } catch (JobsMadeEasyException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("availabilities", availabilities);
        return VIEW_ALL_AVAILABILITY;
    }

    /**
     * @param model: model interface to pass model values to html page
     * @param id:    id of current logged-in user
     * @return It will return the availability.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */

    @GetMapping("/view_my_availability/{id}")
    public String viewAvailabilityById(Model model, @PathVariable int id) throws JobsMadeEasyException, SQLException {

        List<Availability> availabilities = getMyAvailability(id);
        model.addAttribute("availabilities", availabilities);
        return VIEW_MY_AVAILABILITY;
    }

    /**
     * @param model: model interface to pass model values to html page
     * @param id:    id of current logged-in user
     * @return It will return the remaining availabilities.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @GetMapping("/delete_availability_by_id/{id}")
    public String deleteAvailabilityByUserId(Model model, @PathVariable int id) throws JobsMadeEasyException,
            SQLException {
        List<Availability> availability = getMyAvailability(id);
        model.addAttribute("availability", availability);
        return DELETE_AVAILABILITY_BY_ID;
    }







}
