package Group3.JobsMadeEasy.feedback.model;

import Group3.JobsMadeEasy.feedback.dao.FeedbackDaoImpTest;
import Group3.JobsMadeEasy.jobapplication.model.JobApplication;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FeedbackTest {

    FeedbackDaoImpTest FeedbackDaoImpTest = new FeedbackDaoImpTest();
    Feedback feedback = new Feedback(FeedbackDaoImpTest);
    @Test
    public void testFeedbackClass() {
        Feedback feedback = Mockito.spy(new Feedback());
        feedback.getFeedbackId();
        feedback.getExperience();
        feedback.getComments();
        feedback.getOverAllRating();
        feedback.setFeedbackId(1111);
        feedback.setExperience("Excellent experience with Jobs Mde easy");
        feedback.setComments("nicee!!");
        feedback.setOverAllRating("good");
        assertEquals(feedback.getFeedbackId(), 1111);
        assertEquals(feedback.getExperience(), "Excellent experience with Jobs Mde easy");
        assertEquals(feedback.getComments(), "nicee!!");
        assertEquals(feedback.getOverAllRating(), "good");
    }


    @Test
    public void createFeedbackFailureTest(){
        Throwable exception = assertThrows(JobsMadeEasyException.class, () -> feedback.createFeedback(null));
        assertEquals("NO FEEDBACK PROVIDED", exception.getMessage());
    }

    @Test
    public void createFeedbackSuccessTest() throws JobsMadeEasyException, SQLException {
        Feedback test = new Feedback(1113, "Excellent experience with Jobs Mde easy",
                "nicee!!", "good");
        String result = feedback.createFeedback(test);
        assertEquals(result,"feedbackForm");
    }

    @Test
    public void getAllFeedbackTest() throws SQLException, JobsMadeEasyException {
        List<Feedback> test = feedback.getFeedback();
        assertEquals(2,test.size());
    }

}
