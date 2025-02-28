package Group3.JobsMadeEasy.jobpost.model;

import Group3.JobsMadeEasy.jobpost.dao.JobPostDaoImpTest;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobPostTest {
    JobPostDaoImpTest jobPostDaoImpTest = new JobPostDaoImpTest();
    JobPost jobPost = new SimpleJobPostFactory().createJobPost(jobPostDaoImpTest);

    @Test
    public void testJobPostClass() {
        JobPost jobPost = Mockito.spy(new SimpleJobPostFactory().createJobPost());
        jobPost.setJobTitle("HCSR");
        jobPost.setJobPostId(111);
        jobPost.setJobType("full time");
        jobPost.setSalary("100,000 CAD");
        jobPost.setJobDescription("Work with customers and provide great customer experience.");
        jobPost.setJobLocation("Halifax");
        jobPost.setLanguageRequirements("English");
        assertEquals(jobPost.getJobTitle(), "HCSR");
        assertEquals(jobPost.getJobPostId(), 111);
        assertEquals(jobPost.getJobType(), "full time");
        assertEquals(jobPost.getSalary(), "100,000 CAD");
        assertEquals(jobPost.getJobDescription(), "Work with customers and provide great customer experience.");
        assertEquals(jobPost.getJobLocation(), "Halifax");
        assertEquals(jobPost.getLanguageRequirements(), "English");

    }

    @Test
    public void setJobPostIdSuccessfulTest() {
        int id = 11;
        jobPost.setJobPostId(id);
        Assertions.assertEquals(11, jobPost.getJobPostId());
    }

    @Test
    public void setJobPostIdFailureTest() {
        int id = -11;
        jobPost.setJobPostId(id);
        Assertions.assertEquals(0, jobPost.getJobPostId());
    }

    @Test
    public void setJobTitleSuccessfulTest() {
        jobPost.setJobTitle("HCSR");
        Assertions.assertEquals("HCSR", jobPost.getJobTitle());
    }

    @Test
    public void setJobTitleFailureTest() {
        String title = "";
        Assertions.assertNull(jobPost.getJobTitle());
    }

    @Test
    public void setSalarySuccessfulTest() {
        jobPost.setSalary("100,000 CAD");
        Assertions.assertEquals("100,000 CAD", jobPost.getSalary());
    }

    @Test
    public void setSalaryFailureTest() {
        jobPost.setSalary("");
        Assertions.assertNull(jobPost.getSalary());
    }

    @Test
    public void setJobTypeSuccessfulTest() {
        jobPost.setJobType("full-time");
        Assertions.assertEquals("full-time", jobPost.getJobType());
    }

    @Test
    public void setJobTypeFailureTest() {
        jobPost.setJobType("");
        Assertions.assertNull(jobPost.getJobType());
    }

    @Test
    public void setJobDescriptionSuccessfulTest() {
        jobPost.setJobDescription("Implement java applications as per requirements of clients.");
        Assertions.assertEquals("Implement java applications as per requirements of clients.",
                jobPost.getJobDescription());
    }

    @Test
    public void setJobDescriptionFailureTest() {
        jobPost.setJobDescription("");
        Assertions.assertNull(jobPost.getJobDescription());
    }

    @Test
    public void setJobLocationSuccessfulTest() {
        jobPost.setJobLocation("Halifax");
        Assertions.assertEquals("Halifax", jobPost.getJobLocation());
    }

    @Test
    public void setJobLocationFailureTest() {
        jobPost.setJobLocation("");
        Assertions.assertNull(jobPost.getJobLocation());
    }

    @Test
    public void setLanguageRequirementsSuccessfulTest() {
        jobPost.setLanguageRequirements("English");
        Assertions.assertEquals("English", jobPost.getLanguageRequirements());
    }

    @Test
    public void setLanguageRequirementsFailureTest() {
        jobPost.setLanguageRequirements("");
        Assertions.assertNull(jobPost.getLanguageRequirements());
    }

    @Test
    public void createJobPostSuccessTest() throws JobsMadeEasyException {
        JobPost test = new SimpleJobPost((11), "Java Developer", "100,000 CAD",
                "Full time", "Develop java application that interacts with user " +
                "and do some work on data. Write tests as well.",
                "Halifax", "English");
        String result = jobPost.createJobPost(test);
        Assertions.assertEquals(result, "jobPost");
    }

    @Test
    public void createJobPostFailureTest(){
        Throwable exception = assertThrows(JobsMadeEasyException.class, () -> jobPost.createJobPost(null));
        assertEquals("Failed to add job post!!", exception.getMessage());
    }

    @Test
    public void getAllJobPost() throws JobsMadeEasyException {
        List<JobPost> test = jobPost.getAllJobPost();
        assertEquals(2, test.size());
    }

    @Test
    public void getJobPostByIdSuccessTest() throws JobsMadeEasyException, SQLException {
        int jobPostId = 11;
        Optional<JobPost> test = jobPost.getJobPostById(jobPostId);
        assertEquals(11, test.get().getJobPostId());
    }

    @Test
    public void getJobPostByIdFailureTest() {
        Throwable exception = assertThrows(JobsMadeEasyException.class, () -> jobPost.getJobPostById(0));
        assertEquals("No job post found with given ID!!", exception.getMessage());
    }

    @Test
    public void deleteJobPostByIdSuccessTest() throws SQLException, JobsMadeEasyException {
        int jobPostId = 22;
        boolean test = jobPost.deleteJobPostById(jobPostId);
        assertEquals(true, test);
    }

    @Test
    public void deleteJobPostByIdFailureTest(){
        int jobPostId = -1;
        boolean test = false;
        try {
            test = jobPost.deleteJobPostById(jobPostId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(false, test);
    }
}
