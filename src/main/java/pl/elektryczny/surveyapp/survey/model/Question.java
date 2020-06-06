package pl.elektryczny.surveyapp.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@IdClass(QuestionKey.class)
public class Question {

    @Id
    private Integer number;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    @JsonIgnore
    @ToString.Exclude
    @NotNull
    private Survey survey;

    @NotNull
    private String text;

    @NotNull
    @Size(min = 2, max = 7)
    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private List<Answer> answers;


    public void setAnswers(List<Answer> answers) {
        answers.forEach(answer -> answer.setQuestion(this));
        this.answers = new ArrayList<>(answers);

    }
}
