package com.xiaoshu.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;
public class Course {
    @Id
    private Integer id;
    private String name;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createtime;

    public Course(Integer id, String name, String code, Date createtime) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createtime = createtime;
    }

    public Course() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
