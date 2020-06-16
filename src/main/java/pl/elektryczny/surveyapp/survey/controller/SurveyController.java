package pl.elektryczny.surveyapp.survey.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.elektryczny.surveyapp.security.model.User;
import pl.elektryczny.surveyapp.security.service.AuthorizationService;
import pl.elektryczny.surveyapp.survey.model.Survey;
import pl.elektryczny.surveyapp.survey.service.SurveyService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = {"/survey", "/surveys"})
public class SurveyController {

    private final SurveyService surveyService;
    private final AuthorizationService authorizationService;

    @GetMapping("")
    public List<Survey> getSurveys(HttpServletRequest request) {
        User user = authorizationService.authoriseUserRequest(request);
        return surveyService.getAllSurveys(user);
    }

    @GetMapping("/{surveyId}")
    public Survey getSurveyById(@PathVariable("surveyId") int surveyId, HttpServletRequest request) {
        User user = authorizationService.authoriseUserRequest(request);
        return surveyService.getSurveyById(surveyId, user);
    }

    @PostMapping("")
    public Survey addSurvey(@RequestBody @Valid Survey survey, HttpServletRequest request) {
        User user = authorizationService.authoriseCoordinatorRequest(request);
        return surveyService.addSurvey(survey, user);
    }

    @PutMapping("/{surveyId}")
    public Survey updateSurvey(@PathVariable("surveyId") int surveyId, @RequestBody @Valid Survey survey, HttpServletRequest request) {
        User user = authorizationService.authoriseCoordinatorRequest(request);
        return surveyService.updateSurvey(surveyId, survey, user);
    }

    @DeleteMapping("/{surveyId}")
    public void deleteSurvey(@PathVariable("surveyId") int surveyId, HttpServletRequest request) {
        User user = authorizationService.authoriseCoordinatorRequest(request);
        surveyService.deleteSurvey(surveyId, user);
    }


}
