package pl.elektryczny.surveyapp.result.dto;

import lombok.Data;
import org.springframework.scheduling.support.SimpleTriggerContext;

@Data
public class AnswerDetailsDto {
    Integer number;
    String answerText;
    Integer numberOfRespondents;
}
