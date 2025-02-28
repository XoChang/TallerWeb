package Group3.JobsMadeEasy.availability.dao;

import Group3.JobsMadeEasy.availability.model.Availability;
import Group3.JobsMadeEasy.availability.model.AvailabilityUserName;
import Group3.JobsMadeEasy.availability.querygenerator.IAvailabilityQueryGenerator;
import Group3.JobsMadeEasy.database.dao.DatabaseSetup;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import static Group3.JobsMadeEasy.availability.controller.AvailabilityControllerConstant.ADD_AVAILABILITY;
import static Group3.JobsMadeEasy.availability.controller.AvailabilityControllerConstant.AVAILABILITY_HOME_PAGE;

/**
 * @description: Database Layer: it will handle all the queries related to database for the availability model.
 */
@Component
public class AvailabilityDaoImp implements IAvailabilityDao {

    private final IAvailabilityQueryGenerator availabilityQueryGenerator;
    private final DatabaseSetup databaseSetup;
    private final Connection connection;
    private final Statement statement;

    /**
     * It will set up the Database Connection.
     */
    public AvailabilityDaoImp(IAvailabilityQueryGenerator availabilityQueryGenerator, DatabaseSetup databaseSetup)
            throws SQLException, IOException, ClassNotFoundException {
        this.availabilityQueryGenerator = availabilityQueryGenerator;
        this.databaseSetup = databaseSetup;
        this.connection = databaseSetup.getConnectionObject();
        this.statement = connection.createStatement();
    }

    /**
     * @param availability: model availability to add details in it.
     * @param id:           it is an id of current user
     * @return It will return availability.
     * @throws JobsMadeEasyException
     */
    @Override
    public String createAvailability(Availability availability, int id) throws JobsMadeEasyException {

        try {
            String createUserQuery = availabilityQueryGenerator.createAvailability(availability);
            int updatedRows = statement.executeUpdate(createUserQuery, Statement.RETURN_GENERATED_KEYS);
            if (updatedRows > 0) {
                return AVAILABILITY_HOME_PAGE;
            }
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
        }
        return ADD_AVAILABILITY;
    }

    /**
     * @throws JobsMadeEasyException
     * @throws SQLException
     * @return: It will return the availabilities of all applicant.
     */

    @Override
    public List<AvailabilityUserName> viewAllAvailability() throws JobsMadeEasyException, SQLException {

        ResultSet rs = null;
        try {
            String viewAllAvailabilityQuery = availabilityQueryGenerator.viewAllAvailability();
            rs = statement.executeQuery(viewAllAvailabilityQuery);
            List<AvailabilityUserName> availabilities = new LinkedList<>();
            while (rs.next()) {
                int userId = rs.getInt("userId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int availableDays = rs.getInt("availableDays");
                String availableHours = rs.getString("availableHours");
                AvailabilityUserName availability = new AvailabilityUserName(userId, firstName, lastName, availableDays,
                        availableHours);
                availabilities.add(availability);
            }
            return availabilities;
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
            rs.close();
        }
    }

    /**
     * @param id: id of current user
     * @return It will return the availabilities of current user.
     * @throws JobsMadeEasyException
     * @throws SQLException
     */

    @Override
    public List<Availability> viewAvailabilityById(int id) throws JobsMadeEasyException, SQLException {
        List<Availability> availabilities = null;
        ResultSet rs = null;
        try {
            String viewAvailabilityByIdQuery = availabilityQueryGenerator.viewAvailabilityById(id);
            rs = statement.executeQuery(viewAvailabilityByIdQuery);
            availabilities = new LinkedList<>();
            return getAvailabilities(availabilities, rs);

        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();
            rs.close();
        }
    }

    /**
     * @param id: id of current user
     * @return It will return true once the entry is deleted.
     * @throws JobsMadeEasyException
     */

    @Override
    public boolean deleteAvailabilityById(int id) throws JobsMadeEasyException {
        try {
            String deleteAvailabilityByIdQuery = availabilityQueryGenerator.deleteAvailabilityById(id);
            statement.executeUpdate(deleteAvailabilityByIdQuery);
            return true;
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        } finally {
            databaseSetup.closeDatabaseConnection();

        }
    }

    /**
     * @param availabilities: list of availabilities
     * @param rs:             it is a generated result set
     * @return It will return the list of availabilities.
     * @throws SQLException
     */

    public List<Availability> getAvailabilities(List<Availability> availabilities, ResultSet rs) throws SQLException {
        while (rs.next()) {
            int availabilityId = rs.getInt("availabilityId");
            int availableDays = rs.getInt("availableDays");
            String availableHours = rs.getString("availableHours");
            int userId = rs.getInt("userId");

            Availability availability = new Availability(availabilityId, availableDays, availableHours, userId);
            availabilities.add(availability);
        }
        return availabilities;
    }
}
