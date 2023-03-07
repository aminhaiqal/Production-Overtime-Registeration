package com.example.demo.model;

public class staff {
    private int staff_id;
    private String name;
    private String dept;
    private String section;

    public staff() {}

    public staff(int staff_id, String name, String dept, String section) {
        this.staff_id = staff_id;
        this.name = name;
        this.dept = dept;
        this.section = section;
    }

    public int getStaff_id() {
        return staff_id;
    }
    public String getName() {
        return name;
    }
    public String getDept() {
        return dept;
    }
    public String getSection() {
        return section;
    }
}
