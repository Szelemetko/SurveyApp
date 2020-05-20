package pl.elektryczny.surveyapp.survey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean active;

    @Transient
    @JsonIgnore
    private Integer numQuestions = 0;

    @NotNull
    @OneToMany(
            mappedBy = "survey",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private List<Question> questions;

    private void addQuestion(Question question) {
        this.numQuestions++;
        question.setId(this.numQuestions);
        this.questions.add(question);
        question.setSurvey(this);
    }

    private void removeQuestion(Question question) {
        this.questions.remove(question);
        question.setSurvey(null);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = new ArrayList<>();

        questions.forEach(this::addQuestion);
    }

}
