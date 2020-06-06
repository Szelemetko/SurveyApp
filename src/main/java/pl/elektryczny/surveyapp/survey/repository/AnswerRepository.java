package pl.elektryczny.surveyapp.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.elektryczny.surveyapp.survey.model.Answer;
import pl.elektryczny.surveyapp.survey.model.AnswerKey;

public interface AnswerRepository extends JpaRepository<Answer, AnswerKey> {
}
