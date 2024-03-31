package com.spring.newproject.Service;

import com.spring.newproject.Exception.ResourceNotFoundException;
import com.spring.newproject.Model.Student;
import com.spring.newproject.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student );
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student does not exist"+ studentId));
        return student;
    }

    @Override
    public Student updateStudent(Integer studentId, Student updatedStudent) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student does not exist"+ studentId));
        student.setFullname(updatedStudent.getFullname());
        student.setCity(updatedStudent.getCity());
        student.setPhonenumber(updatedStudent.getPhonenumber());
        student.setEmail(updatedStudent.getEmail());

        Student updatedStudentObj = studentRepository.save(student);
        return updatedStudentObj;
    }

    @Override
    public void deleteStudent(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student does not exist"+ studentId));
        studentRepository.deleteById(studentId);
    }
}
