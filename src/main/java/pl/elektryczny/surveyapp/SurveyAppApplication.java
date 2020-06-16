package pl.elektryczny.surveyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SurveyAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SurveyAppApplication.class, args);
    }

}
