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
import model.Topic;

/**
 *
 * @author yentt
 */
public class TopicDAO extends DBContext {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public List<Topic> getTopics() {
        List<Topic> topics = new ArrayList<>();
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = " select topicId, name, status, subId\n"
                        + "from Topic";
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Topic topic = new Topic(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getInt(4));
                    
                    topics.add(topic);
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
        return topics;
    }
    
    public Topic getTopicById(int topicId) {
        try {
            con = DBContext.makeConnection();
            if (con != null) {
                String sql = "select * from Topic where Topic.topicId=? ";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, topicId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Topic topic = new Topic(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getInt(4));
                    
                    return topic;
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
}
