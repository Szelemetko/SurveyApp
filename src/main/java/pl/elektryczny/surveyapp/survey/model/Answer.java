package pl.elektryczny.surveyapp.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@IdClass(AnswerKey.class)
public class Answer {

    @Id
    private Integer number;

    @NotNull
    private String text;

    @JsonIgnore
    @ToString.Exclude
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "survey_id"),
            @JoinColumn(name = "question_number")
    })
    @NotNull
    private Question question;

}
