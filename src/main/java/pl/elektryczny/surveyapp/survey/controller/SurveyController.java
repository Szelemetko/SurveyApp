package pl.elektryczny.surveyapp.survey.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.elektryczny.surveyapp.survey.model.Survey;
import pl.elektryczny.surveyapp.survey.service.SurveyService;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@CrossOrigin
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
