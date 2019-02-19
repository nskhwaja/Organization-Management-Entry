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

import Team5.business.Event;
/**
 *
 * @author NSKhwaja
 */
public class EventDB {

    public static int insert(Event event) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Event (id,title, description,location,presenter, company, startDate, startTime,timeofday1,endDate,endTime, timeofday2, attendence,orgname,semester)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, event.getId());
            ps.setString(2, event.getTitle());
            ps.setString(3, event.getDescription());
            ps.setString(4, event.getLocation());
            ps.setString(5, event.getPresenter());
            ps.setString(6,event.getCompany());
            ps.setString(7, event.getstartDate());
            ps.setString(8, event.getstartTime());
            ps.setString(9, event.gettimeofday1());
            ps.setString(10, event.getendDate());
            ps.setString(11, event.getendTime());
            ps.setString(12, event.gettimeofday2());
            ps.setString(13, event.getAttendance());
            ps.setString(14,event.getstudentOrganization());
            ps.setString(15, event.getSemester());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(Event event) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE Event SET " //NEED TO UPDATE THIS FOR MY APP
                + "id = ? "
                + "title = ?"
                + "description = ? "
                + "location = ?"
                +"presenter = ?"
                +"company=?"
                + "startDate = ? "
                + "startTime = ? "
                + "timeofday1 = ? "
                + "endDate = ? "
                + "endTime = ? "
                + "timeofday2= ? "
                +"attendance=?"
                +"orgname=?"
                +"semester=?";
                        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, event.getId());
            ps.setString(2, event.getTitle());
            ps.setString(3, event.getDescription());
            ps.setString(4, event.getLocation());
            ps.setString(5, event.getPresenter());
            ps.setString(6,event.getCompany());
            ps.setString(7, event.getstartDate());
            ps.setString(8, event.getstartTime());
            ps.setString(9, event.gettimeofday1());
            ps.setString(10, event.getendDate());
            ps.setString(11, event.getendTime());
            ps.setString(12, event.gettimeofday2());
            ps.setString(13, event.getAttendance());
            ps.setString(14,event.getstudentOrganization());
            ps.setString(15, event.getSemester());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int delete(Event event) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM Event "
                + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, event.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean idExists(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT id FROM Event "
                + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public static int idMax() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT id FROM FinalProject.Event WHERE id = (Select Max(id) From FinalProject.Event); ";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            rs.next();
            String maxid1 = rs.getString("id");
            int maxid =Integer.parseInt(maxid1);
            return maxid;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static Event selectEvent(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Event "
                + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            Event event = null;
            if (rs.next()) {
                event = new Event();
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
                event.setAttendance(rs.getString("attendance"));
                event.setstudentOrganization(rs.getString("orgname"));
                event.setSemester(rs.getString("semester"));
            }
            return event;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Event> selectEvents() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Event";
        try {
            ps = connection.prepareStatement(query);
           // ps.setString(1, orgname);
            rs = ps.executeQuery();
            ArrayList<Event> events = new ArrayList<>();
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
    }    
}
