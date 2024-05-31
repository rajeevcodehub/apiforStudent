package com.example.test.service;

import com.example.test.model.Student;
import com.example.test.repository.StudentRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;


@Service
public class StudentsData {

    @Autowired
    StudentRepository studentRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();



    public Student getStudent(String studentId) {
        Student student = studentRepository.findByStudentId(studentId);
        return student;
    }

    public Student updateStudent(String studentId, String name) {
        Student student = studentRepository.findByStudentId(studentId);
        student.setName(name);
        studentRepository.save(student);
        return student;
    }
}
