package pl.elektryczny.surveyapp.result.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(QuestionAnswerKey.class)
public class QuestionAnswer {

    @Id
    private Integer number;

    private Integer chosenAnswer;


    @JsonIgnore
    @ToString.Exclude
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "survey_id"),
            @JoinColumn(name = "survey_result_id")
    })
    @NotNull
    private SurveyResult surveyResult;
}
