package com.xiaoshu.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
public class Jiyun_student {
    @Id
    private Integer id;
    private Integer course_id;
    private String name;
    private Integer age;
    private Integer code;
    private String grade;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entrytime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createtime;
    @Transient
    private Course course;

    public Jiyun_student(Integer id, Integer course_id, String name, Integer age, Integer code, String grade, Date entrytime, Date createtime, Course course) {
        this.id = id;
        this.course_id = course_id;
        this.name = name;
        this.age = age;
        this.code = code;
        this.grade = grade;
        this.entrytime = entrytime;
        this.createtime = createtime;
        this.course = course;
    }

    public Jiyun_student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(Date entrytime) {
        this.entrytime = entrytime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Jiyun_student{" +
                "id=" + id +
                ", course_id=" + course_id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", code=" + code +
                ", grade='" + grade + '\'' +
                ", entrytime=" + entrytime +
                ", createtime=" + createtime +
                ", course=" + course +
                '}';
    }
}
