package com.wjm.spring_mybatis.dao;

import java.util.List;

import com.wjm.spring_mybatis.entities.Student;

public interface StudentDao {

    int addStudent(Student student);

    int updateStudent(Student student);

    Student findStudentByNumber(String number);

    Student findStudentById(int id);

    List<Student> findAll();

    List<Student> findAllWithTeachers();

    int selectTeachers(int studentId, List<Integer>teacherIds);
}
