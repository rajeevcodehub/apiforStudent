package com.example.test.controller;

import com.example.test.model.Student;
import com.example.test.repository.StudentRepository;
import com.example.test.service.StudentsData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentsData studentsData;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable("studentId") String studentId){
        return studentsData.getStudent(studentId);
    }

    @PostMapping("/createStudent")
    public Student createstudent(@RequestBody String responseBody) throws Exception {
        JsonNode jsonNode = objectMapper.readTree(responseBody);


        String rollId = jsonNode.get("rollId").asText();
        String name = jsonNode.get("name").asText();
        String className = jsonNode.get("className").asText();

        Student st = new Student();
        st.setRollId(rollId);
        st.setName(name);
        st.setClassName(className);
        return st;
    }

    @PostMapping("/updateStudent/{studentId}")
    public Student updateStudent(@RequestBody String responseBody, @PathVariable("studentId") String studentId) throws Exception {
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        return studentsData.updateStudent(studentId, jsonNode.get("name").asText());
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public String deleteStudent(@PathVariable("studentId") String studentId){
        studentRepository.deleteById(studentId);
        return "Student Deleted";
    }
}
