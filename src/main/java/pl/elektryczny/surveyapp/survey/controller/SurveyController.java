package pl.elektryczny.surveyapp.survey.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.elektryczny.surveyapp.survey.model.Survey;
import pl.elektryczny.surveyapp.survey.repository.SurveyRepository;
import pl.elektryczny.surveyapp.survey.service.SurveyService;


import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RequiredArgsConstructor
@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/{surveyId}")
    public Survey getSurveyById(@PathVariable("surveyId") int surveyId) {
        return surveyService.getSurveyById(surveyId);
    }

    @PostMapping("")
    public Survey addSurvey(@RequestBody @Valid Survey survey) {
        return surveyService.addSurvey(survey);
    }

    @PutMapping("/{surveyId}")
    public Survey updateSurvey(@PathVariable("surveyId") int surveyId, @Valid Survey survey) {
        return surveyService.updateSurvey(surveyId, survey);
    }

    @DeleteMapping("/{surveyId}")
    public void deleteSurvey(@PathVariable("surveyId") int surveyId) {
        surveyService.deleteSurvey(surveyId);
    }
}
