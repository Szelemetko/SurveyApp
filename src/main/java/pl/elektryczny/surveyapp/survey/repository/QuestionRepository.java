package pl.elektryczny.surveyapp.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.elektryczny.surveyapp.survey.model.Question;
import pl.elektryczny.surveyapp.survey.model.QuestionKey;

public interface QuestionRepository extends JpaRepository<Question, QuestionKey> {
}
