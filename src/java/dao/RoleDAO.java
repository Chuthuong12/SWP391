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
import model.Role;

/**
 *
 * @author Fangl
 */
public class RoleDAO extends DBContext implements Serializable {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Role getRoleById(int roleId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "Select *\n"
                        + "From Role R\n"
                        + "where R.roleId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, roleId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String roleName = rs.getString("roleName");
                    Role role = new Role(roleId, roleName);
                    return role;
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

    public ArrayList<Role> getAllRole() {
        ArrayList<Role> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select * from [Role]";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String roleName = rs.getString("roleName");
                    int roleId = rs.getInt("roleId");
                    Role role = new Role(roleId, roleName);
                    list.add(role);
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
