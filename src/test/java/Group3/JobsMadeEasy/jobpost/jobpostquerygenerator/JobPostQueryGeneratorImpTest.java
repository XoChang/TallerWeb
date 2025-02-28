package Group3.JobsMadeEasy.jobpost.jobpostquerygenerator;

import Group3.JobsMadeEasy.jobpost.model.JobPost;
import Group3.JobsMadeEasy.jobpost.model.SimpleJobPost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JobPostQueryGeneratorImpTest {
    @Test
    public void createJobPostTest() {
        JobPost jobPost = new SimpleJobPost(11, "Java-Developer", "100,000 CAD",
                "Full time", "Develop java application that interacts with user " +
                "and do some work on data. Write tests as well.",
                "Halifax", "English");
        JobPostQueryGeneratorImp jobPostQueryGeneratorImp = JobPostQueryGeneratorImp.getInstance();
        String generatedQuery = jobPostQueryGeneratorImp.createJobPost(jobPost);
        String expectedQuery = "INSERT INTO jobPost(jobPostId,jobTitle,salary,jobType,jobDescription,jobLocation," +
                "languageRequired)VALUES " +
                "(\"11\",\"Java-Developer\",\"100,000 CAD\",\"Full time\",\"Develop java application that interacts " +
                "with user and do some work on data. Write tests as well.\",\"Halifax\",\"English\");";
        Assertions.assertEquals(expectedQuery, generatedQuery, "Query did not matched.!!");
    }

    @Test
    public void viewAllJobsTest() {
        JobPostQueryGeneratorImp jobPostQueryGeneratorImp = JobPostQueryGeneratorImp.getInstance();
        String generatedQuery = jobPostQueryGeneratorImp.viewAllJobs();
        String expectedQuery = "SELECT * FROM jobPost;";
        Assertions.assertEquals(expectedQuery, generatedQuery, "Query did not matched.!!");
    }

    @Test
    public void viewJobByIdTest() {
        int jobPostId = 11;
        JobPostQueryGeneratorImp jobPostQueryGeneratorImp = JobPostQueryGeneratorImp.getInstance();
        String generatedQuery = jobPostQueryGeneratorImp.viewJobById(jobPostId);
        String queryExpected = "SELECT * FROM jobPost WHERE jobPostId = 11;";
        Assertions.assertEquals(queryExpected, generatedQuery, "Query did not matched.!!");
    }

    @Test
    public void deleteJobByIdTest() {
        int jobPostId = 11;
        JobPostQueryGeneratorImp jobPostQueryGeneratorImp = JobPostQueryGeneratorImp.getInstance();
        String generatedQuery = jobPostQueryGeneratorImp.deleteJobById(jobPostId);
        String queryExpected = "DELETE FROM jobPost WHERE jobPostId = 11;";
        Assertions.assertEquals(queryExpected, generatedQuery, "Query did not matched.!!");
    }
}


