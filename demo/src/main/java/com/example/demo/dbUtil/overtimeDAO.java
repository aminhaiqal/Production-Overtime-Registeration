package com.example.demo.dbUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.overtime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

@Repository
public class overtimeDAO {
    @Autowired
    private DataSource DataSource;

    // Insert overtime into overtime table
    public int insertOvertime(overtime overtime) {
        int numRowsInserted = 0;
        try (Connection connection = DataSource.getConnection()) {
            String sql = "INSERT INTO overtime (staff_id, MON, TUE, WED, THU, FRI, SAT, SUN) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, overtime.getStaff_id());
            stmt.setBoolean(2, overtime.getMON());
            stmt.setBoolean(3, overtime.getTUE());
            stmt.setBoolean(4, overtime.getWED());
            stmt.setBoolean(5, overtime.getTHU());
            stmt.setBoolean(6, overtime.getFRI());
            stmt.setBoolean(7, overtime.getSAT());
            stmt.setBoolean(8, overtime.getSUN());
            numRowsInserted = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numRowsInserted;
    }

    // Get overtime from overtime table
    public overtime getOvertime(String staff_id) {
        overtime overtime = new overtime();
        try (Connection connection = DataSource.getConnection()) {
            String sql = "SELECT * FROM overtime WHERE staff_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, staff_id);
            stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return overtime;
    }
}
