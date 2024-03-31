package com.spring.newproject.Service;

import com.spring.newproject.Model.Student;

import java.util.List;

public interface StudentService {
    public Student saveStudent(Student student);
    public List<Student> getAllStudents();
    public Student getStudentById(Integer studentId);
    public Student updateStudent(Integer studentId, Student updatedStudent);
    void deleteStudent(Integer studentId);
}