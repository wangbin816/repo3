package com.xiaoshu.entity;

public class Sousuo {
    private String name;
    private String grade;

    public Sousuo(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public Sousuo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Sousuo{" +
                "name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
