package pl.elektryczny.surveyapp.survey.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.elektryczny.surveyapp.survey.model.Survey;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Integer> {

    List<Survey> findAllByUserId(Long userId);
    Optional<Survey> findByIdAndUserId(int id, Long userId);

}
