package pl.elektryczny.surveyapp.result.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDetailsDto {
    private Integer number;
    private String questionText;
    private Integer totalNumberOfRespondents;
    private List<AnswerDetailsDto> answers;

}
