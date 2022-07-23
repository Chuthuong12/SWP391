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
import model.Slider;
import model.Subject;

/**
 *
 * @author ADMIN
 */
public class SliderDAO{

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

   public List<Slider> getAllSlidersShow() {
        List<Slider> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by Sl.sliderId asc) as r,\n"
                        + "Sl.* from Slider AS Sl left join Subject AS S \n"
                        + "on Sl.subId = S.subjectId and Sl.status = 1) \n"
                        + "select * from t where t.status = 1";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {

                    Slider slider = Slider.builder()
                            .sliderId(rs.getInt(2))
                            .sliderUrl(rs.getString(3))
                            .status(rs.getBoolean(4))
                            .title(rs.getString(5))
                            .content(rs.getString(6))
                            .backlink(rs.getString(7))
                            .notes(rs.getString(8))
                            .isShow(rs.getBoolean(9))
                            .subId(rs.getInt(10))
                            .build();

                    list.add(slider);
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

    public List<Slider> getAllSliders() {
        List<Slider> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by Sl.sliderId asc) as r,\n"
                        + "Sl.* from Slider AS Sl left join Subject AS S \n"
                        + "on Sl.subId = S.subjectId) \n"
                        + "select * from t";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {

                    Slider slider = Slider.builder()
                            .sliderId(rs.getInt(2))
                            .sliderUrl(rs.getString(3))
                            .status(rs.getBoolean(4))
                            .title(rs.getString(5))
                            .content(rs.getString(6))
                            .backlink(rs.getString(7))
                            .notes(rs.getString(9))
                            .isShow(rs.getBoolean(8))
                            .subId(rs.getInt(10))
                            .build();

                    list.add(slider);
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

    public int getTotalSlider() {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(S.subjectId)\n"
                        + "from Slider AS Sl inner join Subject AS S\n"
                        + "on Sl.subId = S.subjectId";
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

    public int getTotalSliderShow() {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(S.subjectId)\n"
                        + "from Slider AS Sl inner join Subject AS S\n"
                        + "on Sl.subId = S.subjectId and Sl.status = 1";
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

    public int getTotalSlider(String keyword, int status) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(Sl.sliderId)\n"
                        + "from Slider AS Sl inner join Subject AS S\n"
                        + "on Sl.subId = S.subjectId\n"
                        + "and Sl.title like ? and Sl.status = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setInt(2, status);
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
    
    public int getTotalSlider(String keyword) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(Sl.sliderId)\n"
                        + "from Slider AS Sl inner join Subject AS S\n"
                        + "on Sl.subId = S.subjectId\n"
                        + "and Sl.title like ? ";
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

    public List<Slider> getListSlidersByPagging(int page, int PAGE_SIZE_3) {
        List<Slider> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by Sl.sliderId asc) as r,\n"
                        + "Sl.* from Slider AS Sl left join Subject AS S \n"
                        + "on Sl.subId = S.subjectId) \n"
                        + "select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, page);
                ps.setInt(2, PAGE_SIZE_3);
                ps.setInt(3, PAGE_SIZE_3);
                ps.setInt(4, page);
                ps.setInt(5, PAGE_SIZE_3);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Slider slider = Slider.builder()
                            .sliderId(rs.getInt(2))
                            .sliderUrl(rs.getString(3))
                            .status(rs.getBoolean(4))
                            .title(rs.getString(5))
                            .content(rs.getString(6))
                            .backlink(rs.getString(7))
                            .notes(rs.getString(8))
                            .isShow(rs.getBoolean(9))
                            .subId(rs.getInt(10))
                            .build();
                    list.add(slider);
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

    public List<Slider> getListSlidersByKeywordAndPagging(String keyword, int page, int PAGE_SIZE_3, int status) {
        List<Slider> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by Sl.sliderId asc) as r,\n"
                        + "Sl.* from Slider AS Sl left join Subject AS S \n"
                        + "on Sl.subId = S.subjectId"
                        + "where Sl.title like ? and Sl.status = ? ) select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, "%" + keyword + "%");
                stm.setInt(2, status);
                stm.setInt(3, page);
                stm.setInt(4, PAGE_SIZE_3);
                stm.setInt(5, PAGE_SIZE_3);
                stm.setInt(6, page);
                stm.setInt(7, PAGE_SIZE_3);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Slider slider = Slider.builder()
                            .sliderId(rs.getInt(2))
                            .sliderUrl(rs.getString(3))
                            .status(rs.getBoolean(4))
                            .title(rs.getString(5))
                            .content(rs.getString(6))
                            .backlink(rs.getString(7))
                            .notes(rs.getString(8))
                            .isShow(rs.getBoolean(9))
                            .subId(rs.getInt(10))
                            .build();
                    list.add(slider);
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

    public List<Slider> getListSliderByKeywordAndPagging(String keyword, int page, int PAGE_SIZE_3) {
        List<Slider> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by Sl.sliderId asc) as r,\n"
                        + "Sl.* from Slider AS Sl left join Subject AS S \n"
                        + "on Sl.subId = S.subjectId"
                        + "where Sl.title like ? ) select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setInt(2, page);
                ps.setInt(3, PAGE_SIZE_3);
                ps.setInt(4, PAGE_SIZE_3);
                ps.setInt(5, page);
                ps.setInt(6, PAGE_SIZE_3);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Slider slider = Slider.builder()
                            .sliderId(rs.getInt(2))
                            .sliderUrl(rs.getString(3))
                            .status(rs.getBoolean(4))
                            .title(rs.getString(5))
                            .content(rs.getString(6))
                            .backlink(rs.getString(7))
                            .notes(rs.getString(8))
                            .isShow(rs.getBoolean(9))
                            .subId(rs.getInt(10))
                            .build();
                    list.add(slider);
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
    
    public List<Slider> getListSliderByKeywordAndPagging(String keyword, int page, int PAGE_SIZE_3, int status) {
        List<Slider> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by Sl.sliderId asc) as r,\n" +
"                    Sl.* from Slider AS Sl left join Subject AS S \n" +
"                    on Sl.subId = S.subjectId\n" +
"                    where Sl.title like ? and Sl.status = ?) select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, "%" + keyword + "%");
                stm.setInt(2, status);
                stm.setInt(3, page);
                stm.setInt(4, PAGE_SIZE_3);
                stm.setInt(5, PAGE_SIZE_3);
                stm.setInt(6, page);
                stm.setInt(7, PAGE_SIZE_3);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {

                    Slider slider = Slider.builder()
                            .sliderId(rs.getInt(2))
                            .sliderUrl(rs.getString(3))
                            .status(rs.getBoolean(4))
                            .title(rs.getString(5))
                            .content(rs.getString(6))
                            .backlink(rs.getString(7))
                            .notes(rs.getString(8))
                            .isShow(rs.getBoolean(9))
                            .subId(rs.getInt(10))
                            .build();

                    list.add(slider);
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

    public List<Slider> getListSliderById(int sliderId) {
        List<Slider> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select * from Slider where status = 1 and sliderId = ? ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, sliderId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    Slider slider = Slider.builder()
                            .sliderId(rs.getInt(1))
                            .sliderUrl(rs.getString(2))
                            .status(rs.getBoolean(3))
                            .title(rs.getString(4))
                            .content(rs.getString(5))
                            .backlink(rs.getString(6))
                            .notes(rs.getString(7))
                            .isShow(rs.getBoolean(8))
                            .subId(rs.getInt(9))
                            .build();

                    list.add(slider);
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

    public Slider getListSliderBySliderId(int sliderId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select * from Slider where sliderId = ? ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, sliderId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    return Slider.builder()
                            .sliderId(rs.getInt(1))
                            .sliderUrl(rs.getString(2))
                            .status(rs.getBoolean(3))
                            .title(rs.getString(4))
                            .content(rs.getString(5))
                            .backlink(rs.getString(6))
                            .notes(rs.getString(7))
                            .isShow(rs.getBoolean(8))
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

    public void updateSliderHide(int sliderId) {
        String sql = "UPDATE [SWP391_Project_Test].[dbo].[Slider]\n"
                + "SET [status] = 0 WHERE [sliderId] = ? ";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, sliderId);
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

    public void updateSliderShow(int sliderId) {
        String sql = "UPDATE [SWP391_Project_Test].[dbo].[Slider]\n"
                + "   SET [status] = 1 WHERE [sliderId] = ? ";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, sliderId);
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

    public int getMinSliderId() {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select MIN(sliderId) from Slider where status = 1";
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

    public boolean getStatusBySliderId(int sliderId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select status from Slider where sliderId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, sliderId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getBoolean(1);
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

    public void updateSlider(int sliderId, String title, boolean status, String image, String content, String notes, int subId) {
        String sql = "UPDATE [SWP391_Project_Test].[dbo].[Slider]\n"
                + "   SET [slider_url] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[title] = ?\n"
                + "      ,[content] = ?\n"
                + "      ,[notes] = ?\n"
                + "      ,[subId] = ?\n"
                + " WHERE [sliderId] = ?";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, image);
                ps.setBoolean(2, status);
                ps.setString(3, title);
                ps.setString(4, content);
                ps.setString(5, notes);
                ps.setInt(6, subId);
                ps.setInt(7, sliderId);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertSlider(String title, boolean status, String image, String content, String notes, int subId) {
        String sql = "INSERT INTO [SWP391_Project_Test].[dbo].[Slider]\n"
                + "           ([slider_url]\n"
                + "           ,[status]\n"
                + "           ,[title]\n"
                + "           ,[content]\n"
                + "           ,[notes]\n"
                + "           ,[subId])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, image);
                ps.setBoolean(2, status);
                ps.setString(3, title);
                ps.setString(4, content);
                ps.setString(5, notes);
                ps.setInt(6, subId);
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

    

}
