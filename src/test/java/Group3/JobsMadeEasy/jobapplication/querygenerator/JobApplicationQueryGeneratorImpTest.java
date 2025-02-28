package Group3.JobsMadeEasy.jobapplication.querygenerator;

import Group3.JobsMadeEasy.jobapplication.model.JobApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JobApplicationQueryGeneratorImpTest {

    @Test
    public void getFeedBackTest(){

    }

    @Test
    public void createJobApplicationTest() {
        JobApplication jobApplication = new JobApplication(1111,"Neha","Karkhanis",20000, "Student", 2020, "part-time", "Engineering", "Masters",
                "Dalhousie","Data Science","Java","Math",89);
        JobApplicationQueryGeneratorImp jobApplicationDaoImp = JobApplicationQueryGeneratorImp.getInstance();
        String queryGenerated = jobApplicationDaoImp.createJobApplication(jobApplication);
        String queryExpected = "INSERT INTO jobApplication (applicationId,firstName,lastName,expectedSalary,currentEmployeeStatus,yearPassOut,applicationType,studyField,degreeType,university,expertOne,expertTwo,expertThree,jobPostId) VALUES " +
                "(\"1111\",\"Neha\",\"Karkhanis\",\"20000\",\"Student\",\"2020\",\"part-time\",\"Engineering\",\"Masters\",\"Dalhousie\",\"Data Science\",\"Java\",\"Math\",\"89\");";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }



    @Test
    public void viewAllJobApplicationTest() {
        JobApplicationQueryGeneratorImp jobApplicationQueryGeneratorDaoImp = JobApplicationQueryGeneratorImp.getInstance();
        String queryGenerated = jobApplicationQueryGeneratorDaoImp.getJobApplications();
        String queryExpected = "SELECT * FROM jobApplication;";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }



}
