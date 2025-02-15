package Group3.JobsMadeEasy.jobpost.model;

import Group3.JobsMadeEasy.jobpost.dao.IJobPostDao;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Component;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @Description It will handle all properties of Job post, and it contains all the business logic related to job posts.
 */
@Component
public abstract class JobPost {
    protected IJobPostDao jobPostDao;
    protected int jobPostId;
    protected String jobTitle;
    protected String salary;
    protected String jobType;
    protected String jobDescription;
    protected String jobLocation;
    protected String languageRequirements;

    public JobPost() {
    }

    public JobPost(int jobPostId, String jobTitle, String salary, String jobType, String jobDescription,
                   String jobLocation, String languageRequirements) {
        this.jobPostId = jobPostId;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.jobType = jobType;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.languageRequirements = languageRequirements;
    }

    public JobPost(IJobPostDao jobPostDao) {
        this.jobPostDao = jobPostDao;
    }

    public int getJobPostId() {
        return jobPostId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getSalary() {
        return salary;
    }

    public String getJobType() {
        return jobType;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public String getLanguageRequirements() {
        return languageRequirements;
    }

    public void setJobPostId(int jobPostId) {
        if (jobPostId <= 0) {
            return;
        }
        this.jobPostId = jobPostId;
    }

    public void setJobTitle(String jobTitle) {
        if (jobTitle.equals("")) {
            return;
        }
        this.jobTitle = jobTitle;
    }

    public void setSalary(String salary) {
        if (salary.equals("")) {
            return;
        }
        this.salary = salary;
    }

    public void setJobType(String jobType) {
        if (jobType.equals("")) {
            return;
        }
        this.jobType = jobType;
    }

    public void setJobDescription(String jobDescription) {
        if (jobDescription.equals("")) {
            return;
        }
        this.jobDescription = jobDescription;
    }

    public void setJobLocation(String jobLocation) {
        if (jobLocation.equals("")) {
            return;
        }
        this.jobLocation = jobLocation;
    }

    public void setLanguageRequirements(String languageRequirements) {
        if (languageRequirements.equals("")) {
            return;
        }
        this.languageRequirements = languageRequirements;
    }

    /**
     * @param jobPost
     * @return redirect HR to job post creation page after login.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    public String createJobPost(JobPost jobPost) throws JobsMadeEasyException {
        if (jobPost == null) {
            throw new JobsMadeEasyException("Failed to add job post!!");
        } else {
            jobPost.setJobPostId(GenerateIdUtil.Object().generateRandomId());
            jobPost.setJobTitle(jobPost.getJobTitle());
            jobPost.setSalary(jobPost.getSalary());
            jobPost.setJobType(jobPost.getJobType());
            jobPost.setJobDescription(jobPost.getJobDescription());
            jobPost.setJobLocation(jobPost.getJobLocation());
            jobPost.setLanguageRequirements(jobPost.getLanguageRequirements());
        }
        return this.jobPostDao.createJobPost(jobPost);
    }

    /**
     * @return return list of all job posts stored in a database to HR.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    public List<JobPost> getAllJobPost() throws JobsMadeEasyException {
        return this.jobPostDao.getAllJobPost();
    }

    /**
     * @param id
     * @return return Job posts of specific ID to HR.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    public Optional<JobPost> getJobPostById(int id) throws JobsMadeEasyException, SQLException {
        if (id == 0) {
            throw new JobsMadeEasyException("No job post found with given ID!!");
        }
        return this.jobPostDao.getJobPostById(id);
    }

    /**
     * @param id
     * @return delete job post from database after button click from HR.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    public boolean deleteJobPostById(int id) throws JobsMadeEasyException, SQLException {
        if (this.getJobPostById(id).isPresent()) {
            return this.jobPostDao.deleteJobPostById(id);
        }
        return false;
    }
}
