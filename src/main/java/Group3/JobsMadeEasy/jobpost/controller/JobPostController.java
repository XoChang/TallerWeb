package Group3.JobsMadeEasy.jobpost.controller;

import Group3.JobsMadeEasy.jobpost.dao.IJobPostDao;
import Group3.JobsMadeEasy.jobpost.model.IJobPostFactory;
import Group3.JobsMadeEasy.jobpost.model.JobPost;
import Group3.JobsMadeEasy.jobpost.model.SimpleJobPost;
import Group3.JobsMadeEasy.jobpost.model.SimpleJobPostFactory;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import static Group3.JobsMadeEasy.jobpost.controller.JobPostControllerConstant.*;

/**
 *@Description It will handle all api requests coming from browser and redirect flow to backend logic.
 */
@Controller
public class JobPostController {
    private final JobPost jobPost;

    public JobPostController(IJobPostDao jobPostDao) {
        IJobPostFactory jobPostFactory = new SimpleJobPostFactory();
        this.jobPost = jobPostFactory.createJobPost(jobPostDao);
    }

    /**
     * @return Home page for Hr.
     */
    @GetMapping("/hr_home_page")
    public String showHrHomePage() {
        return HR_HOME_PAGE;
    }

    /**
     * @param model
     * @return Form to create job post.
     */
    @GetMapping("/create_job_post")
    public String viewCreateJobPost(Model model) {
        IJobPostFactory jobPostFactory = new SimpleJobPostFactory();
        JobPost jobPost = jobPostFactory.createJobPost();
        model.addAttribute("jobPost", jobPost);
        return CREATE_JOB_POST;
    }

    /**
     * @param model
     * @return table that shows all job posts created by HR.
     */
    @GetMapping("/view_all_jobs")
    public String viewAllJobs(Model model) {
        List<JobPost> jobPosts;
        try {
            jobPosts = getAllJobPost();
        } catch (SQLException | JobsMadeEasyException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("jobPosts", jobPosts);
        return VIEW_ALL_JOBS;
    }

    /**
     * @param model
     * @return UI table that will give an option to HR to delete job post.
     * @throws SQLException
     * @throws JobsMadeEasyException
     */
    @GetMapping("/delete_job_by_id")
    public String deleteJobsById(Model model) throws SQLException, JobsMadeEasyException {
        List<JobPost> jobPosts = getAllJobPost();
        model.addAttribute("jobPosts", jobPosts);
        return DELETE_JOBS_BY_ID;
    }

    /**
     * @param jobPost
     * @return redirect HR to job post creation page after login.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @PostMapping("/add_job_post")
    public String createJobPost(@ModelAttribute SimpleJobPost jobPost) throws JobsMadeEasyException, SQLException {

        return this.jobPost.createJobPost(jobPost);
    }

    /**
     * @return return list of all job posts stored in a database to HR.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @GetMapping("/get_all_job_post")
    public List<JobPost> getAllJobPost() throws JobsMadeEasyException, SQLException {

        return jobPost.getAllJobPost();
    }

    /**
     * @param id
     * @return return Job posts of specific ID to HR.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @GetMapping("/get_job_post_by_id/{id}")
    public Optional<JobPost> getJobPostById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        return jobPost.getJobPostById(id);
    }

    /**
     * @param id
     * @return delete job post from database after button click from HR.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @DeleteMapping("/delete_job_post_by_id/{id}")
    public boolean deleteJobPostById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        return jobPost.deleteJobPostById(id);
    }
}
