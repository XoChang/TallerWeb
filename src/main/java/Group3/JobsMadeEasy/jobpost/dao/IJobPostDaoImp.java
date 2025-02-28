package Group3.JobsMadeEasy.jobpost.dao;

import Group3.JobsMadeEasy.database.dao.DatabaseSetup;
import Group3.JobsMadeEasy.jobpost.jobpostquerygenerator.IJobPostQueryGenerator;
import Group3.JobsMadeEasy.jobpost.model.IJobPostFactory;
import Group3.JobsMadeEasy.jobpost.model.JobPost;
import Group3.JobsMadeEasy.jobpost.model.SimpleJobPostFactory;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import static Group3.JobsMadeEasy.jobpost.controller.JobPostControllerConstant.CREATE_JOB_POST;
import static Group3.JobsMadeEasy.jobpost.controller.JobPostControllerConstant.HR_HOME_PAGE;

/**
 *@Description It will handle all the database layer queries related to job posts.
 */
@Component
public class IJobPostDaoImp implements IJobPostDao {
    private final IJobPostQueryGenerator jobPostQueryGenerator;
    private final DatabaseSetup databaseSetup;
    private final Connection connection;
    private final Statement statement;
    private final HttpSession session;

    /**
     * @param jobPostQueryGenerator
     * @param databaseSetup
     * @param session
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public IJobPostDaoImp(IJobPostQueryGenerator jobPostQueryGenerator, DatabaseSetup databaseSetup,
                          HttpSession session)
            throws SQLException, IOException, ClassNotFoundException {
        this.jobPostQueryGenerator = jobPostQueryGenerator;
        this.databaseSetup = databaseSetup;
        this.connection = databaseSetup.getConnectionObject();
        this.statement = connection.createStatement();
        this.session = session;
    }

    /**
     *
     * @param jobPost
     * @return It will either redirect HR to Home page if job post creation is successful, or redirect
     * to job post creation page again.
     * @throws JobsMadeEasyException
     */
    @Override
    public String createJobPost(JobPost jobPost) throws JobsMadeEasyException {
        try {
            String createJobPostQuery = jobPostQueryGenerator.createJobPost(jobPost);
            int updatedRowsInTable = statement.executeUpdate(createJobPostQuery, Statement.RETURN_GENERATED_KEYS);
            if (updatedRowsInTable > 0) {
                return HR_HOME_PAGE;
            }
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
        }
        return CREATE_JOB_POST;
    }

    /**
     * @return It will return list of all job posts from database to HR.
     * @throws JobsMadeEasyException
     */
    @Override
    public List<JobPost> getAllJobPost() throws JobsMadeEasyException {
        ResultSet resultSet;
        try {
            String getAllJobPostQuery = jobPostQueryGenerator.viewAllJobs();
            resultSet = statement.executeQuery(getAllJobPostQuery);
            List<JobPost> jobPosts = new LinkedList<>();
            while (resultSet.next()) {
                int jobPostId = resultSet.getInt("jobPostId");
                String jobTitle = resultSet.getString("jobTitle");
                String salary = resultSet.getString("salary");
                String jobType = resultSet.getString("jobType");
                String jobDescription = resultSet.getString("jobDescription");
                String jobLocation = resultSet.getString("jobLocation");
                String languageRequired = resultSet.getString("languageRequired");
                IJobPostFactory jobPostFactory = new SimpleJobPostFactory();
                JobPost jobPost = jobPostFactory.createJobPost();
                jobPost.setJobPostId(jobPostId);
                jobPost.setJobTitle(jobTitle);
                jobPost.setSalary(salary);
                jobPost.setJobType(jobType);
                jobPost.setJobDescription(jobDescription);
                jobPost.setJobLocation(jobLocation);
                jobPost.setLanguageRequirements(languageRequired);
                jobPosts.add(jobPost);
            }
            return jobPosts;
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
        }
    }

    /**
     *
     * @param id
     * @return return job post that have given ID.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    @Override
    public Optional<JobPost> getJobPostById(int id) throws JobsMadeEasyException, SQLException {
        ResultSet resultSet = null;
        if (id == 0) {
            throw new JobsMadeEasyException("No job post found with given id.!!");
        } else {
            try {
                String getJobPostByIdQuery = jobPostQueryGenerator.viewJobById(id);
                resultSet = statement.executeQuery(getJobPostByIdQuery);
                if (resultSet.next()) {
                    IJobPostFactory jobPostFactory = new SimpleJobPostFactory();
                    JobPost jobPost = jobPostFactory.createJobPost();
                    jobPost.setJobPostId(resultSet.getInt("jobPostId"));
                    jobPost.setJobTitle(resultSet.getString("jobTitle"));
                    jobPost.setSalary(resultSet.getString("salary"));
                    jobPost.setJobType(resultSet.getString("jobType"));
                    jobPost.setJobDescription(resultSet.getString("jobDescription"));
                    jobPost.setJobLocation(resultSet.getString("jobLocation"));
                    jobPost.setLanguageRequirements(resultSet.getString("languageRequired"));
                    return Optional.of(jobPost);
                }
            } catch (SQLException e) {
                throw new JobsMadeEasyException(e.getMessage());
            } finally {
                databaseSetup.closeDatabaseConnection();
                resultSet.close();
            }
        }
        return null;
    }

    /**
     * @param id
     * @return It will delete job post from database that has the same ID as given.
     * @throws JobsMadeEasyException
     */
    @Override
    public boolean deleteJobPostById(int id) throws JobsMadeEasyException {
        try {
            String deleteJobPostByIdQuery = jobPostQueryGenerator.deleteJobById(id);
            statement.executeUpdate(deleteJobPostByIdQuery);
            return true;
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
        }
    }
}
