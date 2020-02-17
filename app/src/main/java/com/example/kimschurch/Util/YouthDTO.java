package com.example.kimschurch.Util;

public class YouthDTO {
    private String pnum;
    private String name;
    private boolean att1;
    private boolean att2;
    private boolean att_srb;
    private boolean att_train;
    private boolean att_visit;

    public YouthDTO(String pnum, String name, boolean att1, boolean att2, boolean att_srb, boolean att_train, boolean att_visit) {
        this.pnum = pnum;
        this.name = name;
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

    public boolean isAtt1() {
        return att1;
    }

    public void setAtt1(boolean att1) {
        this.att1 = att1;
    }

    public boolean isAtt2() {
        return att2;
    }

    public void setAtt2(boolean att2) {
        this.att2 = att2;
    }

    public boolean isAtt_srb() {
        return att_srb;
    }

    public void setAtt_srb(boolean att_srb) {
        this.att_srb = att_srb;
    }

    public boolean isAtt_train() {
        return att_train;
    }

    public void setAtt_train(boolean att_train) {
        this.att_train = att_train;
    }

    public boolean isAtt_visit() {
        return att_visit;
    }

    public void setAtt_visit(boolean att_visit) {
        this.att_visit = att_visit;
    }
}
