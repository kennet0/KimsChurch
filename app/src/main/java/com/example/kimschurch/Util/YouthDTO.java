package com.example.kimschurch.Util;

public class YouthDTO {
    private String pnum;
    private String name;
    private String date;
    private int att1;
    private int att2;
    private int att_srb;
    private int att_train;
    private int att_visit;

    public YouthDTO(String pnum, String name, String date, int att1, int att2, int att_srb, int att_train, int att_visit) {
        this.pnum = pnum;
        this.name = name;
        this.date = date;
        this.att1 = att1;
        this.att2 = att2;
        this.att_srb = att_srb;
        this.att_train = att_train;
        this.att_visit = att_visit;
    }

    public String getPnum() {
        return pnum;
    }

    public void setPnum(String pnum) {
        this.pnum = pnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAtt1() {
        return att1;
    }

    public void setAtt1(int att1) {
        this.att1 = att1;
    }

    public int getAtt2() {
        return att2;
    }

    public void setAtt2(int att2) {
        this.att2 = att2;
    }

    public int getAtt_srb() {
        return att_srb;
    }

    public void setAtt_srb(int att_srb) {
        this.att_srb = att_srb;
    }

    public int getAtt_train() {
        return att_train;
    }

    public void setAtt_train(int att_train) {
        this.att_train = att_train;
    }

    public int getAtt_visit() {
        return att_visit;
    }

    public void setAtt_visit(int att_visit) {
        this.att_visit = att_visit;
    }
}
