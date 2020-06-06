package pl.elektryczny.surveyapp.result.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.stereotype.Service;
import pl.elektryczny.surveyapp.result.dto.AnswerDetailsDto;
import pl.elektryczny.surveyapp.result.dto.QuestionDetailsDto;
import pl.elektryczny.surveyapp.result.dto.SurveyDetailedResultsDto;
import pl.elektryczny.surveyapp.result.model.QuestionAnswer;
import pl.elektryczny.surveyapp.result.model.SurveyResult;
import pl.elektryczny.surveyapp.result.repository.SurveyResultRepository;
import pl.elektryczny.surveyapp.survey.model.Survey;
import pl.elektryczny.surveyapp.survey.service.SurveyService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@RequiredArgsConstructor
@Service
public class SurveyResultServiceImpl implements SurveyResultService {

    private final SurveyResultRepository repository;
    private final SurveyService surveyService;

    @Override
    public SurveyDetailedResultsDto getSurveyResults(Integer surveyId) {
        SurveyDetailedResultsDto detailedResults = new SurveyDetailedResultsDto();
        List<SurveyResult> results = repository.findSurveyResultsBySurveyId(surveyId);
        Survey survey = surveyService.getSurveyById(surveyId);



        detailedResults.setId(surveyId);
        detailedResults.setName(survey.getName());
        detailedResults.setQuestions(new ArrayList<>());

        Map<Integer, List<QuestionAnswer>> questionMap = results.stream()
                .flatMap(surveyResult -> surveyResult.getQuestions().stream())
                .collect(groupingBy(QuestionAnswer::getNumber));



        questionMap.forEach((questionNumber, questionAnswerList) -> {
                    QuestionDetailsDto questionDetails = new QuestionDetailsDto();
                    questionDetails.setNumber(questionNumber);
                    String questionText = survey.getQuestions().stream()
                            .filter(question -> question.getNumber().equals(questionNumber))
                            .findAny()
                            .get()
                            .getText();
                    questionDetails.setQuestionText(questionText);
                    questionDetails.setTotalNumberOfRespondents(questionAnswerList.size());
                    detailedResults.getQuestions().add(questionDetails);

                    questionDetails.setAnswers(new ArrayList<>());

                    Map<Integer, Long> answerList = questionAnswerList.stream().collect(
                            groupingBy(QuestionAnswer::getChosenAnswer, Collectors.counting())
                    );
                    answerList.forEach((answerNumber, respondents) -> {
                        AnswerDetailsDto answerDetails = new AnswerDetailsDto();
                        answerDetails.setNumber(answerNumber);
                        answerDetails.setNumberOfRespondents(respondents.intValue());
                        String answerText = survey.getQuestions().stream()
                                .filter(question -> question.getNumber().equals(questionNumber))
                                .flatMap(question -> question.getAnswers().stream())
                                .filter(answer -> answer.getNumber().equals(answerNumber))
                                .findFirst()
                                .get()
                                .getText();
                        answerDetails.setAnswerText(answerText);
                        questionDetails.getAnswers().add(answerDetails);
                    });


                });



        return detailedResults;
    }

    @Override
    public SurveyResult addSurveyResult(SurveyResult survey) {
        return repository.save(survey);
    }

}
