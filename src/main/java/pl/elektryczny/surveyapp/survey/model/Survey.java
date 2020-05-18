package pl.elektryczny.surveyapp.survey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private boolean active;

    @NotNull
    @OneToMany(
            mappedBy = "survey",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private Set<Question> questions;

    private void addQuestion(Question question) {
        this.questions.add(question);
        question.setSurvey(this);
    }

    private void removeQuestion(Question question) {
        this.questions.remove(question);
        question.setSurvey(null);
    }

}
