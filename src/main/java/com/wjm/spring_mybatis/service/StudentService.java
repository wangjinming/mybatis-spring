package com.wjm.spring_mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wjm.spring_mybatis.dao.StudentDao;
import com.wjm.spring_mybatis.entities.Student;
import com.wjm.spring_mybatis.entities.Teacher;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao; //Dao的实现是由Mybatis自动生成的。

    @Transactional(readOnly=false, propagation=Propagation.REQUIRED)
    public void addStudent(Student student) throws Exception {
        Student another = studentDao.findStudentByNumber(student.getNumber());
        if (another != null && another.getId() != student.getId())
            throw new Exception("参数异常，number重复");
        int result = studentDao.addStudent(student);
        if (result != 1)
            throw new Exception("添加学生信息失败");
    }

    public void updateStudent(Student student) throws Exception {
        if (student.getId() == null)
            throw new Exception("参数异常，id为null");
        Student another = studentDao.findStudentByNumber(student.getNumber());
        if (another != null && another.getId() != student.getId())
            throw new Exception("参数异常，number重复");
        int result = studentDao.updateStudent(student);
        if (result != 1)
            throw new Exception("修改学生信息失败");
    }

    public Student findStudentByNumber(String number) {
        Student student = studentDao.findStudentByNumber(number);
        return student;
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }

    public List<Student> findAllWithTeachers() {
        return studentDao.findAllWithTeachers();
    }

    public Student findStudentById(int id) {
        return studentDao.findStudentById(id);
    }

    public void selectTeachers(int studentId, List<Integer> teacherIds)
            throws Exception {
        Student student = studentDao.findStudentById(studentId);
        for (Teacher teacher : student.getTeachers()) {
            if (teacher != null) {
                if (teacherIds.contains(teacher.getId()))
                    throw new Exception("参数异常,该学生" + studentId + "已经选择了此教师"
                            + teacher.getId() + "，不允许重复操作");
            }
        }

        int result = studentDao.selectTeachers(studentId, teacherIds);
        if (result != teacherIds.size())
            throw new Exception("参数异常，教师id错误");
    }
}
