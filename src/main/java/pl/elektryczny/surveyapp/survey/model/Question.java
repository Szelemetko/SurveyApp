package pl.elektryczny.surveyapp.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@IdClass(QuestionKey.class)
public class Question {

    @Id
    private Integer number;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    @JsonIgnore
    @NotNull
    private Survey survey;

    @NotNull
    private String text;

    @NotNull
    @Size(min = 2, max = 5)
    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Answer> answers;


    public void setAnswers(List<Answer> answers) {
        answers.forEach(answer -> answer.setQuestion(this));
        this.answers = new ArrayList<>(answers);

    }
}
