/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Team5.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Team5.business.Advisor;
/**
 *
 * @author NSKhwaja
 */
public class AdvisorDB {

    public static int insert(Advisor advisor) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Advisor (adhawkid,firstname, lastname,position,contactinformation, orgname,semester)"
                + "VALUES (?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, advisor.getAdhawkid());
            ps.setString(2, advisor.getfirstname());
            ps.setString(3, advisor.getlastname());
            ps.setString(4, advisor.getPosition());
            ps.setString(5, advisor.getcontactinformation());
            ps.setString(6, advisor.getorgname());
            ps.setString(7, advisor.getsemester());
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
