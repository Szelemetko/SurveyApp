package pl.elektryczny.surveyapp.result.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SurveyResultKey implements Serializable {
    private Integer surveyResultId;
    private Integer surveyId;
}
