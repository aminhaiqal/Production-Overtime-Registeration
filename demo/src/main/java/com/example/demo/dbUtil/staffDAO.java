package com.example.demo.dbUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.example.demo.model.staff;

/*
 * ____________________________________________________________
 * |  • The @Repository annotation indicates that the staffDAO class 
 * |    is a Spring repository.
 * |  • The DataSource instance variable is autowired, allowing the 
 * |    staffDAO class to connect to the database.
 * |  • The insertStaff method inserts a new row into the staff table 
 * |    with the specified staff object's properties using a prepared 
 * |    statement. It returns the number of rows affected by the insert.
 * |  • The getStaff method returns a staff object with the specified
 * |____________________________________________________________
 */

@Repository
public class staffDAO {
    @Autowired
    private DataSource DataSource;
    
    // Insert staff into staff table
    public int insertStaff (staff staff) {
        int numRowsInserted = 0;

        try (Connection connection = DataSource.getConnection()) {
            String sql = "INSERT INTO staff (staff_id, name, dept, section) VALUES (?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, staff.getStaff_id());
            stmt.setString(2, staff.getName());
            stmt.setString(3, staff.getDept());
            stmt.setString(4, staff.getSection());
            numRowsInserted = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numRowsInserted;
    }

    // Get staff from staff table
    public staff getStaff (String staff_id) {
        staff staff = new staff();

        try (Connection connection = DataSource.getConnection()) {
            String sql = "SELECT * FROM staff WHERE staff_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, staff_id);
            stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staff;
    }
}
