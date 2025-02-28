package Group3.JobsMadeEasy.jobapplication.controller;

import Group3.JobsMadeEasy.jobapplication.dao.IJobApplicationDao;
import Group3.JobsMadeEasy.jobapplication.model.JobApplication;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static Group3.JobsMadeEasy.jobapplication.controller.JobApplicationControllerConstant.*;

/**
 * @description This handles all the tasks related to Job Application
 */

@Controller
public class JobApplicationController {

    private final JobApplication jobApplication;

    public JobApplicationController(IJobApplicationDao jobApplicationDao) {
        this.jobApplication = new JobApplication(jobApplicationDao);
    }

    /**
     * @param model
     * @return If the application form is filled well, it will direct to the Applicant home page for next task
     * @description This creates an application form
     */

    @GetMapping("/createApplicationForm")
    public String showJobApplicationCreationForm(Model model) {
        JobApplication jobApplication = new JobApplication();
        model.addAttribute("jobApplication", jobApplication);
        return "applicantHomePage";
    }

    /**
     * @return
     * @throws JobsMadeEasyException
     * @throws SQLException
     * @description This method will save all the data entered and send it to the database
     */

    @PostMapping("/save/jobApplication")
    public String createJobApplication(@ModelAttribute JobApplication jobApplication) throws JobsMadeEasyException,
            SQLException {
        return this.jobApplication.createJobApplication(jobApplication);
    }

    /**
     * @throws JobsMadeEasyException
     * @throws SQLException
     * @description It will fetch all the application forms filled.
     */
    @GetMapping("/view_all_job_application")
    public List<JobApplication> getJobApplication() throws JobsMadeEasyException, SQLException {
        return jobApplication.getAllJobApplication();
    }

    /**
     * @return Views page for all applications
     * @description This is view all the applications that have been filled on the Hr page
     */

    @GetMapping("/view_all_application")
    public String viewAllJobApplication(Model model) {
        List<JobApplication> jobApplications;
        try {
            jobApplications = getJobApplication();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JobsMadeEasyException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("jobApplications", jobApplications);
        return APPLICATION_VIEW_PAGE;
    }

    /**
     * @param model
     * @description This is the main Applicant home page
     */
    @GetMapping("/applicant_home_page")
    public String jobApplication(Model model) {
        return APPLICANT_HOME_PAGE;
    }

    /**
     * @return
     * @description This will display the application form for the user to fill
     */

    @GetMapping("/create_job_application")
    public String viewCreateJobApplication(Model model) {
        JobApplication jobApplication = new JobApplication();
        model.addAttribute("jobApplication", jobApplication);
        return CREATE_APPLICATION_PAGE;
    }


    @GetMapping("/delete_application")
    public String deleteApplication(Model model) throws JobsMadeEasyException, SQLException {
        List<JobApplication> jobApplication = getJobApplication();
        model.addAttribute("jobApplication", jobApplication);
        return DELETE_APPLICATION_PAGE;
    }
}


