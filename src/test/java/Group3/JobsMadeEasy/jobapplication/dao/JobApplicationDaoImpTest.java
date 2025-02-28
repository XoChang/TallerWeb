package Group3.JobsMadeEasy.jobapplication.dao;

import Group3.JobsMadeEasy.jobapplication.model.JobApplication;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class JobApplicationDaoImpTest implements IJobApplicationDao {
    private final List<JobApplication> mockDB;

    public JobApplicationDaoImpTest(){
        mockDB = new ArrayList<>();
        mockDB.add(new JobApplication(1234, "Neha",
                "Karkhanis", 20000, "Student",2020,"part-time",
                "Engineering","Masters","Dalhousie",
                "Data Science","maths","Java",89));
        mockDB.add(new JobApplication(2345, "Neha",
                "Karkhanis", 19090, "Employed",2022,"full-time",
                "Marketing","Bachelor","Lancaster",
                "market","management","commerce",1234));
    }

    @Override
    public String createJobApplication(JobApplication jobApplication) {
        this.mockDB.add(jobApplication);
        ListIterator<JobApplication> iterator = mockDB.listIterator();
        while (iterator.hasNext()){
            JobApplication jobApplication1 = iterator.next();
            if(jobApplication1.getApplicationId() == jobApplication.getApplicationId())
            {
                return "register";
            }
        }
        return "";
    }



    @Override
    public List<JobApplication> getJobApplication(){
        return mockDB;
    }



}
