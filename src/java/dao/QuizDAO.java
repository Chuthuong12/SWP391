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
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.AnswerDetail;
import model.Quiz;
import model.QuizzPoint;

/**
 *
 * @author ADMIN
 */
public class QuizDAO {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List<Quiz> getListQuizByLessonId(int lessonId) {
        List<Quiz> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select Qz.quizId, Qz.title, Qz.level, Qz.img_url, Qz.status, Qz.rate, Qz.lessonId, Qz.subId, Qz.typeId, Qz.duration, \n"
                        + "Qz.description, S.subjectName, Qz.attempt, COUNT(Q.questionId) AS \"Total Ques\" from Question Q\n"
                        + "inner join Quiz Qz on Q.quizId = Qz.quizId inner join Subject S on S.subjectId = Qz.subId \n"
                        + "where Qz.lessonId = ? group by Qz.quizId, Qz.title, Qz.level, Qz.img_url, \n"
                        + "Qz.status, Qz.rate, Qz.lessonId, Qz.subId, Qz.typeId, Qz.duration, Qz.description, S.subjectName, Qz.attempt";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, lessonId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Quiz quiz = Quiz.builder()
                            .quizId(rs.getInt(1))
                            .title(rs.getString(2))
                            .level(rs.getString(3))
                            .imgUrl(rs.getString(4))
                            .status(rs.getBoolean(5))
                            .rate(rs.getFloat(6))
                            .lessonId(rs.getInt(7))
                            .subId(rs.getInt(8))
                            .typeId(rs.getString(9))
                            .duration(rs.getInt(10))
                            .description(rs.getString(11))
                            .subjectName(rs.getString(12))
                            .attempt(rs.getInt(13))
                            .totalQues(rs.getInt(14))
                            .build();

                    list.add(quiz);
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

