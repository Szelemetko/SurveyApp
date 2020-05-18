package pl.elektryczny.surveyapp.result.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "survey_result")
@Entity
public class SurveyResult{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @OneToMany(
            mappedBy = "surveyResult",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set<Answer> answers;

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
        answer.setSurveyResult(this);
    }

    public void removeAnswer(Answer answer) {
        this.answers.remove(answer);
        answer.setSurveyResult(null);

    }
}
