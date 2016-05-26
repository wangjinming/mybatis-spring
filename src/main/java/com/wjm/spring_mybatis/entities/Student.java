package com.wjm.spring_mybatis.entities;

import java.util.List;

public class Student {
    private Integer id; // id
    private String name; // 姓名
    private String number; // 学号,保证唯一
    private Integer gender; // 性别
    private Integer age; // 年龄
    private List<Teacher> teachers; // 授课老师集合

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", number=" + number
                + ", gender=" + gender + ", age=" + age + "]";
    }

}
