package pl.elektryczny.surveyapp.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.elektryczny.surveyapp.result.model.SurveyResult;

import java.util.List;

public interface SurveyResultRepository extends JpaRepository<SurveyResult, Integer> {

    List<SurveyResult> findSurveyResultsBySurveyId(Integer id);

}
