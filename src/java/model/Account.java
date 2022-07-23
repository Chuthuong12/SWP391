/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
//@AllArgsConstructor
/**
 *
 * @author thuong
 */
public class Account {
    private int userid;
    private String username;
    private String password;
    private String status;
    private String email;
    private int phone;
    private String fullname;
    private String address;
    private String gender;
    private String avatar;
    private int roleid;
    private Role role;
    private Timestamp created_date;
    private Timestamp modify_date;
    private String password_token;

    public Account() {
    }

    public Account(int userid, String username, String password, String status, String email, int phone, String fullname, String address, String gender, String avatar, int roleid, Role role, Timestamp created_date, Timestamp modify_date) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.status = status;
        this.email = email;
        this.phone = phone;
        this.fullname = fullname;
        this.address = address;
        this.gender = gender;
        this.avatar = avatar;
        this.roleid = roleid;
        this.role = role;
        this.created_date = created_date;
        this.modify_date = modify_date;
    }

    public Account(int userid, String username, String password, String status, String email, int phone, String fullname, String address, String gender, String avatar, int roleid, Role role, Timestamp created_date, Timestamp modify_date, String password_token) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.status = status;
        this.email = email;
        this.phone = phone;
        this.fullname = fullname;
        this.address = address;
        this.gender = gender;
        this.avatar = avatar;
        this.roleid = roleid;
        this.role = role;
        this.created_date = created_date;
        this.modify_date = modify_date;
        this.password_token = password_token;
    }

    public String getPassword_token() {
        return password_token;
    }

    public void setPassword_token(String password_token) {
        this.password_token = password_token;
    }
  
}
