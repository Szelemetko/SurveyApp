package pl.elektryczny.surveyapp.result.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.elektryczny.surveyapp.survey.model.Question;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "survey_result")
@IdClass(SurveyResultKey.class)
public class SurveyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer surveyResultId;

    @Id
    @JsonProperty("id")
    private Integer surveyId;

    @NotNull
    @OneToMany(
            mappedBy = "surveyResult",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    List<QuestionAnswer> questions;

    public void setQuestions(List<QuestionAnswer> questions) {
        questions.forEach(question -> question.setSurveyResult(this));
        this.questions = new ArrayList<>(questions);
    }

}
