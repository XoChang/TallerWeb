package Group3.JobsMadeEasy.jobapplication.model;

import Group3.JobsMadeEasy.jobapplication.dao.IJobApplicationDao;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * @description This is a business layer and all the business logic for Job Application module is handles here
 */
@Component
public class JobApplication {
    private IJobApplicationDao jobApplicationDao;
    private int applicationId;
    private String firstName;
    private String lastName;
    private int expectedSalary;
    private String currentEmployeeStatus;
    private int yearPassOut;
    private String applicationType;
    private String studyField;
    private String degreeType;
    private String university;
    private String expertOne;
    private String expertTwo;
    private String expertThree;
    private int jobPostId;

    public JobApplication(IJobApplicationDao jobApplicationDao) {

        this.jobApplicationDao = jobApplicationDao;
    }

    public JobApplication() {
    }

    public JobApplication(int applicationId, String firstName, String lastName, int expectedSalary, String currentEmployeeStatus, int yearPassOut, String applicationType, String studyField, String degreeType, String university, String expertOne, String expertTwo, String expertThree, int jobPostId) {
        this.applicationId = applicationId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.expectedSalary = expectedSalary;
        this.currentEmployeeStatus = currentEmployeeStatus;
        this.yearPassOut = yearPassOut;
        this.applicationType = applicationType;
        this.studyField = studyField;
        this.degreeType = degreeType;
        this.university = university;
        this.expertOne = expertOne;
        this.expertTwo = expertTwo;
        this.expertThree = expertThree;
        this.jobPostId = jobPostId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(int expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getCurrentEmployeeStatus() {
        return currentEmployeeStatus;
    }

    public void setCurrentEmployeeStatus(String currentEmployeeStatus) {
        this.currentEmployeeStatus = currentEmployeeStatus;
    }

    public int getYearPassOut() {
        return yearPassOut;
    }

    public void setYearPassOut(int yearPassOut) {
        this.yearPassOut = yearPassOut;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getStudyField() {
        return studyField;
    }

    public void setStudyField(String studyField) {
        this.studyField = studyField;
    }

    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getExpertOne() {
        return expertOne;
    }

    public void setExpertOne(String expertOne) {
        this.expertOne = expertOne;
    }

    public String getExpertTwo() {
        return expertTwo;
    }

    public void setExpertTwo(String expertTwo) {
        this.expertTwo = expertTwo;
    }

    public String getExpertThree() {
        return expertThree;
    }

    public void setExpertThree(String expertThree) {
        this.expertThree = expertThree;
    }

    public int getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(int jobPostId) {
        this.jobPostId = jobPostId;
    }

    /**
     * @param jobApplication
     * @return
     * @throws JobsMadeEasyException
     * @throws SQLException
     * @description Business logic to create job application form
     */

    public String createJobApplication(JobApplication jobApplication) throws JobsMadeEasyException, SQLException {

        if (jobApplication == null) {
            throw new JobsMadeEasyException("Job Application details not found..");
        }
        return this.jobApplicationDao.createJobApplication(jobApplication);
    }

    /**
     * Description Business logic to get all the application forms that have been filled
     *
     * @return
     * @throws SQLException
     * @throws JobsMadeEasyException
     */

    public List<JobApplication> getAllJobApplication() throws SQLException, JobsMadeEasyException {
        return this.jobApplicationDao.getJobApplication();
    }


}
