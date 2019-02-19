/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Team5.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.util.ArrayList;

import Team5.business.Member;
/**
 *
 * @author NSKhwaja
 */
public class MemberDB {

    public static int insert(Member member) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Member (hawkid,firstname, lastname,major1,major2, membertype,orgname,semester)"
                + "VALUES (?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, member.gethawkid());
            ps.setString(2, member.getfirstname());
            ps.setString(3, member.getlastname());
            ps.setString(4, member.getMajor1());
            ps.setString(5, member.getMajor2());
            ps.setString(6,member.getMembertype());
            ps.setString(7,member.getMorgname());
            ps.setString(8, member.getSemester());
           return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
   /* public static ArrayList<Event> selectEvents() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Event";
        try {
            ps = connection.prepareStatement(query);
           // ps.setString(1, orgname);
            rs = ps.executeQuery();
            ArrayList<Event> events = new ArrayList<Event>();
            while (rs.next())
            {
                Event event = new Event();
                event.setId(rs.getString("id"));
                event.setTitle(rs.getString("title"));
                event.setDescription(rs.getString("description"));
                event.setLocation(rs.getString("location"));
                event.setPresenter(rs.getString("presenter"));
                event.setCompany(rs.getString("company"));
                event.setstartDate(rs.getString("startDate"));
                event.setstartTime(rs.getString("startTime"));
                event.settimeofday1(rs.getString("timeofday1"));
                event.setendDate(rs.getString("endDate"));
                event.setendTime(rs.getString("endTime"));
                event.settimeofday2(rs.getString("timeofday2"));
                event.setAttendance(rs.getString("attendence"));
                event.setstudentOrganization(rs.getString("orgname"));
                event.setSemester(rs.getString("semester"));
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }  */  
}

