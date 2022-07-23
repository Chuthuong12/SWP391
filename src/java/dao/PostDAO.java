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
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Post;
import model.Subject;

/**
 *
 * @author ADMIN
 */
public class PostDAO {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List<Post> getList3FirstPosts() {
        ArrayList<Post> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select Top 3 p.* from Post p\n"
                        + "inner join Blog b on p.categoryBlogId = b.blogId\n"
                        + "inner join Subject s on b.subId = s.subjectId";

                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Post post = Post.builder()
                            .postId(rs.getInt(1))
                            .thumbnail(rs.getString(2))
                            .userId(rs.getInt(3))
                            .categoryBlogId(rs.getInt(4))
                            .content(rs.getString(5))
                            .created_date(rs.getDate(6))
                            .edit_date(rs.getString(7))
                            .status(rs.getBoolean(8))
                            .brifInfor(rs.getString(9))
                            .title(rs.getString(10))
                            .postFileId(rs.getInt(11))
                            .build();

                    list.add(post);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Post> getAllPosts() {
        List<Post> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM Post\n"
                        + "ORDER BY edit_date DESC";

                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Post post = Post.builder()
                            .postId(rs.getInt(1))
                            .thumbnail(rs.getString(2))
                            .userId(rs.getInt(3))
                            .categoryBlogId(rs.getInt(4))
                            .content(rs.getString(5))
                            .created_date(rs.getDate(6))
                            .edit_date(rs.getString(7))
                            .status(rs.getBoolean(8))
                            .brifInfor(rs.getString(9))
                            .title(rs.getString(10))
                            .postFileId(rs.getInt(11))
                            .build();
                    list.add(post);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;

    }

