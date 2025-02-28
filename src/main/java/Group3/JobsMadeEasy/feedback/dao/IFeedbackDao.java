package Group3.JobsMadeEasy.feedback.dao;

import Group3.JobsMadeEasy.feedback.model.Feedback;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;

import java.sql.SQLException;
import java.util.List;

public interface IFeedbackDao {
    String createFeedback(Feedback feedback) throws JobsMadeEasyException, SQLException;

    List<Feedback> getFeedback() throws JobsMadeEasyException, SQLException;


}
