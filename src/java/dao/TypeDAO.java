/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import model.Post;
import model.Role;
import model.Type;

/**
 *
 * @author yentt
 */
public class TypeDAO extends DBContext implements Serializable {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List<Type> getTypes() {
        List<Type> types = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select typeId, typeName\n"
                        + "from Type\n"
                        + "where typeId like '%l%'";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Type type = Type.builder().typeId(rs.getString(1))
                            .typeName(rs.getString(2)).build();
                    types.add(type);
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
        return types;
    }

    public String getTypeNameByTypeId(String typeId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select typeName from Type where typeId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, typeId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getString(1);
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

    public List<Type> getListTypeQuizes() {
        List<Type> types = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select typeId, typeName\n"
                        + "from Type\n"
                        + "where typeId like '%Q%'";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Type type = Type.builder().typeId(rs.getString(1))
                            .typeName(rs.getString(2)).build();
                    types.add(type);
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
        return types;
    }
    
    public List<Type> getListAllType() {
        List<Type> types = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select typeId, typeName\n"
                        + "from Type\n";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Type type = Type.builder().typeId(rs.getString(1))
                            .typeName(rs.getString(2)).build();
                    types.add(type);
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
        return types;
    }
}
