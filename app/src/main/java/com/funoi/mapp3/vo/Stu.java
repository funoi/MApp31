package com.funoi.mapp3.vo;

public class Stu{
    String name;
    String sex;
    String love;
    String edu;
    String intro;

    public Stu() {
    }

    public Stu(String name, String sex, String love, String edu, String intro) {
        this.name = name;
        this.sex = sex;
        this.love = love;
        this.edu = edu;
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", love='" + love + '\'' +
                ", edu='" + edu + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
