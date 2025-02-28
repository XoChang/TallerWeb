package Group3.JobsMadeEasy.jobpost.jobpostquerygenerator;

import Group3.JobsMadeEasy.jobpost.model.JobPost;

public interface IJobPostQueryGenerator {
    String createJobPost(JobPost jobPost);

    String viewAllJobs();

    String viewJobById(int id);

    String deleteJobById(int id);
}
