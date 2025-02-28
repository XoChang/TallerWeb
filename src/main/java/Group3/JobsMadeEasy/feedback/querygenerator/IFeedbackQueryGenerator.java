package Group3.JobsMadeEasy.feedback.querygenerator;

import Group3.JobsMadeEasy.feedback.model.Feedback;

public interface IFeedbackQueryGenerator {

    String createFeedback(Feedback feedback);

    String getFeedback();

}
