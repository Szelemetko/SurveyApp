package pl.elektryczny.surveyapp.result.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.elektryczny.surveyapp.result.dto.SurveyDetailedResultsDto;
import pl.elektryczny.surveyapp.result.model.SurveyResult;
import pl.elektryczny.surveyapp.result.service.SurveyResultService;
import pl.elektryczny.surveyapp.security.model.User;
import pl.elektryczny.surveyapp.security.service.AuthorizationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:3000", allowedHeaders = "*")
public class SurveyResultController {

    private final SurveyResultService surveyResultService;
    private final AuthorizationService authorizationService;

    @PostMapping("/surveys/{surveyId}/answers")
    public SurveyResult addSurvey(@PathVariable("surveyId") int surveyId, @RequestBody @Valid SurveyResult surveyResult,  HttpServletRequest request) {
        authorizationService.authoriseUserRequest(request);
        return surveyResultService.addSurveyResult(surveyResult);
    }

    @GetMapping("/surveys/{surveyId}/answers")
    public SurveyDetailedResultsDto getSurveyResults(@PathVariable("surveyId") int surveyId, HttpServletRequest request) {
        User user = authorizationService.authoriseUserRequest(request);
        return surveyResultService.getSurveyResults(surveyId, user);
    }


}
