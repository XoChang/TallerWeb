package Group3.JobsMadeEasy.feedback.model;

import Group3.JobsMadeEasy.feedback.dao.IFeedbackDao;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * @description Business Layer: It contains all the business logic for Feedback module
 */
@Component
public class Feedback {

    private int feedbackId;
    private String experience;
    private String comments;
    private String overAllRating;
    private IFeedbackDao feedbackDao;
    private IFeedbackDao getFeedbackDao;

    public Feedback(int feedbackId, String experience, String comments, String overAllRating) {

        this.feedbackId = feedbackId;
        this.experience = experience;
        this.comments = comments;
        this.overAllRating = overAllRating;
    }

    public Feedback(IFeedbackDao feedbackDao) {

        this.getFeedbackDao = feedbackDao;
    }

    public Feedback() {
    }

    public int setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
        return feedbackId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public String getExperience() {
        return experience;
    }

    public String setExperience(String experience) {
        this.experience = experience;
        return experience;
    }

    public String getComments() {
        return comments;
    }

    public String setComments(String comments) {
        this.comments = comments;
        return comments;
    }

    public String getOverAllRating() {
        return overAllRating;
    }

    public String setOverAllRating(String overAllRating) {
        this.overAllRating = overAllRating;
        return overAllRating;
    }

    /**
     * @throws JobsMadeEasyException
     * @throws SQLException
     * @description Business logic to create a Feedback form
     */

    public String createFeedback(Feedback feedback) throws JobsMadeEasyException, SQLException {

        if (feedback == null) {
            throw new JobsMadeEasyException("NO FEEDBACK PROVIDED");
        }
        return this.getFeedbackDao.createFeedback(feedback);
    }

    /**
     * @throws SQLException
     * @throws JobsMadeEasyException
     * @description Business logic to get Feedback
     */

    public List<Feedback> getFeedback() throws SQLException, JobsMadeEasyException {

        return this.getFeedbackDao.getFeedback();
    }
}
