package com.example.kimschurch.Util;

public class MemberDTO {


    private String pnum;
    private String name;
    private String phone;
    private String sex;
    private String position;
    private String department;
    private String part;
    private String srbName;
    private String srbLeader;
    private String work;
    private String birthday;
    private String birthdayCal;
    private String etc;

    public MemberDTO(String  pnum, String name, String phone, String sex, String position, String department, String part, String srbName, String srbLeader, String work, String birthday, String birthdayCal, String etc) {
        this.pnum = pnum;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.position = position;
        this.department = department;
        this.part = part;
        this.srbName = srbName;
        this.srbLeader = srbLeader;
        this.work = work;
        this.birthday = birthday;
        this.birthdayCal = birthdayCal;
        this.etc = etc;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String picture) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getSrbName() {
        return srbName;
    }

    public void setSrbName(String srbName) {
        this.srbName = srbName;
    }

    public String getSrbLeader() {
        return srbLeader;
    }

    public void setSrbLeader(String srbLeader) {
        this.srbLeader = srbLeader;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthdayCal() {
        return birthdayCal;
    }

    public void setBirthdayCal(String birthdayCal) {
        this.birthdayCal = birthdayCal;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

}
