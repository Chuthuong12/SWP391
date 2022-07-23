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

/**
 *
 * @author Dell
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserRegister {
    private int userId;
    private int regisId;
    private String regisStatis;
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
}
