package pl.elektryczny.surveyapp.security;

public interface UserAuthenticationService {

    User login(User user);
    User logout(User user);
    User findByToken(String token);

}
