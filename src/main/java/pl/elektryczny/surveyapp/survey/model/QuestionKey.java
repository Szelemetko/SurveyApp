package pl.elektryczny.surveyapp.survey.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionKey implements Serializable {
    private Integer number;
    private Survey survey;
}
