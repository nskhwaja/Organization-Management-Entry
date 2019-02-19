package Team5.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.util.ArrayList;

import Team5.business.StudentOrganization;
/**
 *
 * @author NSKhwaja
 */
public class SOrgDB {

    public static int insert(StudentOrganization org ) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO StudentOrganization (orgname,description,meetingdate,meetingtime,contactinformation)"
                + "VALUES (?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, org.getorgname());
            ps.setString(2, org.getorgDescription());
            ps.setString(3, org.getmeetingDate());
            ps.setString(4, org.getmeetingTime());
            ps.setString(5, org.getcontactInformation());
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