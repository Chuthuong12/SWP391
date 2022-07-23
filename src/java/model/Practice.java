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
public class Practice {

    private int id;
    private int userId;
    private float point;
    private int quizzId;
    private String title;
    private int duration;
    private int totalQues;
    private String taken_date; 
    private int numOfQues;
    private int attempt;
    private float pointPercent;
    private int numQuesTrue;
    private String subName;
    private int subId;
}
