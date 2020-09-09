package tr.mvs.studentmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.mvs.studentmanager.model.Student;
import tr.mvs.studentmanager.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = this.studentService.getStudents();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(students);
    }

    @GetMapping(value = "/students/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable(name = "id") Long id) {
        Student student = this.studentService.getStudentById(id);

        if (student != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(student);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping(value = "/students")
    public ResponseEntity<Student> deleteAllStudents() {
        this.studentService.deleteStudents();

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping(value = "/students/{id}")
    public ResponseEntity<Student> deleteStudentById(
            @PathVariable(name = "id") Long id) {
        Student student = this.studentService.getStudentById(id);

        if (student != null) {
            this.studentService.deleteStudentById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(student);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PostMapping(value = "/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = this.studentService.saveStudent(student);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedStudent);
    }

    @PutMapping(value = "/students/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable(name = "id") Long id,
            @RequestBody Student student) {
        Student studentOnDb = this.studentService.getStudentById(id);

        if (studentOnDb != null) {

            studentOnDb.setFirstName(student.getFirstName());
            studentOnDb.setLastName(student.getLastName());

            Student savedStudent = this.studentService.saveStudent(studentOnDb);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(savedStudent);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping(value = "/students/search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam(name = "firstname", required = false) String firstName,
                                                        @RequestParam(name = "lastname", required = false) String lastName) {
        List<Student> allStudents = this.studentService.getStudents();

        List<Student> foundStudents = new ArrayList<>();

        if (firstName != null && lastName != null) {
            for (Student student : allStudents) {
                if (student.getFirstName().toLowerCase().contains(firstName.toLowerCase())
                        && student.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
                    foundStudents.add(student);
                }
            }
        } else if (firstName != null) {
            for (Student student : allStudents) {
                if (student.getFirstName().toLowerCase().contains(firstName.toLowerCase())) {
                    foundStudents.add(student);
                }
            }
        } else if (lastName != null) {
            for (Student student : allStudents) {
                if (student.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
                    foundStudents.add(student);
                }
            }
        } else {
            foundStudents = allStudents;
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(foundStudents);
    }
}

