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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RegistrationBuilder;
import model.Subject;
import model.SubjectRegistration;
import model.UserRegister;

/**
 *
 * @author Dell
 */
public class SubjectRegistrationDAO extends DBContext {

    Connection con = null; // ket noi vs sql
    PreparedStatement ps = null; // nhan cau lenh
    ResultSet rs = null; // tra kq

    public List<SubjectRegistration> getAllSubjectRegister(int userId) {
        List<SubjectRegistration> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "Select Acc.userId,Acc.username, RS.subId,RS.userId,RS.statis,S.status,S.subjectId,S.subjectName from Account Acc inner join Registration_Subject RS\n"
                        + "on Acc.userId = RS.userId\n"
                        + "inner join Subject S on S.subjectId = RS.subId where RS.userId = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                userId = 2;
                stm.setInt(1, userId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    SubjectRegistration s = SubjectRegistration.builder()
                            .usedId(rs.getInt(1))
                            .regisId(rs.getInt(3))
                            .subId(rs.getInt(4))
                            .statis(rs.getString(6))
                            .subId(rs.getInt(8))
                            .build();
                    list.add(s);
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

    public void createSubjectRegister(SubjectRegistration sr) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Registration_Subject]\n"
                        + "           ([regis_Date]\n"
                        + "           ,[statis]\n"
                        + "           ,[subId]\n"
                        + "           ,[priceId]\n"
                        + "           ,[userId])\n"
                        + "     VALUES\n"
                        + "           (?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, sr.getRegis_Date());
                ps.setString(2, sr.getStatis());
                ps.setInt(3, sr.getSubId());
                ps.setInt(4, sr.getPriceId());
                ps.setInt(5, sr.getUsedId());;
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

    public List<UserRegister> getAllUserRegister(int userId) {

        List<UserRegister> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "Select distinct RS.userId, RS.regisId,RS.statis,S.*, PP.salePrice,PP.price\n"
                        + "from Subject AS S left join SubjectPrice AS SP \n"
                        + "on S.subjectId = SP.subjectId \n"
                        + "left join PricePackage as PP on PP.priceId = SP.priceId\n"
                        + "inner join Registration_Subject RS on RS.subId = S.subjectId where RS.userId = 2";
                PreparedStatement stm = con.prepareStatement(sql);
                userId = 2;
                stm.setInt(1, userId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    UserRegister us = UserRegister.builder()
                            .userId(rs.getInt(1))
                            .regisId(rs.getInt(2))
                            .regisStatis(rs.getString(3))
                            .subjectId(rs.getInt(4))
                            .subjectName(rs.getString(5))
                            .categoryId(rs.getInt(6))
                            .status(rs.getBoolean(7))
                            .tagLine(rs.getInt(8))
                            .title(rs.getString(9))
                            .thumbnail(rs.getString(10))
                            .description(rs.getString(11))
                            .salePrice(rs.getInt(12))
                            .price(rs.getInt(13))
                            .build();
                    list.add(us);
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

    public int checkRegistration(int subjectId, int userid) {

        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select SR.regisId from Registration_Subject SR WHERE SR.userId = ? and SR.subId = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, userid);
                stm.setInt(2, subjectId);
                ResultSet rs = stm.executeQuery();
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

    public List<RegistrationBuilder> getAllRegistration() {
        List<RegistrationBuilder> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select RS.regisId, RS.regis_Date, RS.statis, PP.price, PP.name, \n"
                        + "S.subjectName, A.email from Registration_Subject RS\n"
                        + "inner join PricePackage PP on RS.priceId = PP.priceId\n"
                        + "inner join Subject S on S.subjectId = RS.subId\n"
                        + "inner join Account A on A.userId = RS.userId";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    RegistrationBuilder us = RegistrationBuilder.builder()
                            .regisId(rs.getInt(1))
                            .regisDate(rs.getString(2))
                            .status(rs.getBoolean(3))
                            .price(rs.getInt(4))
                            .priceName(rs.getString(5))
                            .subName(rs.getString(6))
                            .email(rs.getString(7))
                            .build();
                    list.add(us);
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

    public List<RegistrationBuilder> getListRegisSearchByEmail(String email) {
        List<RegistrationBuilder> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select RS.regisId, RS.regis_Date, RS.statis, PP.price, PP.name, \n"
                        + "S.subjectName, A.email from Registration_Subject RS\n"
                        + "inner join PricePackage PP on RS.priceId = PP.priceId\n"
                        + "inner join Subject S on S.subjectId = RS.subId\n"
                        + "inner join Account A on A.userId = RS.userId where A.email like ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, "%" + email + "%");
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    RegistrationBuilder us = RegistrationBuilder.builder()
                            .regisId(rs.getInt(1))
                            .regisDate(rs.getString(2))
                            .status(rs.getBoolean(3))
                            .price(rs.getInt(4))
                            .priceName(rs.getString(5))
                            .subName(rs.getString(6))
                            .email(rs.getString(7))
                            .build();
                    list.add(us);
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

    public List<RegistrationBuilder> getListRegisBySubIdAndSearchByEmail(String email, int subId) {
        List<RegistrationBuilder> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select RS.regisId, RS.regis_Date, RS.statis, PP.price, PP.name, \n"
                        + "S.subjectName, A.email from Registration_Subject RS\n"
                        + "inner join PricePackage PP on RS.priceId = PP.priceId\n"
                        + "inner join Subject S on S.subjectId = RS.subId\n"
                        + "inner join Account A on A.userId = RS.userId where A.email like ? and S.subjectId = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, "%" + email + "%");
                stm.setInt(2, subId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    RegistrationBuilder us = RegistrationBuilder.builder()
                            .regisId(rs.getInt(1))
                            .regisDate(rs.getString(2))
                            .status(rs.getBoolean(3))
                            .price(rs.getInt(4))
                            .priceName(rs.getString(5))
                            .subName(rs.getString(6))
                            .email(rs.getString(7))
                            .build();
                    list.add(us);
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

    public List<RegistrationBuilder> getListRegisByDateAndSearchByEmail(String email, String date_from, String date_to) {
        List<RegistrationBuilder> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select RS.regisId, RS.regis_Date, RS.statis, PP.price, PP.name, \n"
                        + "S.subjectName, A.email from Registration_Subject RS\n"
                        + "inner join PricePackage PP on RS.priceId = PP.priceId\n"
                        + "inner join Subject S on S.subjectId = RS.subId\n"
                        + "inner join Account A on A.userId = RS.userId where A.email like ? "
                        + "and RS.regis_Date between ? and ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, "%" + email + "%");
                stm.setString(2, date_from);
                stm.setString(3, date_to);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    RegistrationBuilder us = RegistrationBuilder.builder()
                            .regisId(rs.getInt(1))
                            .regisDate(rs.getString(2))
                            .status(rs.getBoolean(3))
                            .price(rs.getInt(4))
                            .priceName(rs.getString(5))
                            .subName(rs.getString(6))
                            .email(rs.getString(7))
                            .build();
                    list.add(us);
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

    public List<RegistrationBuilder> getListRegisByDateAndBySubIdAndSearchByEmail(String email, int subId, String date_from, String date_to) {
        List<RegistrationBuilder> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select RS.regisId, RS.regis_Date, RS.statis, PP.price, PP.name, \n"
                        + "S.subjectName, A.email from Registration_Subject RS\n"
                        + "inner join PricePackage PP on RS.priceId = PP.priceId\n"
                        + "inner join Subject S on S.subjectId = RS.subId\n"
                        + "inner join Account A on A.userId = RS.userId where A.email like ? "
                        + "and S.subjectId = ? and RS.regis_Date between ? and ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, "%" + email + "%");
                stm.setInt(2, subId);
                stm.setString(3, date_from);
                stm.setString(4, date_to);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    RegistrationBuilder us = RegistrationBuilder.builder()
                            .regisId(rs.getInt(1))
                            .regisDate(rs.getString(2))
                            .status(rs.getBoolean(3))
                            .price(rs.getInt(4))
                            .priceName(rs.getString(5))
                            .subName(rs.getString(6))
                            .email(rs.getString(7))
                            .build();
                    list.add(us);
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

    public void hideRegisByRegisId(int regisId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Registration_Subject]\n"
                        + "   SET [statis] = 'false'\n"
                        + " WHERE [regisId] = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, regisId);
                stm.executeUpdate();

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

    public void showRegisByRegisId(int regisId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Registration_Subject]\n"
                        + "   SET [statis] = 'true'\n"
                        + " WHERE [regisId] = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, regisId);
                stm.executeUpdate();

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

   public List<SubjectRegistration> getRegistrationByDimension(int id, int subjectId) {
        List<SubjectRegistration> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select * from  Registration_Subject  as RS\n"
                        + "join SubjectDimension as SD on SD.subjectId =RS.subId\n"
                        + "where SD.dimId = ? and SD.subjectId = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setInt(2, subjectId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    SubjectRegistration s = SubjectRegistration.builder()
                            .usedId(rs.getInt(1))
                            .regisId(rs.getInt(3))
                            .subId(rs.getInt(4))
                            .statis(rs.getString(6))
                            .subId(rs.getInt(8))
                            .build();
                    list.add(s);
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
