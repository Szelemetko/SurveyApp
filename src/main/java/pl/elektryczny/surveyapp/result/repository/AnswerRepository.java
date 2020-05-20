package pl.elektryczny.surveyapp.result.repository;

import org.springframework.data.repository.Repository;
import pl.elektryczny.surveyapp.result.model.Reply;

public interface AnswerRepository extends Repository<Reply, Integer> {
}
