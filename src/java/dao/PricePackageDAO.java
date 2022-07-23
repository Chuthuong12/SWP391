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
import model.PricePackage;

/**
 *
 * @author yentt
 */
public class PricePackageDAO extends DBContext implements Serializable {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public PricePackage getPricePackageById(int priceId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                con = DBContext.makeConnection();
                String sql = "select priceId, name, acessDuration, price, salePrice, status, description\n"
                        + "from PricePackage\n"
                        + "where priceId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, priceId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    PricePackage pricePackage = new PricePackage();
                    pricePackage.setPriceId(rs.getInt(1));
                    pricePackage.setName(rs.getString(2));
                    pricePackage.setAcessDuration(rs.getInt(3));
                    pricePackage.setPrice(rs.getFloat(4));
                    pricePackage.setSalePrice(rs.getFloat(5));
                    pricePackage.setStatus(rs.getBoolean(6));
                    pricePackage.setDescription(rs.getString(7));
                    return pricePackage;
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

    public ArrayList<PricePackage> getAllPricePackageBuSubjectId(int subjectId) {
        ArrayList<PricePackage> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                con = DBContext.makeConnection();
                String sql = "Select * from PricePackage  as P inner join SubjectPrice as SP on  P.priceId = SP.priceId where SP.subjectId=?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, subjectId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    PricePackage p = PricePackage.builder()
                            .priceId(rs.getInt("priceId"))
                            .name(rs.getString("name"))
                            .acessDuration(rs.getInt("acessDuration"))
                            .price(rs.getFloat("price"))
                            .salePrice(rs.getFloat("salePrice"))
                            .status(rs.getBoolean("status"))
                            .description(rs.getString("description"))
                            .build();

                    list.add(p);
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

    public void createPricePackage(PricePackage pricePack) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[PricePackage]\n"
                        + "           ([name]\n"
                        + "           ,[acessDuration]\n"
                        + "           ,[price]\n"
                        + "           ,[salePrice]\n"
                        + "           ,[status]\n"
                        + "           ,[description])\n"
                        + "     VALUES\n"
                        + "           (?,?,?,?,1,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, pricePack.getName());
                ps.setInt(2, pricePack.getAcessDuration());
                ps.setFloat(3, pricePack.getPrice());
                ps.setFloat(4, pricePack.getSalePrice());
                ps.setString(5, pricePack.getDescription());
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

    public PricePackage createPricePackage(String name, int acess, float price, float salePrice, String description) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[PricePackage]\n"
                        + "           ([name]\n"
                        + "           ,[acessDuration]\n"
                        + "           ,[price]\n"
                        + "           ,[salePrice]\n"
                        + "           ,[status]\n"
                        + "           ,[description])\n"
                        + "     VALUES\n"
                        + "           (?,?,?,?,1,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setInt(2, acess);
                ps.setFloat(3, price);
                ps.setFloat(4, salePrice);
                ps.setString(5, description);
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
        return null;
    }

    public void deletePricePackage(int priceId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM [dbo].[PricePackage]\n"
                        + "      WHERE priceId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, priceId);
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

    public PricePackage updatePricePackage(int priceId, String name, int acess, float price, float salePrice, Boolean status, String description) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[PricePackage]\n"
                        + "   SET [name] = ?\n"
                        + "      ,[acessDuration] = ?\n"
                        + "      ,[price] = ?\n"
                        + "      ,[salePrice] = ?\n"
                        + "      ,[status] = ?\n"
                        + "      ,[description] = ?\n"
                        + " WHERE priceId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setInt(2, acess);
                ps.setFloat(3, price);
                ps.setFloat(4, salePrice);
                ps.setBoolean(5, status);
                ps.setString(6, description);
                ps.setInt(7, priceId);
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
        return null;
    }

    public List<PricePackage> getAllPackage() {
        ArrayList<PricePackage> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "Select * from PricePackage ";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    PricePackage p = PricePackage.builder()
                            .priceId(rs.getInt(1))
                            .name(rs.getString(2))
                            .acessDuration(rs.getInt(3))
                            .price(rs.getFloat(4))
                            .salePrice(rs.getFloat(5))
                            .status(rs.getBoolean(6))
                            .description(rs.getString(7))
                            .build();

                    list.add(p);
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

    public PricePackage getAllPricePackageByPriceId(int priceId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select * from PricePackage where priceId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, priceId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return PricePackage.builder()
                            .priceId(rs.getInt(1))
                            .name(rs.getString(2))
                            .acessDuration(rs.getInt(3))
                            .price(rs.getFloat(4))
                            .salePrice(rs.getFloat(5))
                            .status(rs.getBoolean(6))
                            .description(rs.getString(7))
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

    public ArrayList<PricePackage> getAllPricePackageBySubjectId(int subjectId) {
        ArrayList<PricePackage> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                con = DBContext.makeConnection();
                String sql = "Select distinct * from PricePackage  as P inner join SubjectPrice as SP on  P.priceId = SP.priceId where SP.subjectId=?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, subjectId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    PricePackage p = PricePackage.builder()
                            .priceId(rs.getInt("priceId"))
                            .name(rs.getString("name"))
                            .acessDuration(rs.getInt("acessDuration"))
                            .price(rs.getFloat("price"))
                            .salePrice(rs.getFloat("salePrice"))
                            .status(rs.getBoolean("status"))
                            .description(rs.getString("description"))
                            .build();

                    list.add(p);
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

    public ArrayList<PricePackage> getAllPricePackage() {
        ArrayList<PricePackage> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                con = DBContext.makeConnection();
                String sql = "Select * from PricePackage ";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    PricePackage p = PricePackage.builder()
                            .priceId(rs.getInt("priceId"))
                            .name(rs.getString("name"))
                            .acessDuration(rs.getInt("acessDuration"))
                            .price(rs.getFloat("price"))
                            .salePrice(rs.getFloat("salePrice"))
                            .status(rs.getBoolean("status"))
                            .description(rs.getString("description"))
                            .build();

                    list.add(p);

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
        System.out.println("list pp in dao " + list.size());
        return list;
    }

    public boolean AddPackageInSubject(int packageId, int subId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [SWP391_Project_Test].[dbo].[SubjectPrice]\n"
                        + "           ([priceId],[subjectId])VALUES(?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, packageId);
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

    public PricePackage getPricePackageByIdInSubject(int packageId, int subId) {
        ArrayList<PricePackage> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                con = DBContext.makeConnection();
                String sql = "Select distinct * from PricePackage  as P inner join SubjectPrice as SP on  P.priceId = SP.priceId where SP.subjectId=? and P.priceId = ?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(2, packageId);
                stm.setInt(1, subId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    PricePackage p = PricePackage.builder()
                            .priceId(rs.getInt("priceId"))
                            .name(rs.getString("name"))
                            .acessDuration(rs.getInt("acessDuration"))
                            .price(rs.getFloat("price"))
                            .salePrice(rs.getFloat("salePrice"))
                            .status(rs.getBoolean("status"))
                            .description(rs.getString("description"))
                            .build();

                    return p;
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

    public boolean deletePackageInSubject(int pid, int subId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
            String sql = "DELETE FROM SubjectPrice WHERE priceId = ? and subjectId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, pid);
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

}
