package pl.elektryczny.surveyapp.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.elektryczny.surveyapp.security.model.User;
import pl.elektryczny.surveyapp.security.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;


@RestController
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, HttpServletResponse response) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        User savedUser = userRepository.save(user);

        Cookie cookie = new Cookie("SurveyAppSession", savedUser.getId().toString());
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response) {

        User savedUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!user.getPassword().equals(savedUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password incorrect");
        }
        Cookie cookie = new Cookie("SurveyAppSession", savedUser.getId().toString());
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletRequest response) {
        Cookie cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("SurveyAppSession")).findFirst().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST)
        );
        cookie.setMaxAge(0);
        return ResponseEntity.ok().build();
    }
}
