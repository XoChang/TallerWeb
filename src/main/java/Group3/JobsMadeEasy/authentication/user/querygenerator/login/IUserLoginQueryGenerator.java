package Group3.JobsMadeEasy.authentication.user.querygenerator.login;

import Group3.JobsMadeEasy.authentication.user.model.Login;

public interface IUserLoginQueryGenerator {
    String checkLoginDetails(Login login);
}
