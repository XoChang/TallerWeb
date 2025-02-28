package Group3.JobsMadeEasy.feedback.dao;

import Group3.JobsMadeEasy.database.dao.DatabaseSetup;
import Group3.JobsMadeEasy.feedback.model.Feedback;
import Group3.JobsMadeEasy.feedback.querygenerator.IFeedbackQueryGenerator;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import static Group3.JobsMadeEasy.feedback.controller.FeedbackControllerConstant.FEEDBACK_FORM;
import static Group3.JobsMadeEasy.jobapplication.controller.JobApplicationControllerConstant.APPLICANT_HOME_PAGE;

/**
 * @description Database Layer: It will handle all the database based queries for the Feedback Model
 */

@Component
public class FeedbackDaoImp implements IFeedbackDao {
    private final IFeedbackQueryGenerator feedbackQueryGenerator;
    private final DatabaseSetup databaseSetup;
    private final Connection connection;
    private final Statement statement;

    private final HttpSession session;

    /**
     * @param databaseSetup
     * @param feedbackQueryGenerator
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     * @description This will set up the database connection
     */
    public FeedbackDaoImp(IFeedbackQueryGenerator feedbackDao, DatabaseSetup databaseSetup,
                          IFeedbackQueryGenerator feedbackQueryGenerator, HttpSession session) throws
            SQLException, IOException, ClassNotFoundException {
        this.feedbackQueryGenerator = feedbackQueryGenerator;
        this.databaseSetup = databaseSetup;
        this.connection = databaseSetup.getConnectionObject();
        this.session = session;
        this.statement = connection.createStatement();
    }

    /**
     * @param feedback
     * @return It will return the HTML page "jobApplication" on success
     * @throws JobsMadeEasyException
     */

    @Override
    public String createFeedback(Feedback feedback) throws JobsMadeEasyException {

        feedback.setFeedbackId(GenerateIdUtil.Object().generateRandomId());
        feedback.setExperience(feedback.getExperience());
        feedback.setComments(feedback.getComments());
        feedback.setOverAllRating(feedback.getOverAllRating());
        try {
            String createFeedbackQuery = feedbackQueryGenerator.createFeedback(feedback);
            int updatedRows = statement.executeUpdate(createFeedbackQuery, Statement.RETURN_GENERATED_KEYS);
            if (updatedRows > 0) {
                return APPLICANT_HOME_PAGE;
            }
        } catch (SQLException e) {

            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
        }
        return FEEDBACK_FORM;
    }

    /**
     * @throws JobsMadeEasyException
     * @throws SQLException
     * @description It returns feedback
     */

    @Override
    public List<Feedback> getFeedback() throws JobsMadeEasyException, SQLException {
        ResultSet rs = null;
        List<Feedback> feedbacks = new LinkedList<>();
        try {
            String getFeedbackQuery = feedbackQueryGenerator.getFeedback();
            rs = statement.executeQuery(getFeedbackQuery);

            Feedback feedback = null;
            while (rs.next()) {
                int i = rs.getInt("feedbackId");
                String experience = rs.getString("experience");
                String comments = rs.getString("comments");
                String overAllRating = rs.getString("overAllRatings");
                feedback = new Feedback(i, experience, comments, overAllRating);
                feedbacks.add(feedback);
            }
            return (feedbacks);
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
            rs.close();
        }

    }
}

