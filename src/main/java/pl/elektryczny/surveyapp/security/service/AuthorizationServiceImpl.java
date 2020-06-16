package pl.elektryczny.surveyapp.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.elektryczny.surveyapp.security.model.User;
import pl.elektryczny.surveyapp.security.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final UserRepository userRepository;

    @Override
    public  User authoriseCoordinatorRequest(HttpServletRequest request) {
        Cookie cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("SurveyAppSession")).findFirst().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED)
        );
        User user = userRepository.findById(Long.valueOf(cookie.getValue())).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "user does not exist")
        );

        if (!user.getIsCoordinator()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "user is not coordinator");
        }
        return user;
    }

    @Override
    public User authoriseUserRequest(HttpServletRequest request) {

        Cookie cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("SurveyAppSession")).findFirst().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED)
        );
        User user = userRepository.findById(Long.valueOf(cookie.getValue())).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "user does not exist")
        );
        return user;
    }
}
