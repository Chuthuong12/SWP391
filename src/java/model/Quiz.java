/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
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

/**
 *
 * @author ADMIN
 */
public class Quiz {

    private int quizId;
    private String title;
    private String level;
    private String imgUrl;
    private Time start_time;
    private Time end_time;
    private boolean status;
    private float rate;
    private boolean hasJoin;
    private int subId;
    private int userId;
    private int quesId;
    private int lessonId;
    private int duration;
    private String typeId;
    private String typeName;
    private String subjectName;
    private String description;
    private int totalQues;
    private int attempt;
}
