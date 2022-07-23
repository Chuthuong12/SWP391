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
/**
 *
 * @author ADMIN
 */
public class Slider implements Serializable{
    private int sliderId;
    private String sliderUrl;
    private Boolean status;
    private String title;
    private String content;
    private String backlink;
    private String notes;
    private boolean isShow;
    private int subId;
    
    
    
}
