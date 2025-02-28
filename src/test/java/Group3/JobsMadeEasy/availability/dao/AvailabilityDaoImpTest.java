package Group3.JobsMadeEasy.availability.dao;

import Group3.JobsMadeEasy.availability.model.Availability;
import Group3.JobsMadeEasy.availability.model.AvailabilityUserName;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class AvailabilityDaoImpTest implements IAvailabilityDao{
    private final List<Availability> mockDB;
    private final List<AvailabilityUserName>  mockDB2;

    public AvailabilityDaoImpTest() {
        mockDB = new ArrayList<>();
        mockDB2 = new ArrayList<>();
        mockDB.add(new Availability(1,2, "Monday 4", 5));
        mockDB.add(new Availability(2, 1, "Monday 5", 3));
        mockDB2.add(new AvailabilityUserName(1, "Pranay", "Raycha", 1, "Monday 5"));
    }

    @Override
    public String createAvailability(Availability availability, int id) throws JobsMadeEasyException {
        this.mockDB.add(availability);
        ListIterator<Availability> iterator = mockDB.listIterator();
        while (iterator.hasNext()){
            Availability currentAvailability = iterator.next();
            if(currentAvailability.getAvailabilityId() == availability.getAvailabilityId() && currentAvailability.getUserId()==id){
              return  "availabilityHomePage";
            }
        }
        return "";
    }

    @Override
    public List<AvailabilityUserName> viewAllAvailability() throws JobsMadeEasyException, SQLException {
        return mockDB2;
    }

    @Override
    public List<Availability> viewAvailabilityById(int id) throws JobsMadeEasyException, SQLException {
        ListIterator<Availability> iterator = mockDB.listIterator();
        List<Availability> result=new ArrayList<>();
        while (iterator.hasNext()){
            Availability currentAvailability = iterator.next();
            if(currentAvailability.getUserId() == id){
                result.add(currentAvailability);
            }
        }
        return result;

    }

    @Override
    public boolean deleteAvailabilityById(int id) throws JobsMadeEasyException {
        ListIterator<Availability> iterator = mockDB.listIterator();
        while (iterator.hasNext()){
            Availability currentAvailability = iterator.next();
            if(currentAvailability.getAvailabilityId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Availability> getAvailabilities(List<Availability> availabilities, ResultSet rs) throws SQLException {

            while(rs.next()){
                int availabilityId = rs.getInt("availabilityId");
                int availableDays = rs.getInt("availableDays");
                String availableHours = rs.getString("availableHours");
                int userId = rs.getInt("userId");

                Availability availability = new Availability(availabilityId,availableDays,availableHours,userId);
                availabilities.add(availability);
            }
            return availabilities;
        }

    }




