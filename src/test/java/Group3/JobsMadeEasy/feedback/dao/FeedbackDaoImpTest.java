package Group3.JobsMadeEasy.feedback.dao;

import Group3.JobsMadeEasy.feedback.model.Feedback;

import java.util.*;

public class FeedbackDaoImpTest implements IFeedbackDao {

    private final List<Feedback> mockDB;

    public FeedbackDaoImpTest(){
        mockDB = new ArrayList<>();
        mockDB.add(new Feedback(1112, "Excellent experience with Jobs Mde easy",
                "nicee!!", "good"));
        mockDB.add(new Feedback(1111, "Excellent experience with Jobs Mde easy",
                "nicee!!", "good"));
    }

    @Override
    public String createFeedback(Feedback feedback) {
        this.mockDB.add(feedback);
        ListIterator<Feedback> iterator = mockDB.listIterator();
        while (iterator.hasNext()){
            Feedback feedback1 = iterator.next();
            if(feedback1.getFeedbackId() == feedback.getFeedbackId())
            {
                return "feedbackForm";
            }
        }
        return "";
    }

    @Override
    public List<Feedback> getFeedback() {
        ListIterator<Feedback> iterator = mockDB.listIterator();
        List<Feedback> feedbacks= new ArrayList<>();
        while (iterator.hasNext()){
            Feedback feedback = iterator.next();
            if(feedback.getFeedbackId() == feedback.getFeedbackId()){
                feedbacks.add(feedback);
            }
        }
        return feedbacks;

    }

}
