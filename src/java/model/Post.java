/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.Date;
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
public class Post {
    private int postId;
    private String thumbnail;
    private int userId;
    private int categoryBlogId;
    private String content;
    private Date created_date;
    private String edit_date;
    private Boolean status;
    private String brifInfor;
    private String title;
    private int postFileId;
}
