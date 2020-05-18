package pl.elektryczny.surveyapp.survey.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.elektryczny.surveyapp.survey.model.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Integer> {


}
