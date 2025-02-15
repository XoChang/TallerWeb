package Group3.JobsMadeEasy.availability.model;

import Group3.JobsMadeEasy.availability.dao.IAvailabilityDao;


public class AvailabilityUserName {
    /**
     * @description: This class contains user's first name, last name and availability.
     */

    private IAvailabilityDao availabilityDao;
    private int userId;
    private String firstName;
    private String lastName;
    private int availableDays;
    private String availableHours;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(int availableDays) {
        this.availableDays = availableDays;
    }

    public String getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(String availableHours) {
        this.availableHours = availableHours;
    }

    public AvailabilityUserName(IAvailabilityDao availabilityDao) {
        this.availabilityDao = availabilityDao;
    }

    public AvailabilityUserName() {
    }

    public AvailabilityUserName(int userId, String firstName, String lastName, int availableDays, String availableHours)
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.availableDays = availableDays;
        this.availableHours = availableHours;
    }
}
