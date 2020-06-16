package pl.elektryczny.surveyapp.result.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.elektryczny.surveyapp.result.dto.AnswerDetailsDto;
import pl.elektryczny.surveyapp.result.dto.QuestionDetailsDto;
import pl.elektryczny.surveyapp.result.dto.SurveyDetailedResultsDto;
import pl.elektryczny.surveyapp.result.model.SurveyResult;
import pl.elektryczny.surveyapp.result.repository.SurveyResultRepository;
import pl.elektryczny.surveyapp.security.model.User;
import pl.elektryczny.surveyapp.survey.model.Survey;
import pl.elektryczny.surveyapp.survey.service.SurveyService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SurveyResultServiceImpl implements SurveyResultService {

    private final SurveyResultRepository repository;
    private final SurveyService surveyService;

    @Override
    public SurveyDetailedResultsDto getSurveyResults(Integer surveyId, User user) {
        SurveyDetailedResultsDto detailedResults = new SurveyDetailedResultsDto();
        List<SurveyResult> results = repository.findSurveyResultsBySurveyId(surveyId);
        Survey survey = surveyService.getSurveyById(surveyId, user);

        detailedResults.setId(surveyId);
        detailedResults.setName(survey.getName());
        detailedResults.setQuestions(new ArrayList<>());

        survey.getQuestions().forEach(question -> {
            QuestionDetailsDto questionDetails = new QuestionDetailsDto();
            questionDetails.setNumber(question.getNumber());
            questionDetails.setQuestionText(question.getText());
            long questionResponses = results.stream()
                    .flatMap(surveyResult -> surveyResult.getQuestions().stream())
                    .filter(questionAnswer -> questionAnswer.getNumber().equals(question.getNumber()))
                    .count();
            questionDetails.setTotalNumberOfRespondents((int) questionResponses);
            questionDetails.setAnswers(new ArrayList<>());
            detailedResults.getQuestions().add(questionDetails);

            question.getAnswers().forEach(answer -> {
                AnswerDetailsDto answerDetails = new AnswerDetailsDto();
                answerDetails.setNumber(answer.getNumber());
                answerDetails.setAnswerText(answer.getText());
                long answerResponses = results.stream()
                        .flatMap(surveyResult -> surveyResult.getQuestions().stream())
                        .filter(questionAnswer -> questionAnswer.getNumber().equals(question.getNumber()))
                        .filter(questionAnswer -> questionAnswer.getChosenAnswer().equals(answer.getNumber()))
                        .count();
                answerDetails.setNumberOfRespondents((int) answerResponses);
                questionDetails.getAnswers().add(answerDetails);
            });
        });

//        Map<Integer, List<QuestionAnswer>> questionMap = results.stream()
//                .flatMap(surveyResult -> surveyResult.getQuestions().stream())
//                .collect(groupingBy(QuestionAnswer::getNumber));
//
//
//
//        questionMap.forEach((questionNumber, questionAnswerList) -> {
//                    QuestionDetailsDto questionDetails = new QuestionDetailsDto();
//                    questionDetails.setNumber(questionNumber);
//                    String questionText = survey.getQuestions().stream()
//                            .filter(question -> question.getNumber().equals(questionNumber))
//                            .findAny()
//                            .get()
//                            .getText();
//                    questionDetails.setQuestionText(questionText);
//                    questionDetails.setTotalNumberOfRespondents(questionAnswerList.size());
//                    detailedResults.getQuestions().add(questionDetails);
//
//                    questionDetails.setAnswers(new ArrayList<>());
//
//                    Map<Integer, Long> answerList = questionAnswerList.stream().collect(
//                            groupingBy(QuestionAnswer::getChosenAnswer, Collectors.counting())
//                    );
//                    answerList.forEach((answerNumber, respondents) -> {
//                        AnswerDetailsDto answerDetails = new AnswerDetailsDto();
//                        answerDetails.setNumber(answerNumber);
//                        answerDetails.setNumberOfRespondents(respondents.intValue());
//                        String answerText = survey.getQuestions().stream()
//                                .filter(question -> question.getNumber().equals(questionNumber))
//                                .flatMap(question -> question.getAnswers().stream())
//                                .filter(answer -> answer.getNumber().equals(answerNumber))
//                                .findFirst()
//                                .get()
//                                .getText();
//                        answerDetails.setAnswerText(answerText);
//                        questionDetails.getAnswers().add(answerDetails);
//                    });
//
//
//                });


        return detailedResults;
    }

    @Override
    public SurveyResult addSurveyResult(SurveyResult survey) {
        return repository.save(survey);
    }

}
