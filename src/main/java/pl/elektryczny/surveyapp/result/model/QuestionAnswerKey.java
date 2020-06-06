package pl.elektryczny.surveyapp.result.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionAnswerKey implements Serializable {
    private Integer number;
    private SurveyResult surveyResult;
}
