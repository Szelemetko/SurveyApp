package pl.elektryczny.surveyapp.survey.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.elektryczny.surveyapp.survey.model.Survey;
import pl.elektryczny.surveyapp.survey.service.SurveyService;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path= {"/survey", "/surveys"})
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("")
    public List<Survey> getSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{surveyId}")
    public Survey getSurveyById(@PathVariable("surveyId") int surveyId) {
        return surveyService.getSurveyById(surveyId);
    }

    @PostMapping("")
    public Survey addSurvey(@RequestBody @Valid Survey survey) {
        return surveyService.addSurvey(survey);
    }

    @PutMapping("/{surveyId}")
    public Survey updateSurvey(@PathVariable("surveyId") int surveyId, @RequestBody @Valid Survey survey) {
        return surveyService.updateSurvey(surveyId, survey);
    }

    @DeleteMapping("/{surveyId}")
    public void deleteSurvey(@PathVariable("surveyId") int surveyId) {
        surveyService.deleteSurvey(surveyId);
    }
}
