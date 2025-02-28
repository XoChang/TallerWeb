package Group3.JobsMadeEasy.authentication.user.querygenerator.registration;

import Group3.JobsMadeEasy.authentication.user.model.User;

public interface IUserRegistrationQueryGenerator {
    String createUser(User user);
    String getUserById(int id);
    String getUsers();
    String deleteUserById(int id);
}
