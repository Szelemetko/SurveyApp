package pl.elektryczny.surveyapp.survey.repository;

import org.springframework.data.repository.Repository;
import pl.elektryczny.surveyapp.survey.model.Answer;

public interface OptionRepository extends Repository<Answer, Integer> {
}
