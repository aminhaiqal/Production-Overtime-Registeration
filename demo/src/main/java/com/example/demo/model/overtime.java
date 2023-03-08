package com.example.demo.model;

public class overtime {
    private String staff_id;
    private boolean MON;
    private boolean TUE;
    private boolean WED;
    private boolean THU;
    private boolean FRI;
    private boolean SAT;
    private boolean SUN;

    public overtime() {}

    public overtime(String staff_id, boolean MON, boolean TUE, boolean WED, boolean THU, boolean FRI, boolean SAT, boolean SUN) {
        this.staff_id = staff_id;
        this.MON = MON;
        this.TUE = TUE;
        this.WED = WED;
        this.THU = THU;
        this.FRI = FRI;
        this.SAT = SAT;
        this.SUN = SUN;
    }

    public String getStaff_id() {
        return staff_id;
    }
    public boolean getMON() {
        return MON;
    }
    public boolean getTUE() {
        return TUE;
    }
    public boolean getWED() {
        return WED;
    }
    public boolean getTHU() {
        return THU;
    }
    public boolean getFRI() {
        return FRI;
    }
    public boolean getSAT() {
        return SAT;
    }
    public boolean getSUN() {
        return SUN;
    }
}
