package pl.elektryczny.surveyapp.result.service;

import pl.elektryczny.surveyapp.result.dto.SurveyDetailedResultsDto;
import pl.elektryczny.surveyapp.result.model.SurveyResult;
import pl.elektryczny.surveyapp.security.model.User;

public interface SurveyResultService {

    SurveyDetailedResultsDto getSurveyResults(Integer surveyId, User user);

    SurveyResult addSurveyResult(SurveyResult survey);
}
