package Group3.JobsMadeEasy.availability.querygenerator;

import Group3.JobsMadeEasy.availability.model.Availability;

public interface IAvailabilityQueryGenerator {
    String createAvailability (Availability availability);
    String viewAllAvailability();
    String viewAvailabilityById(int id);
    String deleteAvailabilityById(int id);

}
