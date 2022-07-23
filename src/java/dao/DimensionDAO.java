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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dimension;
import model.Role;
import model.Type;

/**
 *
 * @author 84969
 */
public class DimensionDAO extends DBContext {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Dimension getDimensionById(int dimId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "Select * from Dimension where Dimension.dimId = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, dimId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Dimension d = Dimension.builder()
                            .dimId(rs.getInt("dimId"))
                            .typeId(rs.getString("typeId"))
                            .name(rs.getString("name"))
                            .description(rs.getString("description"))
                            .build();
                    return d;
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

    public boolean deleteDimensionInSubject(int dimId, int subId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM SubjectDimension WHERE dimId = ? and subjectId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, dimId);
                ps.setInt(2, subId);
                int check = ps.executeUpdate();
                if (check != 0) {
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

    public ArrayList<Dimension> getAllDimensionBySubjectId(int id) {

        ArrayList<Dimension> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "Select * from Dimension inner join SubjectDimension\n"
                        + "on Dimension.dimId = SubjectDimension.dimId\n"
                        + "AND subjectId =?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {

                    Dimension d = Dimension.builder()
                            .dimId(rs.getInt("dimId"))
                            .typeId(rs.getString("typeId"))
                            .name(rs.getString("name"))
                            .description(rs.getString("description"))
                            .build();

                    list.add(d);
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

    public ArrayList<Type> getAllTypeDimension() {
        ArrayList<Type> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct T.typeId, T.typeName from Dimension as D inner join [Type] as T on D.typeId=T.typeId";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {

                    Type t = Type.builder()
                            .typeId(rs.getString("typeId"))
                            .typeName(rs.getString("typeName"))
                            .build();

                    list.add(t);
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

    public boolean updateDimension(int dimId, String name, String typeId, String description) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "UPDATE [Dimension]\n"
                        + "   SET [typeId] =?,[name] =?,[description] =? WHERE dimId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, typeId);
                ps.setString(2, name);
                ps.setString(3, description);
                ps.setInt(4, dimId);
                int check = ps.executeUpdate();
                if (check != 0) {
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

    public boolean createDimensionInSubject(int subId, int dimId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [SubjectDimension] ([subjectId],[dimId])\n"
                        + "     VALUES(?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, subId);
                ps.setInt(2, dimId);
                System.out.println("dimId in dao " + dimId);
                int check = ps.executeUpdate();
                if (check != 0) {
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

    public void createDimension(Dimension dim) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Dimension]\n"
                        + "           ([typeId]\n"
                        + "           ,[name]\n"
                        + "           ,[description])\n"
                        + "     VALUES\n"
                        + "           (?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, dim.getTypeId());
                ps.setString(2, dim.getName());
                ps.setString(3, dim.getDescription());
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

    public Dimension getDimensionByInfo(String name, String typeId, String description) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select * from Dimension as D where D.name = ? and D.typeId=? and D.[description]= ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, typeId);
                ps.setString(3, description);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    Dimension d = Dimension.builder()
                            .dimId(rs.getInt("dimId"))
                            .typeId(rs.getString("typeId"))
                            .name(rs.getString("name"))
                            .description(rs.getString("description"))
                            .build();

                    return d;
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

    public List<Type> getAllType() {
        ArrayList<Type> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "Select * from Type where typeId like '%d%'";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Type t = Type.builder()
                            .typeId(rs.getString(1))
                            .typeName(rs.getString(2))
                            .build();
                    list.add(t);
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

    public void deleteDimension(int dimId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM [dbo].[Dimension]\n"
                        + "      WHERE dimId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, dimId);
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

    public void updateDimension(Dimension dim) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Dimension]\n"
                        + "   SET [typeId] = ?\n"
                        + "      ,[name] = ?\n"
                        + "      ,[description] = ?\n"
                        + " WHERE dimId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, dim.getTypeId());
                ps.setString(2, dim.getName());
                ps.setString(3, dim.getDescription());
                ps.setInt(4, dim.getDimId());
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

    public List<Dimension> getAllDimension() {
        ArrayList<Dimension> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "Select * from Dimension ";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Dimension d = Dimension.builder()
                            .dimId(rs.getInt(1))
                            .typeId(rs.getString(2))
                            .name(rs.getString(3))
                            .description(rs.getString(4))
                            .build();

                    list.add(d);
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
