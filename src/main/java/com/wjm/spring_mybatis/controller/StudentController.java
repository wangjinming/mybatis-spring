package com.wjm.spring_mybatis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wjm.spring_mybatis.entities.Student;
import com.wjm.spring_mybatis.service.StudentService;

@RestController
@RequestMapping(value = "/demo/", consumes = {MediaType.ALL_VALUE})
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "student", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> addStudent(@RequestBody Student student, HttpServletRequest request) {

        try {
            studentService.addStudent(student);
            return new ResponseEntity<Object>(null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("error", e.getMessage());
            return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "student", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, HttpServletRequest request) {

        try {
            studentService.updateStudent(student);
            return new ResponseEntity<Object>(null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("error", e.getMessage());
            return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "student/{number}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> findByNumber(@PathVariable("number") String number,
                                               HttpServletRequest request) {
        Student student = studentService.findStudentByNumber(number);
        return new ResponseEntity<Object>(student, HttpStatus.OK);
    }


    @RequestMapping(value = "students", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> findAll(HttpServletRequest request) {

        List<Student> students = studentService.findAll();
        return new ResponseEntity<Object>(students, HttpStatus.OK);
    }

    @RequestMapping(value = "students/teachers", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> findAllWithTeachers(HttpServletRequest request) {

        List<Student> students = studentService.findAllWithTeachers();
        return new ResponseEntity<Object>(students, HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "student/{id}/teachers", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> selectTeachers(@PathVariable("id") Integer studentId,
                                                 @RequestBody JSONObject ids,
                                                 HttpServletRequest request) {
        try {
            studentService.selectTeachers(studentId, (List<Integer>) ids.get("teacherIds"));
            return new ResponseEntity<Object>(null, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("error", e.getMessage());
            return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "studentById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> findStudentById(@PathVariable("id")int id) {
        Student student = studentService.findStudentById(id);
        return new ResponseEntity<Object>(student, HttpStatus.OK);

    }
}