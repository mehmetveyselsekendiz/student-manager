package tr.mvs.studentmanager.service;

import tr.mvs.studentmanager.model.Student;

import java.util.List;

public interface StudentService {

    Student getStudentById(Long id);

    List<Student> getStudents();

    void deleteStudentById(Long id);

    void deleteStudents();

    Student saveStudent(Student student);

}
