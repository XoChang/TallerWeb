package Group3.JobsMadeEasy.feedback.querygenerator;

import Group3.JobsMadeEasy.feedback.model.Feedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FeedbackQueryGeneratorImpTest {


    @Test
    public void createFeedbackTest() {
        Feedback feedback = new Feedback(1000,"Excellent experience with Jobs Mde easy","nicee!!","good");
        FeedbackQueryGeneratorImp feedbackDaoImp = FeedbackQueryGeneratorImp.getInstance();
        String queryGenerated = feedbackDaoImp.createFeedback(feedback);
        String queryExpected = "INSERT INTO feedback (feedbackId,experience,comments,overAllRatings) VALUES " +
                "(\"1000\",\"Excellent experience with Jobs Mde easy\",\"nicee!!\",\"good\");";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }



    @Test
    public void getFeedbackTest() {
        FeedbackQueryGeneratorImp feedbackQueryGeneratorDaoImp = FeedbackQueryGeneratorImp.getInstance();
        String queryGenerated = feedbackQueryGeneratorDaoImp.getFeedback();
        String queryExpected = "SELECT * FROM feedback;";
        Assertions.assertEquals(queryExpected,queryGenerated,"query not match");
    }
}
