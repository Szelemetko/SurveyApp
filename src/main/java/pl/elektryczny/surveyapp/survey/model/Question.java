package pl.elektryczny.surveyapp.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer id;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Survey survey;

    @Transient
    @JsonIgnore
    private Integer numOptions = 0;

    @NotNull
    private String question;

    @NotNull
    @Size(min = 2, max = 5)
    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Answer> answers;



    public void addOption(Answer answer) {
        this.numOptions++;
        answer.setId(this.numOptions);
        this.answers.add(answer);
        answer.setQuestion(this);
    }

    public void removeOption(Answer answer) {
        this.answers.remove(answer);
        answer.setQuestion(null);
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = new ArrayList<>();
        answers.forEach(this::addOption);
    }
}
