package Group3.JobsMadeEasy.jobpost.jobpostquerygenerator;

import Group3.JobsMadeEasy.jobpost.model.JobPost;
import org.springframework.stereotype.Repository;
import static Group3.JobsMadeEasy.jobpost.jobpostquerygenerator.JobPostConstant.*;

/**
 * @Description It will generate SQL queries for all job post related operations.
 */
@Repository
public class JobPostQueryGeneratorImp implements IJobPostQueryGenerator {
    private static JobPostQueryGeneratorImp obj;

    public JobPostQueryGeneratorImp() {
    }

    public static JobPostQueryGeneratorImp getInstance() {
        if (obj == null) {
            obj = new JobPostQueryGeneratorImp();
        }
        return obj;
    }

    /**
     *
     * @param jobPost
     * @return It will return query for job post creation.
     */
    @Override
    public String createJobPost(JobPost jobPost) {
        return "INSERT INTO " + JOB_POST +
                "(" + JOB_POST_ID + "," + JOB_TITLE + ","
                + SALARY + "," + JOB_TYPE + ","
                + JOB_DESCRIPTION + ","
                + JOB_LOCATION + "," + LANGUAGE_REQUIREMENTS + ")" +
                "VALUES (" +
                "\"" + jobPost.getJobPostId() + "\"," +
                "\"" + jobPost.getJobTitle() + "\"," +
                "\"" + jobPost.getSalary() + "\"," +
                "\"" + jobPost.getJobType() + "\"," +
                "\"" + jobPost.getJobDescription() + "\"," +
                "\"" + jobPost.getJobLocation() + "\"," +
                "\"" + jobPost.getLanguageRequirements() + "\"" +
                ");";
    }

    /**
     * @return it will return Query to view all job posts from database.
     */
    @Override
    public String viewAllJobs() {
        return "SELECT * FROM " + JOB_POST + ";";
    }

    /**
     * @param id
     * @return it will return Query to view specific job post from database.
     */
    @Override
    public String viewJobById(int id) {
        return "SELECT * FROM " + JOB_POST + " WHERE " + JOB_POST_ID + " = " + id + ";";
    }

    /**
     * @param id
     * @return it will delete specific job post from database.
     */
    @Override
    public String deleteJobById(int id) {
        return "DELETE FROM " + JOB_POST + " WHERE " + JOB_POST_ID + " = " + id + ";";
    }
}

