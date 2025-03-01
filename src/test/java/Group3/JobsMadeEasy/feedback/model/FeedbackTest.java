package Group3.JobsMadeEasy.feedback.model;

import Group3.JobsMadeEasy.feedback.dao.FeedbackDaoImpTest;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FeedbackTest {

    Feedback feedback = new Feedback();

    @Test
    public void testFeedbackClass() {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(1111);
        feedback.setExperience("Excellent experience with Jobs Mde easy");
        feedback.setComments("nicee!!");
        feedback.setOverAllRating("good");
        assertEquals(1111, feedback.getFeedbackId());
        assertEquals("Excellent experience with Jobs Mde easy", feedback.getExperience());
        assertEquals("nicee!!", feedback.getComments());
        assertEquals("good", feedback.getOverAllRating());
    }


    @Test
    public void createFeedbackFailureTest() {
        Throwable exception = assertThrows(JobsMadeEasyException.class, () -> feedback.createFeedback(null));
        assertEquals("NO FEEDBACK PROVIDED", exception.getMessage());
    }

    @Test
    public void createFeedbackSuccessTest() throws JobsMadeEasyException, SQLException {
        
        String result = feedback.createFeedback(new Feedback(1113, "Excellent experience with Jobs Mde easy",
                "nicee!!", "good"));
        assertEquals("feedbackForm", result);
    }

    @Test
    public void getAllFeedbackTest() throws SQLException, JobsMadeEasyException {
        List<Feedback> feedbacks = feedback.getFeedback();
        assertEquals(2, feedbacks.size());
    }

}
