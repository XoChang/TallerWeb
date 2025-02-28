package Group3.JobsMadeEasy.jobapplication.dao;

import Group3.JobsMadeEasy.database.dao.DatabaseSetup;
import Group3.JobsMadeEasy.jobapplication.model.JobApplication;
import Group3.JobsMadeEasy.jobapplication.querygenerator.IJobApplicationQueryGenerator;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
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

import static Group3.JobsMadeEasy.jobapplication.controller.JobApplicationControllerConstant.APPLICANT_HOME_PAGE;
import static Group3.JobsMadeEasy.jobapplication.controller.JobApplicationControllerConstant.CREATE_APPLICATION_PAGE;

/**
 * @description Database Layer: It will handle all the database layer queries for Job Application
 */

@Component
public class JobApplicationDaoImp implements IJobApplicationDao {
    private final IJobApplicationQueryGenerator jobApplicationQueryGenerator;
    private final DatabaseSetup databaseSetup;
    private final Connection connection;
    private final Statement statement;
    private final HttpSession session;

    public JobApplicationDaoImp(IJobApplicationQueryGenerator jobApplicationDao, DatabaseSetup databaseSetup,
                                HttpSession session, IJobApplicationQueryGenerator jobApplicationQueryGenerator) throws
            SQLException, IOException, ClassNotFoundException, JobsMadeEasyException {
        this.jobApplicationQueryGenerator = jobApplicationQueryGenerator;
        this.databaseSetup = databaseSetup;
        this.connection = databaseSetup.getConnectionObject();
        this.session = session;
        this.statement = connection.createStatement();
    }

    /**
     * @param jobApplication
     * @return returns the HTML page to fill the form
     * @throws JobsMadeEasyException
     * @throws SQLException
     * @description Creates Job Application
     */
    @Override
    public String createJobApplication(JobApplication jobApplication) throws JobsMadeEasyException, SQLException {

        jobApplication.setApplicationId(GenerateIdUtil.Object().generateRandomId());
        jobApplication.setFirstName(jobApplication.getFirstName());
        jobApplication.setLastName(jobApplication.getLastName());
        jobApplication.setExpectedSalary(jobApplication.getExpectedSalary());
        jobApplication.setCurrentEmployeeStatus(jobApplication.getCurrentEmployeeStatus());
        jobApplication.setYearPassOut(jobApplication.getYearPassOut());
        jobApplication.setApplicationType(jobApplication.getApplicationType());
        jobApplication.setStudyField(jobApplication.getStudyField());
        jobApplication.setDegreeType(jobApplication.getDegreeType());
        jobApplication.setUniversity(jobApplication.getUniversity());
        jobApplication.setExpertOne(jobApplication.getExpertOne());
        jobApplication.setExpertTwo(jobApplication.getExpertTwo());
        jobApplication.setExpertThree(jobApplication.getExpertThree());
        jobApplication.setJobPostId(jobApplication.getJobPostId());
        try {
            String createJobApplicationQuery = jobApplicationQueryGenerator.createJobApplication(jobApplication);
            int updatedRows = statement.executeUpdate(createJobApplicationQuery, Statement.RETURN_GENERATED_KEYS);
            if (updatedRows > 0) {
                return APPLICANT_HOME_PAGE;
            }
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
        }
        return CREATE_APPLICATION_PAGE;
    }

    /**
     * @return
     * @throws JobsMadeEasyException
     * @throws SQLException
     * @description Gets the Job Application
     */

    @Override
    public List<JobApplication> getJobApplication() throws JobsMadeEasyException, SQLException {
        ResultSet rs = null;
        try {
            String getJobApplicationQuery = jobApplicationQueryGenerator.getJobApplications();
            rs = statement.executeQuery(getJobApplicationQuery);
            List<JobApplication> jobApplications = new LinkedList<>();
            JobApplication jobApplication = null;
            while (rs.next()) {
                int i = rs.getInt("applicationId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int expectedSalary = rs.getInt("expectedSalary");
                String currentEmployeeStatus = rs.getString("currentEmployeeStatus");
                int yearPassOut = rs.getInt("yearPassOut");
                String applicationType = rs.getString("applicationType");
                String studyField = rs.getString("studyField");
                String degreeType = rs.getString("degreeType");
                String university = rs.getString("university");
                String expertOne = rs.getString("expertOne");
                String expertTwo = rs.getString("expertTwo");
                String expertThree = rs.getString("expertThree");
                int jobPostId = rs.getInt("jobPostId");
                jobApplication = new JobApplication(i, firstName, lastName, expectedSalary, currentEmployeeStatus, yearPassOut, applicationType, studyField, degreeType, university,
                        expertOne, expertTwo, expertThree, jobPostId);
                jobApplications.add(jobApplication);
            }
            return jobApplications;
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
            rs.close();
        }

    }

}


