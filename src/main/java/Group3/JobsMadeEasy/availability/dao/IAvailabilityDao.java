package Group3.JobsMadeEasy.availability.dao;

import Group3.JobsMadeEasy.availability.model.Availability;
import Group3.JobsMadeEasy.availability.model.AvailabilityUserName;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IAvailabilityDao {
    String createAvailability(Availability availability, int id) throws JobsMadeEasyException;

    List<AvailabilityUserName> viewAllAvailability() throws JobsMadeEasyException, SQLException;

    List<Availability> viewAvailabilityById(int id) throws JobsMadeEasyException, SQLException;

    boolean deleteAvailabilityById(int id) throws JobsMadeEasyException;

    List<Availability> getAvailabilities(List<Availability> availabilities, ResultSet rs) throws SQLException;

}
