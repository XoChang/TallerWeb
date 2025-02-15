package Group3.JobsMadeEasy.authentication.user.model;

import Group3.JobsMadeEasy.authentication.user.dao.login.IUserLoginDao;
import Group3.JobsMadeEasy.authentication.user.dao.registration.IUserRegistrationDao;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static Group3.JobsMadeEasy.authentication.user.controller.login.UserLoginControllerConstant.LOGIN_WARNING;

/**
 * @description: It will handle all the properties of user and business logic for it.
 */
@Component
public class User {

    private IUserLoginDao userLoginDao;
    private IUserRegistrationDao userRegistrationDao;
    private int userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailId;
    private String password;
    private String city;
    private String province;
    private String address;
    private String postalCode;

    private int roleId;

    private boolean isEmployee;
    private boolean isApproved;



    public User(){
    }

    public User(int userId, String firstName, String lastName, String phoneNumber, String emailId, String password,
                String city, String province, String address, String postalCode, int roleId, boolean isEmployee,
                boolean isApproved) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.password = password;
        this.city = city;
        this.province = province;
        this.address = address;
        this.postalCode = postalCode;
        this.roleId = roleId;
        this.isEmployee = isEmployee;
        this.isApproved = isApproved;
    }

    public User(IUserLoginDao userLoginDao, IUserRegistrationDao userRegistrationDao) {
        this.userLoginDao = userLoginDao;
        this.userRegistrationDao = userRegistrationDao;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    /**
     *
     * @param login
     * @return it will check user's email and password for authentication and redirect on different page
     * @throws SQLException
     * @throws JobsMadeEasyException
     */
    public String checkLoginDetails(Login login) throws SQLException, JobsMadeEasyException {
        String user = this.userLoginDao.checkLoginDetails(login);
        if(user == null){
            return LOGIN_WARNING;
        }else{
            return checkCurrentUserRole(user);
        }
    }

    /**
     *
     * @param roleName
     * @return it will redirect user according to role
     */
    public String checkCurrentUserRole(String roleName) {
        return roleName + "HomePage";
    }

    /**
     *
     * @param user
     * @return redirect user after completing register
     * @throws JobsMadeEasyException
     */
    public String createUser(User user) throws JobsMadeEasyException {
        if (user == null) {
            throw new JobsMadeEasyException("user register details not found..");
        }else{
            user.setUserId(GenerateIdUtil.Object().generateRandomId());
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setPhoneNumber(user.getPhoneNumber());
            user.setEmailId(user.getEmailId());
            user.setPassword(user.getPassword());
            user.setCity(user.getCity());
            user.setProvince(user.getProvince());
            user.setAddress(user.getAddress());
            user.setPostalCode(user.getPostalCode());
            user.setRoleId(1);
            user.setEmployee(false);
            user.setApproved(false);
        }
        return this.userRegistrationDao.createUser(user);
    }

    /**
     *
     * @param id
     * @return return user of same id
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    public Optional<User> getUserById(int id) throws JobsMadeEasyException, SQLException {
        if (id == 0) {
            throw new JobsMadeEasyException("No User found in DB");
        }
        return this.userRegistrationDao.getUserById(id);
    }

    /**
     *
     * @return list of users
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    public List<User> getAllUsers() throws SQLException, JobsMadeEasyException {
        return this.userRegistrationDao.getUsers();
    }

    /**
     *
     * @param id
     * @return true or false according to delete operation success or failure
     * @throws JobsMadeEasyException
     * @throws SQLException
     */
    public boolean deleteUserById(int id) throws SQLException, JobsMadeEasyException {
        if (this.getUserById(id).isPresent())
        {
            return this.userRegistrationDao.deleteUserById(id);
        }
        return false;
    }

    /**
     *
     * @return it will perform logout and clean all the session values
     */
    public String logout() {
        return this.userLoginDao.logout();
    }
}
