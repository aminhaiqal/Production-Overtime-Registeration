package com.example.demo.controller;

import com.example.demo.dbUtil.overtimeDAO;
import com.example.demo.dbUtil.staffDAO;
import com.example.demo.model.overtime;
import com.example.demo.model.staff;

public class DataLoading {
    public DataLoading(String staff_id, String name, String dept, String section, 
                       Boolean MON, Boolean TUE, Boolean WED, Boolean THU, Boolean FRI, Boolean SAT, Boolean SUN) {
        staff staff = new staff(staff_id, name, dept, section);
        overtime overtime = new overtime(staff_id, MON, TUE, WED, THU, FRI, SAT, SUN);

        staffDAO staffDAO = new staffDAO();
        staffDAO.insertStaff(staff);

        overtimeDAO overtimeDAO = new overtimeDAO();
        overtimeDAO.insertOvertime(overtime);
    }
}