    public List<Quiz> getListPracticeByLessonId(int lessonId) {
        List<Quiz> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select Q.quizId, Q.title, Q.level, Q.img_url, Q.status, Q.rate, Q.lessonId, Q.subId, Q.typeId,\n"
                        + "Q.duration, Q.description, Q.totalQues, S.subjectName, Q.attempt from Quiz Q inner join Lesson L\n"
                        + "on Q.lessonId = L.lessonId inner join Subject S\n"
                        + "on S.subjectId = L.subId where L.lessonId = ? and Q.status = 1 and Q.typeId = 'Q1'";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, lessonId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Quiz quiz = Quiz.builder()
                            .quizId(rs.getInt(1))
                            .title(rs.getString(2))
                            .level(rs.getString(3))
                            .imgUrl(rs.getString(4))
                            .status(rs.getBoolean(5))
                            .rate(rs.getFloat(6))
                            .lessonId(rs.getInt(7))
                            .subId(rs.getInt(8))
                            .typeId(rs.getString(9))
                            .duration(rs.getInt(10))
                            .description(rs.getString(11))
                            .totalQues(rs.getInt(12))
                            .subjectName(rs.getString(13))
                            .attempt(rs.getInt(14))
                            .build();

                    list.add(quiz);
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

    public int getTotalQuiz() {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(Q.quizId)\n"
                        + "from Quiz AS Q inner join Lesson AS L\n"
                        + "on Q.lessonId = L.lessonId";
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

    public List<Quiz> getListQuizzesByPagging(int page, int PAGE_SIZE_6) {
        List<Quiz> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by Q.quizId asc) as r,\n"
                        + "Q.quizId, Q.title, Q.level, Q.img_url, Q.status, Q.rate, Q.lessonId, Q.subId, Q.typeId,\n"
                        + "Q.duration, Q.description, Q.totalQues, T.typeName, S.subjectName from Quiz AS Q left join Lesson AS L \n"
                        + "on Q.lessonId = L.lessonId inner join Subject S\n"
                        + "on L.subId = S.subjectId inner join Type T on Q.typeId = T.typeId) \n"
                        + "select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, page);
                ps.setInt(2, PAGE_SIZE_6);
                ps.setInt(3, PAGE_SIZE_6);
                ps.setInt(4, page);
                ps.setInt(5, PAGE_SIZE_6);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = Quiz.builder()
                            .quizId(rs.getInt(2))
                            .title(rs.getString(3))
                            .level(rs.getString(4))
                            .imgUrl(rs.getString(5))
                            .status(rs.getBoolean(6))
                            .rate(rs.getFloat(7))
                            .lessonId(rs.getInt(8))
                            .subId(rs.getInt(9))
                            .typeId(rs.getString(10))
                            .duration(rs.getInt(11))
                            .description(rs.getString(12))
                            .totalQues(rs.getInt(13))
                            .typeName(rs.getString(14))
                            .subjectName(rs.getString(15))
                            .build();
                    list.add(quiz);
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

    public int getTotalQuizOnlySearch(String keyword) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(Q.quizId)\n"
                        + "from Quiz AS Q inner join Lesson AS L\n"
                        + "on Q.lessonId = L.lessonId where Q.title like ?";
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

    public int getTotalQuizBySubIdAndSearch(String keyword, int subId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(Q.quizId)\n"
                        + "from Quiz AS Q inner join Lesson AS L\n"
                        + "on Q.lessonId = L.lessonId  inner join Subject S\n"
                        + "on S.subjectId = L.subId where Q.title like ? and L.subId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setInt(2, subId);
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

    public int getTotalQuizByTypeIdAndSearch(String keyword, String typeId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(Q.quizId)\n"
                        + "from Quiz AS Q inner join Lesson AS L\n"
                        + "on Q.lessonId = L.lessonId  inner join Type T\n"
                        + "on T.typeId = Q.typeId where Q.title like ? and T.typeId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, typeId);
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

    public int getTotalQuizByTypeIdAndSubIdAndSearch(String keyword, int subId, String typeId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(Q.quizId)\n"
                        + "from Quiz AS Q inner join Lesson AS L\n"
                        + "on Q.lessonId = L.lessonId  inner join Type T\n"
                        + "on T.typeId = Q.typeId inner join Subject S\n"
                        + "on S.subjectId = L.subId where Q.title like ? \n"
                        + "and T.typeId = ? and L.subId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, typeId);
                ps.setInt(3, subId);
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

    public List<Quiz> getListQuizzesBySearchAndPagging(int page, int PAGE_SIZE_6, String keyword) {
        List<Quiz> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by Q.quizId asc) as r,\n"
                        + "Q.quizId, Q.title, Q.level, Q.img_url, Q.status, Q.rate, Q.lessonId, Q.subId, Q.typeId, "
                        + "Q.duration, Q.description, Q.totalQues, T.typeName, S.subjectName from Quiz AS Q left join Lesson AS L \n"
                        + "on Q.lessonId = L.lessonId inner join Subject S on L.subId = S.subjectId \n"
                        + "inner join Type T on Q.typeId = T.typeId where Q.title like ?) \n"
                        + "select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setInt(2, page);
                ps.setInt(3, PAGE_SIZE_6);
                ps.setInt(4, PAGE_SIZE_6);
                ps.setInt(5, page);
                ps.setInt(6, PAGE_SIZE_6);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = Quiz.builder()
                            .quizId(rs.getInt(2))
                            .title(rs.getString(3))
                            .level(rs.getString(4))
                            .imgUrl(rs.getString(5))
                            .status(rs.getBoolean(6))
                            .rate(rs.getFloat(7))
                            .lessonId(rs.getInt(8))
                            .subId(rs.getInt(9))
                            .typeId(rs.getString(10))
                            .duration(rs.getInt(11))
                            .description(rs.getString(12))
                            .totalQues(rs.getInt(13))
                            .typeName(rs.getString(14))
                            .subjectName(rs.getString(15))
                            .build();
                    list.add(quiz);
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

    public List<Quiz> getListQuizzesBySubIdAndSearchAndPagging(int page, int PAGE_SIZE_6, String keyword, int subId) {
        List<Quiz> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by Q.quizId asc) as r,\n"
                        + "Q.quizId, Q.title, Q.level, Q.img_url, Q.status, Q.rate, Q.lessonId, Q.subId, Q.typeId, "
                        + "Q.duration, Q.description, Q.totalQues, T.typeName, S.subjectName from Quiz AS Q left join Lesson AS L \n"
                        + "on Q.lessonId = L.lessonId inner join Subject S on L.subId = S.subjectId \n"
                        + "inner join Type T on Q.typeId = T.typeId where Q.title like ? and S.subjectId = ?) \n"
                        + "select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setInt(2, subId);
                ps.setInt(3, page);
                ps.setInt(4, PAGE_SIZE_6);
                ps.setInt(5, PAGE_SIZE_6);
                ps.setInt(6, page);
                ps.setInt(7, PAGE_SIZE_6);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = Quiz.builder()
                            .quizId(rs.getInt(2))
                            .title(rs.getString(3))
                            .level(rs.getString(4))
                            .imgUrl(rs.getString(5))
                            .status(rs.getBoolean(6))
                            .rate(rs.getFloat(7))
                            .lessonId(rs.getInt(8))
                            .subId(rs.getInt(9))
                            .typeId(rs.getString(10))
                            .duration(rs.getInt(11))
                            .description(rs.getString(12))
                            .totalQues(rs.getInt(13))
                            .typeName(rs.getString(14))
                            .subjectName(rs.getString(15))
                            .build();
                    list.add(quiz);
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

    public List<Quiz> getListQuizzesByTypeIdSearchAndPagging(int page, int PAGE_SIZE_6, String keyword, String typeId) {
        List<Quiz> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by Q.quizId asc) as r,\n"
                        + "Q.quizId, Q.title, Q.level, Q.img_url, Q.status, Q.rate, Q.lessonId, Q.subId, Q.typeId, "
                        + "Q.duration, Q.description, Q.totalQues, T.typeName, S.subjectName from Quiz AS Q left join Lesson AS L \n"
                        + "on Q.lessonId = L.lessonId inner join Subject S on L.subId = S.subjectId \n"
                        + "inner join Type T on Q.typeId = T.typeId where Q.title like ? and T.typeId = ? ) \n"
                        + "select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, typeId);
                ps.setInt(3, page);
                ps.setInt(4, PAGE_SIZE_6);
                ps.setInt(5, PAGE_SIZE_6);
                ps.setInt(6, page);
                ps.setInt(7, PAGE_SIZE_6);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = Quiz.builder()
                            .quizId(rs.getInt(2))
                            .title(rs.getString(3))
                            .level(rs.getString(4))
                            .imgUrl(rs.getString(5))
                            .status(rs.getBoolean(6))
                            .rate(rs.getFloat(7))
                            .lessonId(rs.getInt(8))
                            .subId(rs.getInt(9))
                            .typeId(rs.getString(10))
                            .duration(rs.getInt(11))
                            .description(rs.getString(12))
                            .totalQues(rs.getInt(13))
                            .typeName(rs.getString(14))
                            .subjectName(rs.getString(15))
                            .build();
                    list.add(quiz);
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

    public List<Quiz> getListQuizzesBySubIdAndTypeIdAndSearchAndPagging(int page, int PAGE_SIZE_6, String keyword, int subId, String typeId) {
        List<Quiz> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by Q.quizId asc) as r,\n"
                        + "Q.quizId, Q.title, Q.level, Q.img_url, Q.status, Q.rate, Q.lessonId, Q.subId, Q.typeId, "
                        + "Q.duration, Q.description, Q.totalQues, T.typeName, S.subjectName, Q.attempt from Quiz AS Q left join Lesson AS L \n"
                        + "on Q.lessonId = L.lessonId inner join Subject S on L.subId = S.subjectId \n"
                        + "inner join Type T on Q.typeId = T.typeId where Q.title like ? and S.subjectId = ? and T.typeId = ? ) \n"
                        + "select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setInt(2, subId);
                ps.setString(3, typeId);
                ps.setInt(4, page);
                ps.setInt(5, PAGE_SIZE_6);
                ps.setInt(6, PAGE_SIZE_6);
                ps.setInt(7, page);
                ps.setInt(8, PAGE_SIZE_6);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Quiz quiz = Quiz.builder()
                            .quizId(rs.getInt(2))
                            .title(rs.getString(3))
                            .level(rs.getString(4))
                            .imgUrl(rs.getString(5))
                            .status(rs.getBoolean(6))
                            .rate(rs.getFloat(7))
                            .lessonId(rs.getInt(8))
                            .subId(rs.getInt(9))
                            .typeId(rs.getString(10))
                            .duration(rs.getInt(11))
                            .description(rs.getString(12))
                            .totalQues(rs.getInt(13))
                            .typeName(rs.getString(14))
                            .subjectName(rs.getString(15))
                            .attempt(rs.getInt(16))
                            .build();
                    list.add(quiz);
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

    public Quiz getQuizByQuizId(int quizId) {
        String sql = "select Q.quizId, Q.title, Q.level, Q.img_url, Q.status, Q.rate, Q.lessonId, Q.subId, Q.typeId,\n"
                + "Q.duration, Q.description, Q.totalQues, T.typeName, S.subjectName, Q.attempt from Quiz AS Q left join Lesson AS L \n"
                + "on Q.lessonId = L.lessonId inner join Subject S on L.subId = S.subjectId \n"
                + "inner join Type T on Q.typeId = T.typeId where Q.quizId = ?";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, quizId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return Quiz.builder()
                            .quizId(rs.getInt(1))
                            .title(rs.getString(2))
                            .level(rs.getString(3))
                            .imgUrl(rs.getString(4))
                            .status(rs.getBoolean(5))
                            .rate(rs.getFloat(6))
                            .lessonId(rs.getInt(7))
                            .subId(rs.getInt(8))
                            .typeId(rs.getString(9))
                            .duration(rs.getInt(10))
                            .description(rs.getString(11))
                            .totalQues(rs.getInt(12))
                            .typeName(rs.getString(13))
                            .subjectName(rs.getString(14))
                            .attempt(rs.getInt(15))
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

    public void insertQuiz(String title, int subId, String description, int durarion, String level, Float rate, String typeId, int lessonId, int totalQues, int attempt) {
        String sql = "INSERT INTO [SWP391_Project_Test].[dbo].[Quiz]\n"
                + "                          ([title]\n"
                + "                          ,[level]\n"
                + "                          ,[rate]\n"
                + "                         ,[subId]\n"
                + "                         ,[duration]\n"
                + "                        ,[typeId]\n"
                + "                          ,[description]\n"
                + "                        ,[lessonId]\n"
                + "                        ,[totalQues]\n"
                + "                        ,[status]\n"
                + "                        ,[attempt])\n"
                + "                    VALUES\n"
                + "                          (?,?,?,?,?,?,?,?,?,1,?)";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, title);
                ps.setString(2, level);
                ps.setFloat(3, rate);
                ps.setInt(4, subId);
                ps.setInt(5, durarion);
                ps.setString(6, typeId);
                ps.setString(7, description);
                ps.setInt(8, lessonId);
                ps.setInt(9, totalQues);
                ps.setInt(10, attempt);
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

    public void updateQuiz(String title, int subId, String description, int durarion, String level, Float rate, String typeId, int quizId, int lessonId, int totalQues, int attempt) {
        String sql = "UPDATE [SWP391_Project_Test].[dbo].[Quiz]\n"
                + "   SET [title] = ?\n"
                + "      ,[level] = ?\n"
                + "      ,[rate] = ?\n"
                + "      ,[subId] = ?\n"
                + "      ,[duration] = ?\n"
                + "      ,[typeId] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[lessonId] = ?\n"
                + "      ,[attempt] = ?\n"
                + "      ,[totalQues] = ?\n"
                + " WHERE quizId = ?";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, title);
                ps.setString(2, level);
                ps.setFloat(3, rate);
                ps.setInt(4, subId);
                ps.setInt(5, durarion);
                ps.setString(6, typeId);
                ps.setString(7, description);
                ps.setInt(8, lessonId);
                ps.setInt(9, attempt);
                ps.setInt(10, totalQues);
                ps.setInt(11, quizId);
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

    public boolean insertAnswerDetail(AnswerDetail answerDetail) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "insert into answerDetail values(?,?,?,?,?);";
                pst = con.prepareStatement(sql);
                pst.setInt(1, answerDetail.getQuestionId());
                pst.setInt(2, answerDetail.getAnswerId());
                pst.setInt(3, answerDetail.getUserId());
                pst.setInt(4, answerDetail.getQuizId());
                pst.setInt(5, answerDetail.getAttempt());
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

    public void showQuizByQuizId(int quizId) {
        String sql = "UPDATE [dbo].[Quiz]\n"
                + "   SET [status] = 1\n"
                + " WHERE quizId = ?";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, quizId);
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

    public void hideQuizByQuizId(int quizId) {
        String sql = "UPDATE [dbo].[Quiz]\n"
                + "   SET [status] = 0\n"
                + " WHERE quizId = ?";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, quizId);
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

    public boolean isDoQuizz(int userId, int quizzId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select *\n"
                        + "from QUIZ_POINT\n"
                        + "where userId = ? and quizId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, userId);
                pst.setInt(2, quizzId);
                rs = pst.executeQuery();
                if (rs.next()) {
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

    public boolean updateQuizPoint(int userId, float point, int quizzId, float pointPercent, int numQuesTrue) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "update QUIZ_POINT\n"
                        + "set point = ? ,[taken_date] = ?, [pointPercent] = ?, [numQuesTrue] = ? \n"
                        + "where userId = ? and quizId = ?";
                pst = con.prepareStatement(sql);
                PreparedStatement ps = con.prepareStatement(sql);
                DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//Zoned datetime instance
                ZonedDateTime zdt = ZonedDateTime.now();

//Get formatted String
                String nowDate = FOMATTER.format(zdt);

                pst.setDouble(1, point);
                pst.setString(2, nowDate);
                pst.setFloat(3, pointPercent);
                pst.setInt(4, numQuesTrue);
                pst.setInt(5, userId);
                pst.setInt(6, quizzId);

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

    public boolean deleteAnswerDetail(int userId, int quizzId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "delete answerDetail\n"
                        + "where userId = ? and quizId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, userId);
                pst.setInt(2, quizzId);
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

    public boolean insertQuizPoint(QuizzPoint quizzPoint, float pointPercent, int numQuesTrue) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//Zoned datetime instance
                ZonedDateTime zdt = ZonedDateTime.now();

//Get formatted String
                String nowDate = FOMATTER.format(zdt);

                String sql = "insert into QUIZ_POINT values(?,?,?,?,?,?,?);";
                pst = con.prepareStatement(sql);
                pst.setInt(1, quizzPoint.getUserId());
                pst.setDouble(2, Double.parseDouble("" + quizzPoint.getPoint()));
                pst.setInt(3, quizzPoint.getQuizzId());
                pst.setString(4, nowDate);
                pst.setFloat(5, pointPercent);
                pst.setInt(6, numQuesTrue);
                pst.setInt(7, quizzPoint.getAttempt());
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

    public QuizzPoint getQuizPoint(int userId, int quizId, int attempt) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select *\n"
                        + "from QUIZ_POINT\n"
                        + "where userId = ? and quizId = ? and attempt = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, userId);
                pst.setInt(2, quizId);
                pst.setInt(3, attempt);
                rs = pst.executeQuery();
                if (rs.next()) {
                    QuizzPoint quizzPoint = QuizzPoint.builder()
                            .id(rs.getInt(1))
                            .userId(userId)
                            .point(rs.getFloat(3))
                            .quizzId(quizId)
                            .attempt(rs.getInt(8))
                            .build();
                    return quizzPoint;

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

    public QuizzPoint getQuizPointAttempt(int userId, int quizId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select *\n"
                        + "from QUIZ_POINT\n"
                        + "where quizId = ? and userId = ?\n"
                        + "order by attempt desc";
                pst = con.prepareStatement(sql);
                pst.setInt(1, quizId);
                pst.setInt(2, userId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    QuizzPoint quizzPoint = QuizzPoint.builder()
                            .id(rs.getInt(1))
                            .userId(userId)
                            .point(rs.getFloat(3))
                            .quizzId(quizId)
                            .attempt(rs.getInt(8))
                            .build();
                    return quizzPoint;

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

    public boolean checkUserIsChoosen(int answerId, int quizId, int userid, int attempt) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select *\n"
                        + "from answerDetail\n"
                        + "where answerId = ? and quizId = ? and userId = ? and attempt = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, answerId);
                pst.setInt(2, quizId);
                pst.setInt(3, userid);
                pst.setInt(4, attempt);
                rs = pst.executeQuery();
                if (rs.next()) {
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

    public ArrayList<Quiz> getAllQuiz() {
        ArrayList<Quiz> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select Q.quizId, Q.title, Q.level, Q.img_url, Q.status, Q.rate, Q.lessonId, Q.subId, Q.typeId,\n"
                        + "Q.duration, Q.description, Q.totalQues, S.subjectName, Q.attempt from Quiz Q inner join Lesson L\n"
                        + "on Q.lessonId = L.lessonId inner join Subject S\n"
                        + "on S.subjectId = L.subId";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Quiz quiz = Quiz.builder()
                            .quizId(rs.getInt(1))
                            .title(rs.getString(2))
                            .level(rs.getString(3))
                            .imgUrl(rs.getString(4))
                            .status(rs.getBoolean(5))
                            .rate(rs.getFloat(6))
                            .lessonId(rs.getInt(7))
                            .subId(rs.getInt(8))
                            .typeId(rs.getString(9))
                            .duration(rs.getInt(10))
                            .description(rs.getString(11))
                            .totalQues(rs.getInt(12))
                            .subjectName(rs.getString(13))
                            .attempt(rs.getInt(14))
                            .build();

                    list.add(quiz);
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

    public ArrayList<Quiz> getListQuizzesBySubId(int subId) {
        ArrayList<Quiz> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select Q.quizId, Q.title, Q.level, Q.img_url, Q.status, Q.rate, Q.lessonId, Q.subId, Q.typeId,\n"
                        + "                       Q.duration, Q.description, Q.totalQues, S.subjectName, Q.attempt from Quiz Q inner join Lesson L\n"
                        + "                       on Q.lessonId = L.lessonId inner join Subject S\n"
                        + "                      on S.subjectId = L.subId where Q.subId=?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, subId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Quiz quiz = Quiz.builder()
                            .quizId(rs.getInt(1))
                            .title(rs.getString(2))
                            .level(rs.getString(3))
                            .imgUrl(rs.getString(4))
                            .status(rs.getBoolean(5))
                            .rate(rs.getFloat(6))
                            .lessonId(rs.getInt(7))
                            .subId(rs.getInt(8))
                            .typeId(rs.getString(9))
                            .duration(rs.getInt(10))
                            .description(rs.getString(11))
                            .totalQues(rs.getInt(12))
                            .subjectName(rs.getString(13))
                            .attempt(rs.getInt(14))
                            .build();

                    list.add(quiz);
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

    public boolean checkExistDoQuiz(int quizId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select * from QUIZ_POINT where quizId = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, quizId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
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
}
