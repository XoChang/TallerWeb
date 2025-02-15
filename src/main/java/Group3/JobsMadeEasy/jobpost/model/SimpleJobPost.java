package Group3.JobsMadeEasy.jobpost.model;

import Group3.JobsMadeEasy.jobpost.dao.IJobPostDao;

public class SimpleJobPost extends JobPost {
    public SimpleJobPost() {
        super();
    }

    public SimpleJobPost(IJobPostDao jobPostDao) {
        super(jobPostDao);
    }

    public SimpleJobPost(int jobPostId, String jobTitle, String salary, String jobType, String jobDescription,
                         String jobLocation, String languageRequirements) {
        super(jobPostId, jobTitle, salary, jobType, jobDescription, jobLocation, languageRequirements);
    }

}
