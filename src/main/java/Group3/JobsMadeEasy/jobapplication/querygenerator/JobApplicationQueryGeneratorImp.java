package Group3.JobsMadeEasy.jobapplication.querygenerator;

import Group3.JobsMadeEasy.jobapplication.model.JobApplication;
import org.springframework.stereotype.Component;

import static Group3.JobsMadeEasy.jobapplication.querygenerator.JobApplicationConstant.*;


/**
 * @description This class is responsible for query generation for Job Application
 */
@Component
public class JobApplicationQueryGeneratorImp implements IJobApplicationQueryGenerator {

    private static JobApplicationQueryGeneratorImp obj;

    private JobApplicationQueryGeneratorImp() {
    }

    public static JobApplicationQueryGeneratorImp getInstance() {
        if (obj == null) {
            obj = new JobApplicationQueryGeneratorImp();
        }
        return obj;
    }

    public String createJobApplication(JobApplication jobApplication) {
        return "INSERT INTO "+ JOB_APPLICATION_TABLE +
                " (" +APPLICATION_ID_COLUMN+ "," + FIRST_NAME_COLUMN + "," +
                LAST_NAME_COLUMN +"," + EXPECTED_SALARY_COLUMN + "," +
                CURRENT_EMPLOYEE_STATUS_COLUMN + "," + YEAR_PASS_OUT_COLUMN + "," + APPLICATION_TYPE_COLUMN + "," +
                STUDY_FIELD_COLUMN + "," + DEGREE_TYPE_COLUMN + "," + UNIVERSITY_COLUMN + "," +
                EXPERT_ONE_COLUMN + "," + EXPERT_TWO_COLUMN + "," + EXPERT_THREE_COLUMN + "," + JOB_POST_ID_COLUMN + ")"+
                " VALUES (" +
                "\"" + jobApplication.getApplicationId() + "\"," +
                "\"" + jobApplication.getFirstName() + "\"," +
                "\"" + jobApplication.getLastName() + "\"," +
                "\"" + jobApplication.getExpectedSalary() + "\"," +
                "\"" + jobApplication.getCurrentEmployeeStatus() + "\"," +
                "\"" + jobApplication.getYearPassOut() + "\"," +
                "\"" + jobApplication.getApplicationType() + "\"," +
                "\"" + jobApplication.getStudyField() + "\"," +
                "\"" + jobApplication.getDegreeType() + "\"," +
                "\"" + jobApplication.getUniversity() + "\"," +
                "\"" + jobApplication.getExpertOne() + "\"," +
                "\"" + jobApplication.getExpertTwo() + "\"," +
                "\"" + jobApplication.getExpertThree() + "\"," +
                "\"" +jobApplication.getJobPostId()  + "\"" +

                ");";
    }



    @Override
    public String getJobApplications() {
        return "SELECT * FROM " + JOB_APPLICATION_TABLE +";";
    }




}
