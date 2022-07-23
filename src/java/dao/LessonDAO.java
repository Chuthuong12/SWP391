/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lesson;
import model.Post;
import model.Type;

/**
 *
 * @author ADMIN
 */
public class LessonDAO {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List<Lesson> getAllLessons() {
        ArrayList<Lesson> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select l.* from Lesson l \n"
                        + "inner join Subject s on l.subId = s.subjectId\n";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Lesson lesson = Lesson.builder()
                            .lessonId(rs.getInt(1))
                            .lessonName(rs.getString(2))
                            .typeId(rs.getString(3))
                            .order(rs.getInt(4))
                            .video_url(rs.getString(5))
                            .content(rs.getString(6))
                            .topicId(rs.getInt(7))
                            .status(rs.getBoolean(8))
                            .subId(rs.getInt(9))
                            .description(rs.getString(10))
                            .build();

                    list.add(lesson);
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

    
    public List<Lesson> get3LessonsBySubId(int subjectId) {
        ArrayList<Lesson> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select Top 3 l.* from Lesson l \n"
                        + "inner join Subject s on l.subId = s.subjectId\n"
                        + "where s.subjectId = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, subjectId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Lesson lesson = Lesson.builder()
                            .lessonId(rs.getInt(1))
                            .lessonName(rs.getString(2))
                            .typeId(rs.getString(3))
                            .order(rs.getInt(4))
                            .video_url(rs.getString(5))
                            .content(rs.getString(6))
                            .topicId(rs.getInt(7))
                            .status(rs.getBoolean(8))
                            .subId(rs.getInt(9))
                            .description(rs.getString(10))
                            .build();

                    list.add(lesson);
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

    public List<Lesson> getListLessonsBySubId(int subId) {
        ArrayList<Lesson> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select l.*, t.typeName from Lesson l\n"
                        + "inner join Type t on l.typeId = t.typeId\n"
                        + "inner join Subject s on s.subjectId = l.subId\n"
                        + "where s.subjectId = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, subId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Lesson lesson = Lesson.builder()
                            .lessonId(rs.getInt(1))
                            .lessonName(rs.getString(2))
                            .typeId(rs.getString(3))
                            .order(rs.getInt(4))
                            .video_url(rs.getString(5))
                            .content(rs.getString(6))
                            .topicId(rs.getInt(7))
                            .status(rs.getBoolean(8))
                            .subId(rs.getInt(9))
                            .description(rs.getString(10))
                            .typeName(rs.getString(11))
                            .build();

                    list.add(lesson);
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

    public Lesson getLessonById(int lessonId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "Select lessonId, lessonName, typeId, [order] , video_url, content, topicId, status, subId, description\n"
                        + "From Lesson\n"
                        + "where lessonId = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, lessonId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    return Lesson.builder()
                            .lessonId(rs.getInt(1))
                            .lessonName(rs.getString(2))
                            .typeId(rs.getString(3))
                            .order(rs.getInt(4))
                            .video_url(rs.getString(5))
                            .content(rs.getString(6))
                            .topicId(rs.getInt(7))
                            .status(rs.getBoolean(8))
                            .subId(rs.getInt(9))
                            .description(rs.getString(10))
                            .build();
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

    public boolean updateLesson(String name, String type, int topic, int order, String videoUrl, String content, int lessonId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "update Lesson\n"
                        + "set lessonName = ?,\n"
                        + "typeId = ?,\n"
                        + "topicId = ?,\n"
                        + "[order] = ?,\n"
                        + "video_url = ?,\n"
                        + "content = ?\n"
                        + "where\n"
                        + "lessonId = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, type);
                pst.setInt(3, topic);
                pst.setInt(4, order);
                pst.setString(5, videoUrl);
                pst.setString(6, content);
                pst.setInt(7, lessonId);
                if (pst.executeUpdate() > 0) {
                    return true;
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
        return false;
    }

    public void updateActiveStatusLesson(int activeLessonId) {
        String sql = "UPDATE [SWP391_Project_Test].[dbo].[Lesson]\n"
                + "   SET [status] = 1\n"
                + " WHERE [lessonId] = ? ";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, activeLessonId);
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

    public void updateInactiveStatusLesson(int inactiveLessonId) {
        String sql = "UPDATE [SWP391_Project_Test].[dbo].[Lesson]\n"
                + "   SET [status] = 0\n"
                + " WHERE [lessonId] = ? ";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, inactiveLessonId);
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

    public int getTotalLesson(int subId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(l.lessonId) from Lesson l\n"
                        + "inner join Type t on l.typeId = t.typeId\n"
                        + "inner join Subject s on s.subjectId = l.subId where l.subId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, subId);
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

    public int getTotalLesson(String keyword, int status, int subId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(l.lessonId) from Lesson l\n"
                        + "inner join Type t on l.typeId = t.typeId\n"
                        + "inner join Subject s on s.subjectId = l.subId where l.subId = ?\n"
                        + "and l.lessonName like ? and l.status = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, subId);
                ps.setString(2, "%" + keyword + "%");
                ps.setInt(3, status);
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

    public int getTotalLesson(String keyword, int subId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(l.lessonId) from Lesson l\n"
                        + "inner join Type t on l.typeId = t.typeId\n"
                        + "inner join Subject s on s.subjectId = l.subId where l.subId = ?\n"
                        + "and l.lessonName like ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, subId);
                ps.setString(2, "%" + keyword + "%");
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

    public List<Lesson> getListLessonsByPagging(int page, int PAGE_SIZE_6, int subId) {
        List<Lesson> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by L.lessonId asc) as r,\n"
                        + "L.*, T.typeName  from Lesson AS L left join Subject AS S \n"
                        + "on L.subId = S.subjectId inner join Type T on T.typeId = L.typeId\n"
                        + "where L.subId = ?)select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, subId);
                ps.setInt(2, page);
                ps.setInt(3, PAGE_SIZE_6);
                ps.setInt(4, PAGE_SIZE_6);
                ps.setInt(5, page);
                ps.setInt(6, PAGE_SIZE_6);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Lesson lesson = Lesson.builder()
                            .lessonId(rs.getInt(2))
                            .lessonName(rs.getString(3))
                            .typeId(rs.getString(4))
                            .order(rs.getInt(5))
                            .video_url(rs.getString(6))
                            .content(rs.getString(7))
                            .topicId(rs.getInt(8))
                            .status(rs.getBoolean(9))
                            .subId(rs.getInt(10))
                            .description(rs.getString(11))
                            .typeName(rs.getString(12))
                            .build();
                    list.add(lesson);
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

    public List<Lesson> getListLessonsByKeywordAndPagging(String keyword, int page, int PAGE_SIZE_6, int status, int subId) {
        List<Lesson> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = " with t as (select ROW_NUMBER() over (order by L.lessonId asc) as r,\n"
                        + "L.*, T.typeName  from Lesson AS L left join Subject AS S \n"
                        + "on L.subId = S.subjectId inner join Type T on T.typeId = L.typeId\n"
                        + "where L.subId = ? and L.lessonName like ? and L.status = ?) \n"
                        + "select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, subId);
                ps.setString(2, "%" + keyword + "%");
                ps.setInt(3, status);
                ps.setInt(4, page);
                ps.setInt(5, PAGE_SIZE_6);
                ps.setInt(6, PAGE_SIZE_6);
                ps.setInt(7, page);
                ps.setInt(8, PAGE_SIZE_6);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Lesson lesson = Lesson.builder()
                            .lessonId(rs.getInt(2))
                            .lessonName(rs.getString(3))
                            .typeId(rs.getString(4))
                            .order(rs.getInt(5))
                            .video_url(rs.getString(6))
                            .content(rs.getString(7))
                            .topicId(rs.getInt(8))
                            .status(rs.getBoolean(9))
                            .subId(rs.getInt(10))
                            .description(rs.getString(11))
                            .typeName(rs.getString(12))
                            .build();
                    list.add(lesson);
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

    public List<Lesson> getListLessonsByKeywordAndPagging(String keyword, int page, int PAGE_SIZE_6, int subId) {
        List<Lesson> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by L.lessonId asc) as r,\n"
                        + "L.*, T.typeName  from Lesson AS L left join Subject AS S \n"
                        + "on L.subId = S.subjectId inner join Type T on T.typeId = L.typeId\n"
                        + "where L.subId = ? and L.lessonName like ?) \n"
                        + "select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, subId);
                ps.setString(2, "%" + keyword + "%");
                ps.setInt(3, page);
                ps.setInt(4, PAGE_SIZE_6);
                ps.setInt(5, PAGE_SIZE_6);
                ps.setInt(6, page);
                ps.setInt(7, PAGE_SIZE_6);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Lesson lesson = Lesson.builder()
                            .lessonId(rs.getInt(2))
                            .lessonName(rs.getString(3))
                            .typeId(rs.getString(4))
                            .order(rs.getInt(5))
                            .video_url(rs.getString(6))
                            .content(rs.getString(7))
                            .topicId(rs.getInt(8))
                            .status(rs.getBoolean(9))
                            .subId(rs.getInt(10))
                            .description(rs.getString(11))
                            .typeName(rs.getString(12))
                            .build();
                    list.add(lesson);
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

    public boolean insertLesson(Lesson lesson) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [SWP391_Project_Test].[dbo].[Lesson]\n"
                        + "           ([lessonName]\n"
                        + "           ,[typeId]\n"
                        + "           ,[order]\n"
                        + "           ,[video_url]\n"
                        + "           ,[content]\n"
                        + "           ,[topicId]\n"
                        + "           ,[status]\n"
                        + "           ,[subId]\n"
                        + "           ,[description])\n"
                        + "     VALUES\n"
                        + "           (?,?,?,?,?,?,?,?,?)\n";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, lesson.getLessonName());
                pst.setString(2, lesson.getTypeId());
                pst.setInt(3, lesson.getOrder());
                pst.setString(4, lesson.getVideo_url());
                pst.setString(5, lesson.getContent());
                pst.setInt(6, lesson.getTopicId());
                pst.setBoolean(7, lesson.getStatus());
                pst.setInt(8, lesson.getSubId());
                pst.setString(9, lesson.getDescription());
                if (pst.executeUpdate() > 0) {
                    return true;
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
        return false;
    }

    public Lesson getTop1LessonByLessonId(int subId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "SELECT TOP 1.*\n"
                        + " FROM [SWP391_Project_Test].[dbo].[Lesson] WHERE subId = ? ";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, subId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    return Lesson.builder()
                            .lessonId(rs.getInt(1))
                            .lessonName(rs.getString(2))
                            .typeId(rs.getString(3))
                            .order(rs.getInt(4))
                            .video_url(rs.getString(5))
                            .content(rs.getString(6))
                            .topicId(rs.getInt(7))
                            .status(rs.getBoolean(8))
                            .subId(rs.getInt(9))
                            .description(rs.getString(10))
                            .build();
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

    public Lesson getLessonByLessonId(int lessonId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "Select *  From Lesson where lessonId = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, lessonId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    return Lesson.builder()
                            .lessonId(rs.getInt(1))
                            .lessonName(rs.getString(2))
                            .typeId(rs.getString(3))
                            .order(rs.getInt(4))
                            .video_url(rs.getString(5))
                            .content(rs.getString(6))
                            .topicId(rs.getInt(7))
                            .status(rs.getBoolean(8))
                            .subId(rs.getInt(9))
                            .build();
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
    
    public List<Lesson> getListLessons() {
        ArrayList<Lesson> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select l.* from Lesson l \n"
                        + "inner join Subject s on l.subId = s.subjectId\n";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Lesson lesson = Lesson.builder()
                            .lessonId(rs.getInt(1))
                            .lessonName(rs.getString(2))
                            .typeId(rs.getString(3))
                            .order(rs.getInt(4))
                            .video_url(rs.getString(5))
                            .content(rs.getString(6))
                            .topicId(rs.getInt(7))
                            .status(rs.getBoolean(8))
                            .subId(rs.getInt(9))
                            .build();

                    list.add(lesson);
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
