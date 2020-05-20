package pl.elektryczny.surveyapp.survey.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Data
public class AnswerKey implements Serializable {
    private Integer id;
    private Question question;
}
