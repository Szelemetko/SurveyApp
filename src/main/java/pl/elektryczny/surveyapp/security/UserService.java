package pl.elektryczny.surveyapp.security;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService  {

    User save(User user);
    User findByEmail(String email);
}
