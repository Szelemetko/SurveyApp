package pl.elektryczny.surveyapp.survey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String question;

    @NotNull
    @Size(min = 2, max = 5)
    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Option> options;

    @ManyToOne(fetch = FetchType.LAZY)
    private Survey survey;

    public void addOption(Option option) {
        this.options.add(option);
        option.setQuestion(this);
    }

    public void removeOption(Option option) {
        this.options.remove(option);
        option.setQuestion(null);
    }
}
