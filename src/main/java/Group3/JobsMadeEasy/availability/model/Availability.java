package Group3.JobsMadeEasy.availability.model;

import Group3.JobsMadeEasy.availability.dao.IAvailabilityDao;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;

import java.sql.SQLException;
import java.util.List;

/**
 * @description: This is model availability which contains business logic
 */

public class Availability {
    private int availabilityId;
    private int availableDays;
    private String availableHours;
    private int userId;
    private IAvailabilityDao availabilityDao;

    public Availability(int availabilityId, int availableDays, String availableHours, int userId) {
        this.availabilityId = availabilityId;
        this.availableDays = availableDays;
        this.availableHours = availableHours;
        this.userId = userId;
    }
    public Availability(IAvailabilityDao availabilityDao) {
        this.availabilityDao = availabilityDao;
    }

    public Availability() {

    }

    public int getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public int getAvailableDays() {
        return availableDays;
    }
    public void setAvailableDays(Integer availableDays) {
        this.availableDays = availableDays;
    }

    public String getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(String availableHours) {
        this.availableHours = availableHours;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @param availability: model availability to add details in it.
     * @param id:           it is an id of current user
     * @return It will return availability.
     * @throws JobsMadeEasyException
     */

    public String createAvailability(Availability availability, int id) throws JobsMadeEasyException {
        if (availability == null) {
            throw new JobsMadeEasyException("no such availability found");
        } else {
            availability.setAvailabilityId(GenerateIdUtil.Object().generateRandomId());
            availability.setAvailableDays(availability.getAvailableDays());
            availability.setAvailableHours(availability.getAvailableHours());
            availability.setUserId(id);
        }

        return this.availabilityDao.createAvailability(availability, id);
    }

    /**
     * @return It will return the availabilities of all applicant.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */

    public List<AvailabilityUserName> getAllAvailability() throws JobsMadeEasyException, SQLException {
        return this.availabilityDao.viewAllAvailability();

    }

    /**
     * @param id: id of current user
     * @return It will return the availabilities of current user.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */

    public List<Availability> getMyAvailability(int id) throws JobsMadeEasyException, SQLException {

        if (id == 0) {
            throw new JobsMadeEasyException("No Availability found in DB");
        }
        return availabilityDao.viewAvailabilityById(id);

    }

    /**
     * @param id: id of current user
     * @return It will return true once the entry is deleted.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */

    public boolean deleteAvailabilityById(int id) throws JobsMadeEasyException, SQLException {

        if (id == 0) {
            throw new JobsMadeEasyException("No Availability found in DB");
        }
        return availabilityDao.deleteAvailabilityById(id);
    }

}
