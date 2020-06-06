package pl.elektryczny.surveyapp.result.service;

import pl.elektryczny.surveyapp.result.dto.SurveyDetailedResultsDto;
import pl.elektryczny.surveyapp.result.model.SurveyResult;

public interface SurveyResultService {

    SurveyDetailedResultsDto getSurveyResults(Integer surveyId);

    SurveyResult addSurveyResult(SurveyResult survey);
}
