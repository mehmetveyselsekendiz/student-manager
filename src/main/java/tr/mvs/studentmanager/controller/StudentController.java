package tr.mvs.studentmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.mvs.studentmanager.model.Student;
import tr.mvs.studentmanager.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = this.studentRepository.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(students);
    }

    @GetMapping(value = "/students/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable(name = "id", required = true) Long id) {
        Optional<Student> optionalStudent = this.studentRepository.findById(id);

        if (optionalStudent.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(optionalStudent.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping(value = "/students")
    public ResponseEntity<Student> deleteAllStudents() {
        this.studentRepository.deleteAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping(value = "/students/{id}")
    public ResponseEntity<Student> deleteStudentById(
            @PathVariable(name = "id", required = true) Long id) {
        Optional<Student> optionalStudent = this.studentRepository.findById(id);

        if (optionalStudent.isPresent()) {
            this.studentRepository.deleteById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(optionalStudent.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PostMapping(value = "/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = this.studentRepository.save(student);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedStudent);
    }
}
