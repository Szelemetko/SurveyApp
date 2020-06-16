package pl.elektryczny.surveyapp.survey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.elektryczny.surveyapp.security.model.User;
import pl.elektryczny.surveyapp.survey.model.Survey;
import pl.elektryczny.surveyapp.survey.repository.SurveyRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository repository;

    @Override
    public List<Survey> getAllSurveys(User user) {
        return repository.findAll();
    }

    @Override
    public Survey addSurvey(Survey survey, User user) {
        survey.setUserId(user.getId());
        return repository.save(survey);
    }

    @Override
    public Survey updateSurvey(int id, Survey survey, User user) {
        Survey oldSurvey = repository.findByIdAndUserId(id, user.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        survey.setId(oldSurvey.getId());
        return repository.save(survey);
    }

    @Override
    public Survey getSurveyById(int id, User user) {
        Survey survey = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        survey.setQuestions(survey.getQuestions().stream().distinct().collect(Collectors.toList()));
        return survey;
    }

    @Override
    public void deleteSurvey(int id, User user) {
        Survey oldSurvey = repository.findByIdAndUserId(id, user.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(oldSurvey);
    }
}
