package pl.elektryczny.surveyapp.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(AnswerKey.class)
public class Answer {

    @Id
    private Integer id;

    @NotNull
    private String answer;

    @JsonIgnore
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "survey_id", insertable = false, updatable = false),
            @JoinColumn(name = "question_id", insertable = false, updatable = false)
    })
    private Question question;

}
