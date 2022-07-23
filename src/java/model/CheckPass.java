/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author thuong
 */
public class CheckPass {
   public static void main(String[] args) {
      String passwd = "gygy66a"; 
      String pattern = "([a-zA-Z0-9]{6,})";// 
      System.out.println(passwd.matches(pattern));
   }

}
