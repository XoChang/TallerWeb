package Group3.JobsMadeEasy.feedback.querygenerator;

import Group3.JobsMadeEasy.feedback.model.Feedback;
import org.springframework.stereotype.Component;

import static Group3.JobsMadeEasy.feedback.querygenerator.FeedbackConstant.*;


/**
 * @description This class is responsible for query generation for Feedback module
 */
@Component
public class FeedbackQueryGeneratorImp implements IFeedbackQueryGenerator {
    private static FeedbackQueryGeneratorImp obj;

    public static FeedbackQueryGeneratorImp getInstance() {
        if (obj == null) {
            obj = new FeedbackQueryGeneratorImp();
        }
        return obj;
    }

    public String createFeedback(Feedback feedback) {
        return "INSERT INTO " + FEEDBACK_TABLE +
                " (" + FEEDBACK_ID_COLUMN + "," + EXPERIENCE_COLUMN + "," +
                COMMENTS_COLUMN + "," + OVER_ALL_RATING_COLUMN + ")" +
                " VALUES (" +
                "\"" + feedback.getFeedbackId() + "\"," +
                "\"" + feedback.getExperience() + "\"," +
                "\"" + feedback.getComments() + "\"," +
                "\"" + feedback.getOverAllRating() + "\"" +

                ");";
    }

    @Override
    public String getFeedback() {

        return "SELECT * FROM " + FEEDBACK_TABLE + ";";
    }
}



