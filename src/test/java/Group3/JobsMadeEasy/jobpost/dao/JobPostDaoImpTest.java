package Group3.JobsMadeEasy.jobpost.dao;

import Group3.JobsMadeEasy.jobpost.model.JobPost;
import Group3.JobsMadeEasy.jobpost.model.SimpleJobPost;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class JobPostDaoImpTest implements IJobPostDao {
    private final List<JobPost> mockDB;

    public JobPostDaoImpTest() {
        mockDB = new ArrayList<>();
        mockDB.add(new SimpleJobPost(11, "Java Developer", "100,000 CAD",
                "Full time", "Develop java application that interacts with user " +
                "and do some work on data. Write tests as well.",
                "Halifax", "English"));
        mockDB.add(new SimpleJobPost(22, "python developer",
                "80,000 CAD", "Part time", "Develop application using python " +
                "and write scripts to test other apps.", "Montreal",
                "French and English"));
    }

    @Override
    public String createJobPost(JobPost jobPost) {
        this.mockDB.add(jobPost);
        ListIterator<JobPost> listIterator = mockDB.listIterator();
        while (listIterator.hasNext()) {
            JobPost currentJobPost = listIterator.next();
            if (currentJobPost.getJobPostId() == jobPost.getJobPostId()) {
                return "jobPost";
            }
        }
        return "";
    }

    @Override
    public List<JobPost> getAllJobPost() {
        return mockDB;
    }

    @Override
    public Optional<JobPost> getJobPostById(int id) {
        ListIterator<JobPost> listIterator = mockDB.listIterator();
        while (listIterator.hasNext()) {
            JobPost currentJobPost = listIterator.next();
            if (currentJobPost.getJobPostId() == id) {
                return Optional.of(currentJobPost);
            }
        }
        return null;
    }

    @Override
    public boolean deleteJobPostById(int id) {
        ListIterator<JobPost> listIterator = mockDB.listIterator();
        while (listIterator.hasNext()) {
            JobPost currentJobPost = listIterator.next();
            if (currentJobPost.getJobPostId() == id) {
                return true;
            }
        }
        return false;
    }
}
