package pl.elektryczny.surveyapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User save(User user) {
        repository.findById(user.getEmail()).ifPresent(s -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        });
        return repository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
