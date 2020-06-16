package pl.elektryczny.surveyapp.survey.service;

import pl.elektryczny.surveyapp.security.model.User;
import pl.elektryczny.surveyapp.survey.model.Survey;

import java.util.List;

public interface SurveyService {
    List<Survey> getAllSurveys(User user);
    Survey addSurvey(Survey survey, User user);
    Survey updateSurvey(int id, Survey survey, User user);
    Survey getSurveyById(int id, User user);
    void deleteSurvey(int id, User user);
}
