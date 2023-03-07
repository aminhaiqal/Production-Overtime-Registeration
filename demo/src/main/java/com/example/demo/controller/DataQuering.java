package com.example.demo.controller;

import com.example.demo.dbUtil.overtimeDAO;
import com.example.demo.dbUtil.staffDAO;

public class DataQuering {
    public DataQuering(int staff_id) {

        staffDAO staffDAO = new staffDAO();
        staffDAO.getStaff(staff_id);

        overtimeDAO overtimeDAO = new overtimeDAO();
        overtimeDAO.getOvertime(staff_id);
}
}
