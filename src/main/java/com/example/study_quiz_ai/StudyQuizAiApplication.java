package com.example.study_quiz_ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = {
		"com.example.study_quiz_ai.core",
		"com.example.study_quiz_ai.modules"
})
@EnableWebMvc
public class StudyQuizAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyQuizAiApplication.class, args);
	}

}