    public List<Post> getPostsAndPagging(int page, int PAGE_SIZE) {
        List<Post> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (ORDER BY P.edit_date DESC) as r,\n"
                        + "P.* from Post P) select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, page);
                ps.setInt(2, PAGE_SIZE);
                ps.setInt(3, PAGE_SIZE);
                ps.setInt(4, page);
                ps.setInt(5, PAGE_SIZE);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Post post = Post.builder()
                            .postId(rs.getInt(2))
                            .thumbnail(rs.getString(3))
                            .userId(rs.getInt(4))
                            .categoryBlogId(rs.getInt(5))
                            .content(rs.getString(6))
                            .created_date(rs.getDate(7))
                            .edit_date(rs.getString(8))
                            .status(rs.getBoolean(9))
                            .brifInfor(rs.getString(10))
                            .title(rs.getString(11))
                            .postFileId(rs.getInt(12))
                            .build();
                    list.add(post);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public Post getPostById(int postId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "Select * from Post where postId = ?";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, postId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
//                Post post = new Post(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), 
//                        rs.getDate(6), rs.getDate(7), rs.getBoolean(8), rs.getString(9), rs.getString(10), rs.getInt(11));
                    Post post = Post.builder()
                            .postId(rs.getInt(1))
                            .thumbnail(rs.getString(2))
                            .userId(rs.getInt(3))
                            .categoryBlogId(rs.getInt(4))
                            .content(rs.getString(5))
                            .created_date(rs.getDate(6))
                            .edit_date(rs.getString(7))
                            .status(rs.getBoolean(8))
                            .brifInfor(rs.getString(9))
                            .title(rs.getString(10))
                            .postFileId(rs.getInt(11)).build();
                    return post;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Post getLastPost() {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "SELECT *\n"
                        + "FROM Post\n"
                        + "WHERE created_date = (SELECT MAX(created_date) FROM Post);";

                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
//                Post post = new Post(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), 
//                        rs.getDate(6), rs.getDate(7), rs.getBoolean(8), rs.getString(9), rs.getString(10), rs.getInt(11));
                    Post lastPost = Post.builder()
                            .postId(rs.getInt(1))
                            .thumbnail(rs.getString(2))
                            .userId(rs.getInt(3))
                            .categoryBlogId(rs.getInt(4))
                            .content(rs.getString(5))
                            .created_date(rs.getDate(6))
                            .edit_date(rs.getString(7))
                            .status(rs.getBoolean(8))
                            .brifInfor(rs.getString(9))
                            .title(rs.getString(10))
                            .postFileId(rs.getInt(11)).build();
                    return lastPost;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int getTotalPost() {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
//            String sql = "select distinct count(S.subjectId)\n"
//                    + "from Subject AS S left join SubjectPrice AS SP \n"
//                    + "on S.subjectId = SP.subjectId \n"
//                    + "left join PricePackage as PP on PP.priceId = SP.priceId\n";            
                String sql = " select count(*) from Post";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    
    
    public int getTotalPostByBlogId(int blogId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {        
                String sql = " select count(*) from Post where categoryBlogId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, blogId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public List<Post> getPostByCategoryId(int categoryId) {
        List<Post> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "Select * from Post where Post.categoryBlogId = ?";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, categoryId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Post post = Post.builder()
                            .postId(rs.getInt(1))
                            .thumbnail(rs.getString(2))
                            .userId(rs.getInt(3))
                            .categoryBlogId(rs.getInt(4))
                            .content(rs.getString(5))
                            .created_date(rs.getDate(6))
                            .edit_date(rs.getString(7))
                            .status(rs.getBoolean(8))
                            .brifInfor(rs.getString(9))
                            .title(rs.getString(10))
                            .postFileId(rs.getInt(11)).build();
                    list.add(post);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void updatePost(int postId, String title, boolean status, String thumbnail, String briefInfor, String content) {
        String sql = "UPDATE [SWP391_Project_Test].[dbo].[Post]\n"
                + "   SET [thumbnail] = ? \n"
                + "      ,[content] =  ?\n"
                + "      ,[status] = ?\n"
                + "      ,[brifInfor] = ?\n"
                + "      ,[title] = ?\n"
                + "      ,[edit_date] = ?\n"
                + " WHERE postId = ?";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(sql);
                DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//Zoned datetime instance
                ZonedDateTime zdt = ZonedDateTime.now();

//Get formatted String
                String nowDate = FOMATTER.format(zdt);

                ps.setString(1, thumbnail);
                ps.setString(2, content);
                ps.setBoolean(3, status);
                ps.setString(4, briefInfor);
                ps.setString(5, title);
                ps.setString(6, nowDate);
                ps.setInt(7, postId);

                ps.executeUpdate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void insertPost(int blogId, String title, boolean status, String thumbnail, String briefInfor, String content) {
        String sql = "INSERT INTO [SWP391_Project_Test].[dbo].[Post]\n"
                + "           ([thumbnail]\n"
                + "           ,[categoryBlogId]\n"
                + "           ,[content]\n"
                + "           ,[status]\n"
                + "           ,[brifInfor]\n"
                + "           ,[title])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, thumbnail);
                ps.setInt(2, blogId);
                ps.setString(3, content);
                ps.setBoolean(4, status);
                ps.setString(5, briefInfor);
                ps.setString(6, title);
                ps.executeUpdate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public List<Post> getPostsByBlogIdAndPagging(int blogId, int page, int PAGE_SIZE) {
        List<Post> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (ORDER BY P.edit_date DESC) as r,\n"
                        + "P.* from Post P where P.categoryBlogId = ?) select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, blogId);
                ps.setInt(2, page);
                ps.setInt(3, PAGE_SIZE);
                ps.setInt(4, PAGE_SIZE);
                ps.setInt(5, page);
                ps.setInt(6, PAGE_SIZE);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Post post = Post.builder()
                            .postId(rs.getInt(2))
                            .thumbnail(rs.getString(3))
                            .userId(rs.getInt(4))
                            .categoryBlogId(rs.getInt(5))
                            .content(rs.getString(6))
                            .created_date(rs.getDate(7))
                            .edit_date(rs.getString(8))
                            .status(rs.getBoolean(9))
                            .brifInfor(rs.getString(10))
                            .title(rs.getString(11))
                            .postFileId(rs.getInt(12))
                            .build();
                    list.add(post);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
