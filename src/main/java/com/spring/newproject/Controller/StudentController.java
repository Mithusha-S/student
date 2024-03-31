package com.spring.newproject.Controller;

import com.spring.newproject.Exception.ResourceNotFoundException;
import com.spring.newproject.Model.Student;
import com.spring.newproject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public String add(@RequestBody Student student){
        studentService.saveStudent(student);
        return "New student added";
    }

    @GetMapping("/getAll")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Integer studentId)
    {
        try {
        Student student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") Integer studentId, @RequestBody Student updatedStudent)
    {
        try {
        Student student = studentService.updateStudent(studentId,updatedStudent);
        return ResponseEntity.ok(student);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public String deleteStudent(@PathVariable("id") Integer studentId)
    {
        try {
        studentService.deleteStudent(studentId);
        return "Successfully deleted";
        }catch (ResourceNotFoundException e){
            return e.getMessage();
        }
    }


}
