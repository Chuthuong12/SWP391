/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import context.DBContext;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Role;

/**
 *
 * @author thuong
 */
public class AccountDAO {

    Connection con = null; // ket noi vs sql
    PreparedStatement ps = null; // nhan cau lenh
    ResultSet rs = null; // tra kq

    public Account checkLogin(String user, String pass) {
        String query = "SELECT * From Account WHERE [username] = ? and [password] = ? ";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, user);
                ps.setString(2, pass);
                
                rs = ps.executeQuery();
                while (rs.next()) {
//                return new Account(rs.getString(1),rs.getString(2));

                //Get Role For Authen
                RoleDAO roleDAO = new RoleDAO();
                Role role = roleDAO.getRoleById(rs.getInt("roleId"));
                    return Account.builder()
                        .userid(rs.getInt(1))
                        .username(rs.getString(2))
                        .password(rs.getString(3))
                        .fullname(rs.getString(7))
                        .phone(rs.getInt(6))
                        .address(rs.getString(8))
                        .email(rs.getString(5))
                        .avatar(rs.getString(10))
                        .role(role)
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
    

    public Account CheckAccountExit(String user) {
        String query = "  select * from Account  "
                + "where [username] = ?";

        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, user);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return Account.builder()
                            .username(rs.getString(2))
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

    public void singup(String user, String pass, String email) {
        String query = "  INSERT INTO Account ([username], [password] ,[email], [roleId]) VALUES (?, ?, ?, 2)";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(query);;
                ps.setString(1, user);
                ps.setString(2, pass);
                ps.setString(3, email);
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
    //    
    //    public Account login(String user, String pass) {
    //        String query = "SELECT * From Account WHERE [USERNAME] like ? and [password] like ?";
    //        System.out.println(query);
    //        try {
    //            PreparedStatement stm = con.prepareStatement(query);
    //            stm.setString(1, user);
    //            stm.setString(2, pass);
    //            ResultSet rs = stm.executeQuery();
    //            while (rs.next()) {
    //                Account ac = new Account(rs.getString(1), rs.getString(2));
    //                return ac;
    //            }
    //        } catch (Exception e) {
    //        }
    //        return null;
    //    }

    public Account getAccountById(int userId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select userId, username, password, email, phone, fullname, address, gender, avatar, roleId, created_date, modify_date\n"
                        + "from Account\n"
                        + "where userId = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return Account.builder()
                            .userid(rs.getInt(1))
                            .username(rs.getString(2))
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

    public void changePassword(int userID, String newPassword) {
        String sql = "UPDATE Account SET password = ? WHERE userId = ?";
        PreparedStatement stm = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(sql);
                stm.setInt(2, userID);
                stm.setString(1, newPassword);
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

    public Account getAccountWithPasswordToken(String token) {
        String sql = "select userId, username,password, status, email, phone, fullname, address, gender, avatar, roleId, created_date, modify_date, password_token\n"
                + "from Account\n"
                + "where password_token = ?";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, token);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return Account.builder()
                            .userid(rs.getInt(1))
                            .username(rs.getString(2))
                            .password(rs.getString(3))
                            .fullname(rs.getString(7))
                            .phone(rs.getInt(6))
                            .address(rs.getString(8))
                            .email(rs.getString(5))
                            .avatar(rs.getString(10))
                            .password_token(rs.getString(14))
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

    public boolean updateNewPasswordForgoted(String userId, String newPassword) {
        String sql = "update Account\n"
                + "set password = ?,\n"
                + "modify_date = ?,\n"
                + "password_token = null\n"
                + "where userId = ?";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, newPassword);
                long milis = System.currentTimeMillis();
                Date newDate = new Date(milis);
                ps.setDate(2, newDate);
                ps.setString(3, userId);
                if (ps.executeUpdate() > 0) {
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

    public Timestamp getTimeModify(String email) {
        String sql = " SELECT [modify_date] FROM [Account] WHERE Email = ?";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                //PreparedStatement ps = connection.prepareStatement(sql);
                PreparedStatement stm = con.prepareStatement(sql);
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getTimestamp("modify_date");
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

    public int getAccountIdByEmail(String email) {
        String sql = "SELECT [userId] FROM [Account]WHERE [Email] = ?";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, email);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("userId");
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
        return -1;
    }

    public void editProfile(Account acc) {
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());

        String sql = "   UPDATE [Account] SET [fullname] =? , [phone] =?, [avatar]=?, [modify_date]= ?, [address] =? WHERE [userId] = ?";
        PreparedStatement stm = null;
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(sql);
                stm.setString(1, acc.getFullname());
                stm.setString(2, String.valueOf(acc.getPhone()));
                stm.setString(3, acc.getAvatar());
                stm.setTimestamp(4, createdTime);
                stm.setString(5, acc.getAddress());
                stm.setInt(6, acc.getUserid());

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

    public Account getAccount(String email, String password) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "SELECT a.*, r.* FROM [Account] a , [Role] r  WHERE [Email] = ? AND [password] = ? and r.roleId = a.roleId";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    Account account = new Account();
                    Role role = new Role();

                    role.setRole_id(rs.getInt("RoleID"));
                    role.setRole_name(rs.getString("Name"));
                    account.setUserid(rs.getInt("userId"));
                    account.setFullname(rs.getString("fullname"));
                    account.setPhone(rs.getInt("phone"));
                    account.setEmail(rs.getString("email"));
                    account.setAvatar(rs.getString("avatar"));
//                account.setRole(role);
//                account.setCreated_date(rs.);
                    account.setCreated_date(rs.getTimestamp("created_date"));
                    account.setModify_date(rs.getTimestamp("modify_date"));
                    account.setAddress(rs.getString("address"));
//                account.setCreatedTime(rs.getTimestamp("CreatedTime"));
//                account.setModifiedTime(rs.getTimestamp("ModifiedTime"));
//                account.setGender(Gender.of(rs.getBoolean("Gender")));
                    account.setPassword(rs.getString("password"));
//                account.setBalance(rs.getBigDecimal("Balance"));
                    return account;
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

    public boolean isExistAccount(String email) {
        String sql = "SELECT * FROM [Account] WHERE Email = ?";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, email);

                rs = ps.executeQuery();
                if (rs.next()) {
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

    public boolean updatePasswordTokenWithEmail(String email, String token) {
        String sql = "update Account\n"
                + "set password_token = ?\n"
                + "where email = ?";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, token);
                ps.setString(2, email);
                if (ps.executeUpdate() > 0) {
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

    public void insertPassword(int userid, String password) {

        String sqlPassword = "INSERT INTO [Account] ([password]) VALUES (? )";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sqlPassword);
                ps.setInt(1, userid);
                ps.setString(2, password);
                ps.executeQuery();
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
    
    public ArrayList<Account> getAccountByStatus(int status, int genderFilter, int roleIdFilter) {
        ArrayList<Account> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select A.userId, A.username,A.[password], A.[status], A.email, A.phone, A.fullname, A.[address], A.gender, A.avatar, A.roleId from Account as A where A.status = ?";
                if (genderFilter == 0 || genderFilter == 1) {
                    sql += " and A.gender= " + genderFilter;
                }

                if (roleIdFilter != 0) {
                    sql += " and A.roleId = " + roleIdFilter;
                }
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, status);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Role role = new RoleDAO().getRoleById(rs.getInt(11));
                    Account a = Account.builder()
                            .userid(rs.getInt(1))
                            .username(rs.getString(2))
                            .password(rs.getString(3))
                            .status(rs.getString(4))
                            .email(rs.getString(5))
                            .phone(rs.getInt(6))
                            .fullname(rs.getString(7))
                            .address(rs.getString(8))
                            .gender(rs.getString(9))
                            .avatar(rs.getString(10))
                            .role(role)
                            .build();

                    list.add(a);
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

    public Account getInforUserById(int uid) {
        String sql = "select A.userId, A.username,A.[password], A.[status], A.email, A.phone, A.fullname, A.[address], A.gender, A.avatar, A.roleId\n"
                + "                from Account as A\n"
                + "                where userId =?";
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, uid);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Role role = new RoleDAO().getRoleById(rs.getInt(11));
                    Account a = Account.builder()
                            .userid(rs.getInt(1))
                            .username(rs.getString(2))
                            .password(rs.getString(3))
                            .status(rs.getString(4))
                            .email(rs.getString(5))
                            .phone(rs.getInt(6))
                            .fullname(rs.getString(7))
                            .address(rs.getString(8))
                            .gender(rs.getString(9))
                            .avatar(rs.getString(10))
                            .role(role)
                            .build();
                    return a;
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
    
    public int getTotalAccount() {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct count(A.userId) from Account AS A ";
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

    public ArrayList<Account> getAllUser() {
        ArrayList<Account> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select distinct A.userId, A.username,A.[password], A.[status], A.email, A.phone, A.fullname, A.[address], A.gender, A.avatar, A.roleId from Account AS A";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Role role = new RoleDAO().getRoleById(rs.getInt(11));
                    Account a = Account.builder()
                            .userid(rs.getInt(1))
                            .username(rs.getString(2))
                            .password(rs.getString(3))
                            .status(rs.getString(4))
                            .email(rs.getString(5))
                            .phone(rs.getInt(6))
                            .fullname(rs.getString(7))
                            .address(rs.getString(8))
                            .gender(rs.getString(9))
                            .avatar(rs.getString(10))
                            .role(role)
                            .build();

                    list.add(a);
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

    public ArrayList<Account> getListUsersByPagging(int page, int PAGE_SIZE_10) {
        ArrayList<Account> list = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "with t as (select ROW_NUMBER() over (order by A.userId asc) as r,\n"
                        + "       A.* from Account AS A )\n"
                        + "           select * from t where r between ?*?-(?-1) and ?*?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, page);
                ps.setInt(2, PAGE_SIZE_10);
                ps.setInt(3, PAGE_SIZE_10);
                ps.setInt(4, page);
                ps.setInt(5, PAGE_SIZE_10);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Role role = new RoleDAO().getRoleById(rs.getInt(12));
                    Account a = Account.builder()
                            .userid(rs.getInt(2))
                            .username(rs.getString(3))
                            .password(rs.getString(4))
                            .status(rs.getString(5))
                            .email(rs.getString(6))
                            .phone(rs.getInt(7))
                            .fullname(rs.getString(8))
                            .address(rs.getString(9))
                            .gender(rs.getString(10))
                            .avatar(rs.getString(11))
                            .role(role)
                            .build();

                    list.add(a);
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
