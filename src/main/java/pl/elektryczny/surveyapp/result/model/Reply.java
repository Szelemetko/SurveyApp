package pl.elektryczny.surveyapp.result.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.elektryczny.surveyapp.survey.model.Question;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Question question;

    @OneToOne
    private Reply reply;

    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyResult surveyResult;

}
