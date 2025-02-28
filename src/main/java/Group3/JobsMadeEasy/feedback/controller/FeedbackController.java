package Group3.JobsMadeEasy.feedback.controller;

import Group3.JobsMadeEasy.feedback.dao.IFeedbackDao;
import Group3.JobsMadeEasy.feedback.model.Feedback;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

import static Group3.JobsMadeEasy.feedback.controller.FeedbackControllerConstant.FEEDBACK_FORM;
import static Group3.JobsMadeEasy.feedback.controller.FeedbackControllerConstant.FEEDBACK_VIEW;

/**
 * @description This class will control all the tasks related to Feedback provided by the user
 */
@Controller
public class FeedbackController {
    private final Feedback feedback;
    public FeedbackController(IFeedbackDao feedbackDao) {

        this.feedback = new Feedback(feedbackDao);
    }

    /**
     * @param feedback
     * @return It returns the saved data and redirects to "myApplication"
     * @throws JobsMadeEasyException
     * @throws SQLException
     */

    @PostMapping("/save/feedback")
    public String createFeedback(@ModelAttribute Feedback feedback) throws JobsMadeEasyException, SQLException {
        return this.feedback.createFeedback(feedback);
    }

    /**
     * @description This helps in viewing the received feedback
     */

    @GetMapping("/view_feedback")
    public List<Feedback> getFeedback() throws JobsMadeEasyException, SQLException {
        return feedback.getFeedback();
    }

    /**
     * @param model
     * @return
     * @description This API is created for UI to display all the feedback to the admin
     */

    @GetMapping("/view_all_feedback")
    public String viewFeedback(Model model) {
        List<Feedback> feedbacks;
        try {
            feedbacks = getFeedback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JobsMadeEasyException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("feedbacks", feedbacks);
        return FEEDBACK_VIEW;
    }

    /**
     * @param model
     * @return It will return the feedback form
     */

    @GetMapping("/create_feedback_form")
    public String viewCreateFeedbackApplication(Model model) {
        Feedback feedback = new Feedback();
        model.addAttribute("feedback", feedback);
        return FEEDBACK_FORM;
    }
}



