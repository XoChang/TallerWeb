package Group3.JobsMadeEasy.jobpost.model;

import Group3.JobsMadeEasy.jobpost.dao.IJobPostDao;

public interface IJobPostFactory {
    JobPost createJobPost();

    JobPost createJobPost(IJobPostDao jobPostDao);

}
