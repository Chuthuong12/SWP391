/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class QuestionL {
    private  int questionId;
    private int quizId;
    private String contentQuestion;
    private Subject subject;
    private Lesson lesson;
    private Topic topic;
    private String level;
    private boolean status;
    private ArrayList<Answer> listAnswer;
    private Dimension dimmension;

    public QuestionL(int questionId, int quizId, String contentQuestion, Subject subject, Lesson lesson, Topic topic, String level, boolean status, Dimension dimmension) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.contentQuestion = contentQuestion;
        this.subject = subject;
        this.lesson = lesson;
        this.topic = topic;
        this.level = level;
        this.status = status;
        this.dimmension = dimmension;
    }

    
}
