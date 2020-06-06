package pl.elektryczny.surveyapp.survey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.elektryczny.surveyapp.survey.model.Survey;
import pl.elektryczny.surveyapp.survey.repository.SurveyRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository repository;

    @Override
    public List<Survey> getAllSurveys() {
        return repository.findAll();
    }

    @Override
    public Survey addSurvey(Survey survey) {
        return repository.save(survey);
    }

    @Override
    public Survey updateSurvey(int id, Survey survey) {
        Survey oldSurvey = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        repository.delete(oldSurvey);
        survey.setId(id);
        return repository.save(survey);
    }

    @Override
    public Survey getSurveyById(int id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteSurvey(int id) {
        Survey oldSurvey = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(oldSurvey);
    }
}
