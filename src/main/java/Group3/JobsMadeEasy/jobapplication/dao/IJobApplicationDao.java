package Group3.JobsMadeEasy.jobapplication.dao;

import Group3.JobsMadeEasy.jobapplication.model.JobApplication;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;

import java.sql.SQLException;
import java.util.List;

public interface IJobApplicationDao {

    String createJobApplication(JobApplication jobApplication) throws JobsMadeEasyException, SQLException;

    List<JobApplication> getJobApplication() throws JobsMadeEasyException, SQLException;


}
