package pl.elektryczny.surveyapp.result.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.elektryczny.surveyapp.result.dto.SurveyDetailedResultsDto;
import pl.elektryczny.surveyapp.result.model.SurveyResult;
import pl.elektryczny.surveyapp.result.service.SurveyResultService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SurveyResultController {

    private final SurveyResultService surveyResultService;

    @PostMapping("/surveys/{surveyId}/answers")
    public SurveyResult addSurvey(@PathVariable("surveyId") int surveyId, @RequestBody @Valid SurveyResult surveyResult) {
        return surveyResultService.addSurveyResult(surveyResult);
    }

    @GetMapping("/surveys/{surveyId}/answers")
    public SurveyDetailedResultsDto getSurveyResults(@PathVariable("surveyId") int surveyId) {
        return surveyResultService.getSurveyResults(surveyId);
    }


}
