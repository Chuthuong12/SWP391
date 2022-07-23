/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Fangl
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class AnswerD {
    private int answerId;
    private String content;
    private boolean correct;
    private int quesId;
}
