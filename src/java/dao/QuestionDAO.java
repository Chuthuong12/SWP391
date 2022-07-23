/*
 * Copyright 2022 Fangl
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Fangl
 * which accompanies this distribution, and is available at
 * https://github.com/fanglong-it
 *
 * Contributors:
 *    Fangl - initial API and implementation and/or initial documentation
 */
package dao;

import context.DBContext;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import model.Dimension;
import model.Lesson;
import model.Question;
import model.QuestionL;
import model.Subject;
import model.Topic;

/**
 *
 * @author Fangl
 */
public class QuestionDAO implements Serializable {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List<Question> getQuestionByQuizId(int QuizId) {
        List<Question> questions = new ArrayList<>();

        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select questionId, content, subjectId, lessonId, topicId, level, status, quizId, dimmensionId,isMultipleChoice\n"
                        + "from Question\n"
                        + "where quizId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, QuizId);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Question question = Question.builder().questionId(rs.getInt(1))
                                .content(rs.getString(2))
                                .subjectId(rs.getInt(3))
                                .lessonId(rs.getInt(4))
                                .topicId(rs.getInt(5))
                                .level(rs.getString(6))
                                .status(rs.getBoolean(7))
                                .quizId(rs.getInt(8))
                                .dimension(rs.getString(9))
                                .isMultipleChoice(rs.getBoolean(10))
                                .build();
                        questions.add(question);
                    }
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
        return questions;
    }

    public Question getQuestionById(int QuestionId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select questionId, content, subjectId, lessonId, topicId, level, status, quizId, dimmensionId,isMultipleChoice\n"
                        + "from Question\n"
                        + "where questionId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, QuestionId);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Question question = Question.builder().questionId(rs.getInt(1))
                                .content(rs.getString(2))
                                .subjectId(rs.getInt(3))
                                .lessonId(rs.getInt(4))
                                .topicId(rs.getInt(5))
                                .level(rs.getString(6))
                                .status(rs.getBoolean(7))
                                .quizId(rs.getInt(8))
                                .dimension(rs.getString(9))
                                .isMultipleChoice(rs.getBoolean(10))
                                .build();
                        return question;
                    }
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

    public ArrayList<QuestionL> getAllQuestion() {
        ArrayList<QuestionL> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct Q.* from Question AS Q";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Subject sub = new SubjectDAO().getSubjectById(rs.getInt(3));
                    Lesson less = new LessonDAO().getLessonByLessonId(rs.getInt(4));
                    Topic topic = new TopicDAO().getTopicById(rs.getInt(5));
                    Dimension dim = new DimensionDAO().getDimensionById(rs.getInt(9));
                    QuestionL q = QuestionL.builder()
                            .questionId(rs.getInt(1))
                            .contentQuestion(rs.getString(2))
                            .subject(sub)
                            .lesson(less)
                            .topic(topic)
                            .level(rs.getString(6))
                            .status(rs.getBoolean(7))
                            .quizId(rs.getInt(8))
                            .dimmension(dim)
                            .build();

                    list.add(q);
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

    public int getTotalQuestion(String keyword) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(Q.questionId)from Question AS Q where Q.content like ?";
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

    public ArrayList<QuestionL> getListQuestionsByPagging(int page, int PAGE_SIZE_3) {
        ArrayList<QuestionL> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by Q.questionId asc) as r,\n"
                        + "      Q.* from Question AS Q )\n"
                        + "                                select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, page);
                ps.setInt(2, PAGE_SIZE_3);
                ps.setInt(3, PAGE_SIZE_3);
                ps.setInt(4, page);
                ps.setInt(5, PAGE_SIZE_3);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Subject sub = new SubjectDAO().getSubjectById(rs.getInt(4));
                    Lesson less = new LessonDAO().getLessonByLessonId(rs.getInt(5));
                    Topic topic = new TopicDAO().getTopicById(rs.getInt(6));
                    Dimension dim = new DimensionDAO().getDimensionById(rs.getInt(10));
                    QuestionL q = QuestionL.builder()
                            .questionId(rs.getInt(2))
                            .contentQuestion(rs.getString(3))
                            .subject(sub)
                            .lesson(less)
                            .topic(topic)
                            .level(rs.getString(7))
                            .status(rs.getBoolean(8))
                            .quizId(rs.getInt(9))
                            .dimmension(dim)
                            .build();

                    list.add(q);
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

    public int getTotalQuestion() {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(Q.questionId) from Question AS Q ";
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

    public ArrayList<QuestionL> getQuestionByStatus(int status) {
        ArrayList<QuestionL> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct Q.* from Question AS Q where Q.status =?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Subject sub = new SubjectDAO().getSubjectById(rs.getInt(3));
                    Lesson less = new LessonDAO().getLessonByLessonId(rs.getInt(4));
                    Topic topic = new TopicDAO().getTopicById(rs.getInt(5));
                    Dimension dim = new DimensionDAO().getDimensionById(rs.getInt(9));
                    QuestionL q = QuestionL.builder()
                            .questionId(rs.getInt(1))
                            .contentQuestion(rs.getString(2))
                            .subject(sub)
                            .lesson(less)
                            .topic(topic)
                            .level(rs.getString(6))
                            .status(rs.getBoolean(7))
                            .quizId(rs.getInt(8))
                            .dimmension(dim)
                            .build();

                    list.add(q);
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

    public ArrayList<QuestionL> getQuestionByStatus(int status, int subjectId, int lessonId, int dimId) {
        ArrayList<QuestionL> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct Q.* from Question AS Q where Q.status =?";
                if (subjectId != 0) {
                    sql += " and Q.subjectId = " + subjectId;
                }
                if (dimId != 0) {
                    sql += " and Q.dimmensionId =" + dimId;
                }
                if (lessonId != 0) {
                    sql += " and Q.lessonId = " + lessonId;
                }
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, status);

                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Subject sub = new SubjectDAO().getSubjectById(rs.getInt(3));
                    Lesson less = new LessonDAO().getLessonByLessonId(rs.getInt(4));
                    Topic topic = new TopicDAO().getTopicById(rs.getInt(5));
                    Dimension dim = new DimensionDAO().getDimensionById(rs.getInt(9));
                    QuestionL q = QuestionL.builder()
                            .questionId(rs.getInt(1))
                            .contentQuestion(rs.getString(2))
                            .subject(sub)
                            .lesson(less)
                            .topic(topic)
                            .level(rs.getString(6))
                            .status(rs.getBoolean(7))
                            .quizId(rs.getInt(8))
                            .dimmension(dim)
                            .build();

                    list.add(q);
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

    public List<QuestionL> getListQuestionByKeyword(String keyword) {
        List<QuestionL> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct Q.* from Question AS Q where Q.content like ? ";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, "%" + keyword + "%");
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Subject sub = new SubjectDAO().getSubjectById(rs.getInt(3));
                    Lesson less = new LessonDAO().getLessonByLessonId(rs.getInt(4));
                    Topic topic = new TopicDAO().getTopicById(rs.getInt(5));
                    Dimension dim = new DimensionDAO().getDimensionById(rs.getInt(9));
                    QuestionL q = QuestionL.builder()
                            .questionId(rs.getInt(1))
                            .contentQuestion(rs.getString(2))
                            .subject(sub)
                            .lesson(less)
                            .topic(topic)
                            .level(rs.getString(6))
                            .status(rs.getBoolean(7))
                            .quizId(rs.getInt(8))
                            .dimmension(dim)
                            .build();

                    list.add(q);
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
    
    public boolean insertQuestion(Question question) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "insert into Question values(?,?,?,?,?,?,?,?,?);";
                pst = con.prepareStatement(sql);
                pst.setString(1, question.getContent());
                pst.setInt(2, question.getSubjectId());
                pst.setInt(3, question.getLessonId());
                pst.setInt(4, question.getTopicId());
                pst.setString(5, question.getLevel());
                pst.setBoolean(6, true);
                pst.setInt(7, question.getQuizId());
                pst.setString(8, null);
                pst.setBoolean(9, question.isIsMultipleChoice());

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
    
    public Question getLastestQuestion() {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select TOP(1) * \n"
                        + "from Question\n"
                        + "order by questionId desc";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Question q = Question.builder()
                            .questionId(rs.getInt(1))
                            .content(rs.getString(2))
                            .subjectId(rs.getInt(3))
                            .lessonId(rs.getInt(4))
                            .topicId(rs.getInt(5))
                            .level(rs.getString(6))
                            .status(rs.getBoolean(7))
                            .quizId(rs.getInt(8))
                            .dimension(rs.getString(9))
                            .isMultipleChoice(rs.getBoolean(10))
                            .build();
                    return q;
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
    
    public boolean updateQuestion(int questionId, String content) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "update Question\n"
                        + "set content = ?\n"
                        + "where questionId = ?";
                pst = con.prepareStatement(sql);
//                pst.setString(1, question.getContent());
//                pst.setInt(2, question.getSubjectId());
//                pst.setInt(3, question.getLessonId());
//                pst.setInt(4, question.getTopicId());
//                pst.setString(5, question.getLevel());
//                pst.setBoolean(6, true);
//                pst.setInt(7, question.getQuizId());
//                pst.setString(8, null);
//                pst.setBoolean(9, question.isIsMultipleChoice());
                pst.setString(1, content);
                pst.setInt(2, questionId);

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
    
    public boolean deleteQuestion(int questionId, boolean status) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "update Question\n"
                        + "set status = ?\n"
                        + "where questionId = ?";
                pst = con.prepareStatement(sql);
//                pst.setString(1, question.getContent());
//                pst.setInt(2, question.getSubjectId());
//                pst.setInt(3, question.getLessonId());
//                pst.setInt(4, question.getTopicId());
//                pst.setString(5, question.getLevel());
//                pst.setBoolean(6, true);
//                pst.setInt(7, question.getQuizId());
//                pst.setString(8, null);
//                pst.setBoolean(9, question.isIsMultipleChoice());
                pst.setBoolean(1, status);
                pst.setInt(2, questionId);

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


    public int getTotalQuestionExist(int quizId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select COUNT(questionId) from Question where quizId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, quizId);
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
}
