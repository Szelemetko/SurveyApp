package pl.elektryczny.surveyapp.survey.service;

import pl.elektryczny.surveyapp.survey.model.Survey;

import java.util.List;

public interface SurveyService {
    List<Survey> getAllSurveys();
    Survey addSurvey(Survey survey);
    Survey updateSurvey(int id, Survey survey);
    Survey getSurveyById(int id);
    void deleteSurvey(int id);
}
