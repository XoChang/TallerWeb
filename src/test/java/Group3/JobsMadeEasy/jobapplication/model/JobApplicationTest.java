package Group3.JobsMadeEasy.jobapplication.model;

import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.jobapplication.dao.JobApplicationDaoImpTest;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobApplicationTest {

    JobApplicationDaoImpTest JobApplicationDaoImpTest = new JobApplicationDaoImpTest();
    JobApplication jobApplication = new JobApplication(JobApplicationDaoImpTest);
    @Test
    public void testJobApplicationClass() {
        JobApplication jobApplication = Mockito.spy(new JobApplication());
        jobApplication.getApplicationId();
        jobApplication.getFirstName();
        jobApplication.getLastName();
        jobApplication.getExpectedSalary();
        jobApplication.getCurrentEmployeeStatus();
        jobApplication.getYearPassOut();
        jobApplication.getApplicationType();
        jobApplication.getStudyField();
        jobApplication.getDegreeType();
        jobApplication.getUniversity();
        jobApplication.getExpertOne();
        jobApplication.getExpertTwo();
        jobApplication.getExpertThree();
        jobApplication.getJobPostId();
        jobApplication.setApplicationId(1123);
        jobApplication.setFirstName("Neha");
        jobApplication.setLastName("Karkhanis");
        jobApplication.setExpectedSalary(20000);
        jobApplication.setCurrentEmployeeStatus("Student");
        jobApplication.setYearPassOut(2020);
        jobApplication.setApplicationType("part-time");
        jobApplication.setStudyField("Engineering");
        jobApplication.setDegreeType("Masters");
        jobApplication.setUniversity("Dalhousie");
        jobApplication.setExpertOne("Data Science");
        jobApplication.setExpertTwo("Maths");
        jobApplication.setExpertThree("Java");
        jobApplication.setJobPostId(89);
        assertEquals(jobApplication.getApplicationId(), 1123);
        assertEquals(jobApplication.getFirstName(), "Neha");
        assertEquals(jobApplication.getLastName(), "Karkhanis");
        assertEquals(jobApplication.getExpectedSalary(), 20000);
        assertEquals(jobApplication.getCurrentEmployeeStatus(), "Student");
        assertEquals(jobApplication.getYearPassOut(), 2020);
        assertEquals(jobApplication.getApplicationType(), "part-time");
        assertEquals(jobApplication.getStudyField(), "Engineering");
        assertEquals(jobApplication.getDegreeType(), "Masters");
        assertEquals(jobApplication.getUniversity(), "Dalhousie");
        assertEquals(jobApplication.getExpertOne(), "Data Science");
        assertEquals(jobApplication.getExpertTwo(), "Maths");
        assertEquals(jobApplication.getExpertThree(),"Java");
        assertEquals(jobApplication.getJobPostId(),89);
    }





    @Test
    public void createJobApplicationFailureTest(){
        Throwable exception = assertThrows(JobsMadeEasyException.class, () -> jobApplication.createJobApplication(null));
        assertEquals("Job Application details not found..", exception.getMessage());
    }

    @Test
    public void createJobApplicationSuccessTest() throws JobsMadeEasyException, SQLException {
        JobApplication test = new JobApplication(1123, "Neha",
                "Karkhanis", 20000, "Student",2020,"part-time","Engineering",
                "Masters","Dalhousie","Data Science",
                "Maths","Java",89);
        String result = jobApplication.createJobApplication(test);
        assertEquals(result,"register");
    }

    @Test
    public void getAllJobApplicationTest() throws SQLException, JobsMadeEasyException {
        List<JobApplication> test = jobApplication.getAllJobApplication();
        assertEquals(2,test.size());
    }





}
