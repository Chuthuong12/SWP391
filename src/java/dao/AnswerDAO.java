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
import model.Answer;
import model.AnswerD;

/**
 *
 * @author Fangl
 */
public class AnswerDAO implements Serializable {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List<Answer> getAnswerByQuestionId(int QuestionId) {
        List<Answer> answers = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select answerId, content, correct, quesId\n"
                        + "from Answer\n"
                        + "where quesId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, QuestionId);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        Answer answer = Answer.builder().answerId(rs.getInt(1))
                                .content(rs.getString(2))
                                .correct(rs.getBoolean(3))
                                .quesId(rs.getInt(4))
                                .build();

                        answers.add(answer);

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
        return answers;
    }

    public boolean checkAnswerIsCorrect(int answerId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select answerId, content, correct, quesId\n"
                        + "from Answer\n"
                        + "where answerId = ? and correct = 1";
                pst = con.prepareStatement(sql);
                pst.setInt(1, answerId);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        return true;
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
        return false;
    }

    public int getTotalCorrectAnswer(int questionId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select COUNT(answerId) as total\n"
                        + "from Answer\n"
                        + "where quesId = ? and correct = 1";
                pst = con.prepareStatement(sql);
                pst.setInt(1, questionId);
                rs = pst.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        return rs.getInt(1);
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
        return 0;
    }
    
    public boolean insertAnswer(AnswerD answer) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "insert into Answer values(?,?,?);";
                pst = con.prepareStatement(sql);
                pst.setString(1, answer.getContent());
                pst.setBoolean(2, answer.isCorrect());
                pst.setInt(3, answer.getQuesId());
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

    public boolean updateAnswer(int answerId, String content, boolean isCorrect) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "update Answer\n"
                        + "set content = ?,\n"
                        + "correct = ? \n"
                        + "where answerId = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, content);
                pst.setBoolean(2, isCorrect);
                pst.setInt(3, answerId);
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

    public boolean deleteAnswer(int answerId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "delete Answer where answerId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, answerId);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        return true;
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
        return false;
    }
}
