package pl.elektryczny.surveyapp.security.service;

import pl.elektryczny.surveyapp.security.model.User;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizationService {
    User authoriseCoordinatorRequest(HttpServletRequest request);
    User authoriseUserRequest(HttpServletRequest request);
}
