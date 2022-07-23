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
import model.Setting;

/**
 *
 * @author Fangl
 */
public class SettingDAO implements Serializable {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List<Setting> getSettings(int page, int PAGE_SIZE, String value) {
        List<Setting> settings = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by S.settingId asc) as r,\n"
                        + "   S.* from Setting AS S where value like ?)\n"
                        + "   select * from t where r between ?*?-(?-1) and ?*?";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + value + "%");
                pst.setInt(2, page);
                pst.setInt(3, PAGE_SIZE);
                pst.setInt(4, PAGE_SIZE);
                pst.setInt(5, page);
                pst.setInt(6, PAGE_SIZE);
                rs = pst.executeQuery();
                while (rs.next()) {

                    Setting setting = Setting.builder()
                            .settingId(rs.getInt(2))
                            .name(rs.getString(3))
                            .description(rs.getString(4))
                            .value(rs.getString(5))
                            .typeId(rs.getString(6))
                            .status(rs.getBoolean(7))
                            .build();
                    settings.add(setting);

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
        return settings;

    }

    public List<Setting> getSettingsByTypeAndStatus(int page, int PAGE_SIZE, String type, boolean status) {
        List<Setting> settings = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by S.settingId asc) as r,\n"
                        + "                    S.* from Setting AS S where typeId like ? and status = ?)\n"
                        + "                                                       select * from t where r between ?*?-(?-1) and ?*?";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + type + "%");
                pst.setBoolean(2, status);
                pst.setInt(3, page);
                pst.setInt(4, PAGE_SIZE);
                pst.setInt(5, PAGE_SIZE);
                pst.setInt(6, page);
                pst.setInt(7, PAGE_SIZE);
                rs = pst.executeQuery();
                while (rs.next()) {

                    Setting setting = Setting.builder().settingId(rs.getInt(2))
                            .name(rs.getString(3))
                            .description(rs.getString(4))
                            .value(rs.getString(5))
                            .typeId(rs.getString(6))
                            .status(rs.getBoolean(7))
                            .build();
                    settings.add(setting);

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
        return settings;

    }

    public boolean insertSetting(Setting setting) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "insert into Setting values (?,?,?, ?, ?, ?);";
                pst = con.prepareStatement(sql);
                pst.setInt(1, setting.getSettingId());
                pst.setString(2, setting.getName());
                pst.setString(3, setting.getDescription());
                pst.setString(4, setting.getValue());
                pst.setString(5, setting.getTypeId());
                pst.setBoolean(6, true);

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

    public boolean updateSetting(Setting setting) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "update Setting set name = ?, description = ?, value = ?, typeId = ?, status = ? where settingId = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, setting.getName());
                pst.setString(2, setting.getDescription());
                pst.setString(3, setting.getValue());
                pst.setString(4, setting.getTypeId());
                pst.setBoolean(5, true);
                pst.setInt(6, setting.getSettingId());
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

    public int getTotalSetting() {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select count(settingId) as total\n"
                        + "from Setting";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
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

    public Setting getSettingById(int SettingId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "Select * from Setting where settingId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, SettingId);
                rs = pst.executeQuery();
                if (rs.next()) {

                    Setting setting = Setting.builder().settingId(rs.getInt(1))
                            .name(rs.getString(2))
                            .description(rs.getString(3))
                            .value(rs.getString(4))
                            .typeId(rs.getString(5))
                            .status(rs.getBoolean(6))
                            .build();
                    return setting;
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

    public boolean updateSettingStatus(int settingId, boolean status) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "update Setting set status = ? where settingId = ?";
                pst = con.prepareStatement(sql);
                pst.setBoolean(1, status);
                pst.setInt(2, settingId);

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

}
