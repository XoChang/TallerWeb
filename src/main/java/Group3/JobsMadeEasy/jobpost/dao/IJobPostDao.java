package Group3.JobsMadeEasy.jobpost.dao;

import Group3.JobsMadeEasy.jobpost.model.JobPost;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IJobPostDao {
    String createJobPost(JobPost jobPost) throws JobsMadeEasyException;

    List<JobPost> getAllJobPost() throws JobsMadeEasyException;

    Optional<JobPost> getJobPostById(int id) throws JobsMadeEasyException, SQLException;

    boolean deleteJobPostById(int id) throws JobsMadeEasyException;
}
