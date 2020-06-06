package pl.elektryczny.surveyapp.result.dto;

import lombok.Data;

import java.util.List;

@Data
public class SurveyDetailedResultsDto {
    Integer id;
    String name;
    List<QuestionDetailsDto> questions;
}
