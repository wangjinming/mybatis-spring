package com.wjm.spring_mybatis.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wjm.spring_mybatis.entities.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application-context.xml") 
public class StudentServiceTest {
	@Autowired
	private StudentService studentService;
	
	@Test
	public void testAddStudent() throws Exception {
		Student student = new Student();
		student.setName("WangQin");
		student.setNumber("0002");
		student.setAge(15);
		student.setGender(0)	;
		
		studentService.addStudent(student);
	}
	
	@Test
	public void testUpdateStudent() throws Exception {
		Student student = new Student();
		student.setName("WangQin");
		student.setId(2);
		student.setAge(16);
		student.setGender(0)	;
		
		studentService.updateStudent(student);
	}
	
	@Test
	public void testFindStudentByNumber() throws Exception {
		Student student = studentService.findStudentByNumber("0001");
		System.out.println(student.getName());
	}
	
	@Test
	public void testFindAllWithTeachers() {
		List<Student> students = studentService.findAllWithTeachers();
		
		System.out.println(students);
	}
	
	@Test
	public void selectTeachers() throws Exception {
		int studentId = 1;
		
		List<Integer> teacherIds = new ArrayList<Integer> ();
		teacherIds.add(1);
		teacherIds.add(2);
		
		studentService.selectTeachers(studentId, teacherIds);
	}
}
