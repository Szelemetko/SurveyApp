package pl.elektryczny.surveyapp.survey.repository;

import org.springframework.data.repository.Repository;
import pl.elektryczny.surveyapp.survey.model.Question;

public interface QuestionRepository extends Repository<Question, Integer> {
}
