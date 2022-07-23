/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Practice;
import model.Quiz;

/**
 *
 * @author KDIchigo
 */
public class PracticeDAO {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List<Practice> getListPracticeByAccId(int userid) {
        ArrayList<Practice> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select QP.point, QP.taken_date, Qz.title, Qz.duration, Qz.totalQues, QP.pointPercent, QP.numQuesTrue, \n"
                        + "S.subjectId, S.subjectName, COUNT(Q.questionId) AS \"Number of Question\", Qz.quizId,QP.attempt from QUIZ_POINT QP\n"
                        + "inner join Quiz Qz on QP.quizId = Qz.quizId inner join Question Q on Q.quizId = Qz.quizId \n"
                        + "inner join Subject S on Qz.subId = S.subjectId WHERE QP.userId = ?\n"
                        + "GROUP BY QP.point, QP.taken_date, Qz.title, Qz.duration, Qz.totalQues, QP.pointPercent, QP.numQuesTrue, S.subjectId, S.subjectName,Qz.quizId,QP.attempt";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, userid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    Practice practice = Practice.builder()
                            .point(rs.getInt(1))
                            .taken_date(rs.getString(2))
                            .title(rs.getString(3))
                            .duration(rs.getInt(4))
                            .totalQues(rs.getInt(5))
                            .pointPercent(rs.getFloat(6))
                            .numQuesTrue(rs.getInt(7))
                            .subId(rs.getInt(8))
                            .subName(rs.getString(9))
                            .numOfQues(rs.getInt(10))
                            .quizzId(rs.getInt(11))
                            .attempt(rs.getInt(12))
                            .build();

                    list.add(practice);
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

    public List<Practice> getListPracticeByAccId(int userid, int subId) {
        ArrayList<Practice> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select QP.point, QP.taken_date, Qz.title, Qz.duration, Qz.totalQues, QP.pointPercent, QP.numQuesTrue, \n"
                        + "S.subjectId, S.subjectName, COUNT(Q.questionId) AS \"Number of Question\", Qz.quizId,QP.attempt from QUIZ_POINT QP\n"
                        + "inner join Quiz Qz on QP.quizId = Qz.quizId inner join Question Q on Q.quizId = Qz.quizId \n"
                        + "inner join Subject S on Qz.subId = S.subjectId WHERE QP.userId = ? and S.subjectId = ? \n"
                        + "GROUP BY QP.point, QP.taken_date, Qz.title, Qz.duration, Qz.totalQues, QP.pointPercent, QP.numQuesTrue, S.subjectId, S.subjectName, Qz.quizId,QP.attempt";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, userid);
                ps.setInt(2, subId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    Practice practice = Practice.builder()
                            .point(rs.getInt(1))
                            .taken_date(rs.getString(2))
                            .title(rs.getString(3))
                            .duration(rs.getInt(4))
                            .totalQues(rs.getInt(5))
                            .pointPercent(rs.getFloat(6))
                            .numQuesTrue(rs.getInt(7))
                            .subId(rs.getInt(8))
                            .subName(rs.getString(9))
                            .numOfQues(rs.getInt(10))
                            .quizzId(rs.getInt(11))
                            .attempt(rs.getInt(12))
                            .build();

                    list.add(practice);
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

    public Practice getPracticeDetail(int userId, int quizId, int attempt) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select QP.point, QP.taken_date, Qz.title, Qz.duration, Qz.totalQues, QP.pointPercent, QP.numQuesTrue, \n"
                        + "S.subjectId, S.subjectName, COUNT(Q.questionId) AS \"Number of Question\", Qz.quizId,QP.attempt from QUIZ_POINT QP\n"
                        + "inner join Quiz Qz on QP.quizId = Qz.quizId inner join Question Q on Q.quizId = Qz.quizId \n"
                        + "inner join Subject S on Qz.subId = S.subjectId WHERE QP.userId = ? and QP.attempt = ? and Qz.quizId = ?\n"
                        + "GROUP BY QP.point, QP.taken_date, Qz.title, Qz.duration, Qz.totalQues, QP.pointPercent, QP.numQuesTrue, S.subjectId, S.subjectName,Qz.quizId,QP.attempt";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, attempt);
                ps.setInt(3, quizId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    Practice practice = Practice.builder()
                            .point(rs.getInt(1))
                            .taken_date(rs.getString(2))
                            .title(rs.getString(3))
                            .duration(rs.getInt(4))
                            .totalQues(rs.getInt(5))
                            .pointPercent(rs.getFloat(6))
                            .numQuesTrue(rs.getInt(7))
                            .subId(rs.getInt(8))
                            .subName(rs.getString(9))
                            .numOfQues(rs.getInt(10))
                            .quizzId(rs.getInt(11))
                            .attempt(rs.getInt(12))
                            .build();

                    return practice;
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

    public List<Practice> getListQuizByQuizId(int quizId, int userid) {
        ArrayList<Practice> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select QP.point, QP.taken_date, Qz.title, Qz.duration, Qz.totalQues, QP.pointPercent, QP.numQuesTrue, \n"
                        + " S.subjectId, S.subjectName, COUNT(Q.questionId) AS \"Number of Question\", Qz.quizId, QP.attempt from QUIZ_POINT QP\n"
                        + "inner join Quiz Qz on QP.quizId = Qz.quizId inner join Question Q on Q.quizId = Qz.quizId \n"
                        + "inner join Subject S on Qz.subId = S.subjectId WHERE QP.userId = ? and Qz.quizId = ?\n"
                        + "GROUP BY QP.point, QP.taken_date, Qz.title, Qz.duration, Qz.totalQues, QP.pointPercent, QP.numQuesTrue, S.subjectId, \n"
                        + "S.subjectName, Qz.quizId, QP.attempt ORDER BY QP.attempt ASC";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, userid);
                ps.setInt(2, quizId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    Practice practice = Practice.builder()
                            .point(rs.getInt(1))
                            .taken_date(rs.getString(2))
                            .title(rs.getString(3))
                            .duration(rs.getInt(4))
                            .totalQues(rs.getInt(5))
                            .pointPercent(rs.getFloat(6))
                            .numQuesTrue(rs.getInt(7))
                            .subId(rs.getInt(8))
                            .subName(rs.getString(9))
                            .numOfQues(rs.getInt(10))
                            .quizzId(rs.getInt(11))
                            .attempt(rs.getInt(12))
                            .build();

                    list.add(practice);
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
