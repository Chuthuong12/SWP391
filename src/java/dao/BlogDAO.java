/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;

/**
 *
 * @author ADMIN
 */
public class BlogDAO {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List<Blog> getListBlogs() {
        ArrayList<Blog> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select * from Blog";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {

                    Blog blog = Blog.builder()
                            .blogId(rs.getInt(1))
                            .blogName(rs.getString(2))
                            .subId(rs.getInt(3))
                            .build();

                    list.add(blog);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    

}
