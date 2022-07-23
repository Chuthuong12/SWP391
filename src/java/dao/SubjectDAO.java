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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Subject;

/**
 *
 * @author ADMIN
 */
public class SubjectDAO extends DBContext implements Serializable {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List<Subject> getAllSubjects() {
        List<Subject> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct S.* from Subject AS S";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {

                    Subject p = Subject.builder()
                            .subjectId(rs.getInt(1))
                            .subjectName(rs.getString(2))
                            .categoryId(rs.getInt(3))
                            .status(rs.getBoolean(4))
                            .tagLine(rs.getInt(5))
                            .title(rs.getString(6))
                            .thumbnail(rs.getString(7))
                            .description(rs.getString(8))
                            .build();

                    list.add(p);
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

    public List<Subject> getListSubjectBySubjectId(int subjectId) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct S.*from Subject AS S\n"
                    + "                    where S.subjectId = ? ";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, subjectId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {

                    Subject p = Subject.builder()
                            .subjectId(rs.getInt(1))
                            .subjectName(rs.getString(2))
                            .categoryId(rs.getInt(3))
                            .status(rs.getBoolean(4))
                            .tagLine(rs.getInt(5))
                            .title(rs.getString(6))
                            .thumbnail(rs.getString(7))
                            .description(rs.getString(8))
                            .build();

                    list.add(p);
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

    public int getTotalSubject(String keyword) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(S.subjectId)from Subject AS S where S.subjectName like ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
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

    public List<Subject> getListSubjectsByKeywordAndPagging(String keyword, int page, int PAGE_SIZE_6) {
        List<Subject> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by S.subjectId asc) as r,\n" +
"                    S.*from Subject AS S \n" +
"                    where S.subjectName like ?) select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setInt(2, page);
                ps.setInt(3, PAGE_SIZE_6);
                ps.setInt(4, PAGE_SIZE_6);
                ps.setInt(5, page);
                ps.setInt(6, PAGE_SIZE_6);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Subject subject = Subject.builder()
                            .subjectId(rs.getInt(2))
                            .subjectName(rs.getString(3))
                            .categoryId(rs.getInt(4))
                            .status(rs.getBoolean(5))
                            .tagLine(rs.getInt(6))
                            .title(rs.getString(7))
                            .thumbnail(rs.getString(8))
                            .description(rs.getString(9))
                            .build();
                    list.add(subject);
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

    public List<Subject> getListSubjectsByPagging(int page, int PAGE_SIZE_6) {
        List<Subject> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by S.subjectId asc) as r,\n" +
"                    S.* from Subject AS S )\n" +
"                    select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, page);
                ps.setInt(2, PAGE_SIZE_6);
                ps.setInt(3, PAGE_SIZE_6);
                ps.setInt(4, page);
                ps.setInt(5, PAGE_SIZE_6);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Subject subject = Subject.builder()
                            .subjectId(rs.getInt(2))
                            .subjectName(rs.getString(3))
                            .categoryId(rs.getInt(4))
                            .status(rs.getBoolean(5))
                            .tagLine(rs.getInt(6))
                            .title(rs.getString(7))
                            .thumbnail(rs.getString(8))
                            .description(rs.getString(9))
                            .build();
                    list.add(subject);
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

    public int getTotalSubject() {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(S.subjectId) from Subject AS S ";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Subject getSubjectById(int subjectId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                if (con != null) {
                    String sql = "select subjectId, subjectName, categoryId, status, tagLine, title, thumbnail, description\n"
                            + "from Subject\n"
                            + "where subjectId = ?";
                    PreparedStatement stm = con.prepareStatement(sql);
                    stm.setInt(1, subjectId);
                    ResultSet rs = stm.executeQuery();
                    if (rs.next()) {
                        return Subject.builder()
                                .subjectId(rs.getInt(1))
                                .subjectName(rs.getString(2))
                                .categoryId(rs.getInt(3))
                                .status(rs.getBoolean(4))
                                .tagLine(rs.getInt(5))
                                .title(rs.getString(6))
                                .thumbnail(rs.getString(7))
                                .description(rs.getString(8))
                                .build();
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public Subject getSubjectById(String subjectId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                int id = Integer.parseInt(subjectId);
                String sql = "select distinct S.* from Subject AS S where S.subjectId = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {

                    Subject p = Subject.builder()
                            .subjectId(rs.getInt(1))
                            .subjectName(rs.getString(2))
                            .categoryId(rs.getInt(3))
                            .status(rs.getBoolean(4))
                            .tagLine(rs.getInt(5))
                            .title(rs.getString(6))
                            .thumbnail(rs.getString(7))
                            .description(rs.getString(8))
                            .build();

                    return p;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public boolean Update(Subject subject) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "UPDATE [SWP391_Project_Test].[dbo].[Subject]\n"
                        + "   SET [subjectName] = ?,[categoryId] = ?,[status] = ?,[tagLine] = ?,[title] = ?,[thumbnail] = ?,[description] = ?\n"
                        + " WHERE subjectId = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, subject.getSubjectName());
                stm.setInt(2, subject.getCategoryId());
                stm.setBoolean(3, subject.isStatus());
                stm.setInt(4, subject.getTagLine());
                stm.setString(5, subject.getTitle());
                stm.setString(6, subject.getThumbnail());
                stm.setString(7, subject.getDescription());
                stm.setInt(8, subject.getSubjectId());
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public List<Subject> getSubjectByStatus(int status) {
        List<Subject> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct S.* from Subject AS S where S.status=?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {

                    Subject p = Subject.builder()
                            .subjectId(rs.getInt(1))
                            .subjectName(rs.getString(2))
                            .categoryId(rs.getInt(3))
                            .status(rs.getBoolean(4))
                            .tagLine(rs.getInt(5))
                            .title(rs.getString(6))
                            .thumbnail(rs.getString(7))
                            .description(rs.getString(8))
                            .build();

                    list.add(p);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public boolean insertNewSubject(Subject subject) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [SWP391_Project_Test].[dbo].[Subject]\n"
                        + "           ([subjectName]\n"
                        + "           ,[categoryId]\n"
                        + "           ,[status]\n"
                        + "           ,[tagLine]\n"
                        + "           ,[title]\n"
                        + "           ,[thumbnail]\n"
                        + "           ,[description])\n"
                        + "     VALUES (?,?,?,?,?,?,?)";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, subject.getSubjectName());
                stm.setInt(2, subject.getCategoryId());
                stm.setBoolean(3, subject.isStatus());
                stm.setInt(4, subject.getTagLine());
                stm.setString(5, subject.getTitle());
                stm.setString(6, subject.getThumbnail());
                stm.setString(7, subject.getDescription());
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public int getTotalSubjectAdmin() {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by S.subjectName asc) as r,\n"
                        + "                    S.* from [Subject] AS S ) \n"
                        + "                    select distinct count(t.subjectId) from t";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }
}
