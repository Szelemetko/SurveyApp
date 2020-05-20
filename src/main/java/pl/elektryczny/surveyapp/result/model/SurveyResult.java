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
    Set<Reply> replies;

    public void addAnswer(Reply reply) {
        this.replies.add(reply);
        reply.setSurveyResult(this);
    }

    public void removeAnswer(Reply reply) {
        this.replies.remove(reply);
        reply.setSurveyResult(null);

    }
}
