package Group3.JobsMadeEasy.jobpost.model;

import Group3.JobsMadeEasy.jobpost.dao.IJobPostDao;

public class SimpleJobPostFactory implements IJobPostFactory {

    /**
     * @description This will allow to create Simple Job Posts that have no parameters.
     * @return
     */
    @Override
    public JobPost createJobPost() {
        return new SimpleJobPost();
    }

    /**
     * @description This will allow to create Simple Job Posts that takes jobPost as an argument.
     * @param jobPostDao
     * @return
     */
    @Override
    public JobPost createJobPost(IJobPostDao jobPostDao) {
        return new SimpleJobPost(jobPostDao);
    }
}
