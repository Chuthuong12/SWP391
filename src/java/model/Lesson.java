/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


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
public class Lesson {
    private int lessonId;
    private String lessonName;
    private String typeId;
    private int order;
    private String video_url;
    private String content;
    private int topicId;
    private Boolean status;
    private int subId;
    private String description;
    private String typeName;
}
