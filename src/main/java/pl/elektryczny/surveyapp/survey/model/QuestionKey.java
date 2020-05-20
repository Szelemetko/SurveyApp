package pl.elektryczny.surveyapp.survey.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Data
public class QuestionKey implements Serializable {
    private Integer id;
    private Survey survey;
}
