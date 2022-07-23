/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

public class Subject implements Serializable {

    private int subjectId;
    private String subjectName;
    private int categoryId;
    private boolean status;
    private int tagLine;
    private String title;
    private String thumbnail;
    private String description;
    private int salePrice;
    private int price;

    public Subject(int subjectId, String subjectName, int categoryId, boolean status, int tagLine, String title, String thumbnail, String description) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.categoryId = categoryId;
        this.status = status;
        this.tagLine = tagLine;
        this.title = title;
        this.thumbnail = thumbnail;
        this.description = description;
    }
}
