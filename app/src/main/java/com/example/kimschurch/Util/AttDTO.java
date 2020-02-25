package com.example.kimschurch.Util;

import java.io.Serializable;

public class AttDTO implements Serializable {


    private int tag;
    private String pnum;
    private String name;
    private String att_date;
    private String att1;
    private String att2;
    private String att3;
    private String att4;
    private String att5;
    private String att5a;
    private String att5b;
    private String att5c;
    private String att_etc;


    public AttDTO(int tag, String pnum, String name, String att_date, String att1, String att2, String att3, String att4, String att5, String att5a, String att5b, String att5c, String att_etc) {
        this.tag = tag;
        this.pnum = pnum;
        this.name = name;
        this.att_date = att_date;
        this.att1 = att1;
        this.att2 = att2;
        this.att3 = att3;
        this.att4 = att4;
        this.att5 = att5;
        this.att5a = att5a;
        this.att5b = att5b;
        this.att5c = att5c;
        this.att_etc = att_etc;
    }
    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
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

    public String getAtt_date() {
        return att_date;
    }

    public void setAtt_date(String att_date) {
        this.att_date = att_date;
    }

    public String getAtt1() {
        return att1;
    }

    public void setAtt1(String att1) {
        this.att1 = att1;
    }

    public String getAtt2() {
        return att2;
    }

    public void setAtt2(String att2) {
        this.att2 = att2;
    }

    public String getAtt3() {
        return att3;
    }

    public void setAtt3(String att3) {
        this.att3 = att3;
    }

    public String getAtt4() {
        return att4;
    }

    public void setAtt4(String att4) {
        this.att4 = att4;
    }

    public String getAtt5() {
        return att5;
    }

    public void setAtt5(String att5) {
        this.att5 = att5;
    }

    public String getAtt5a() {
        return att5a;
    }

    public void setAtt5a(String att5a) {
        this.att5a = att5a;
    }

    public String getAtt5b() {
        return att5b;
    }

    public void setAtt5b(String att5b) {
        this.att5b = att5b;
    }

    public String getAtt5c() {
        return att5c;
    }

    public void setAtt5c(String att5c) {
        this.att5c = att5c;
    }

    public String getAtt_etc() {
        return att_etc;
    }

    public void setAtt_etc(String att_etc) {
        this.att_etc = att_etc;
    }
}
