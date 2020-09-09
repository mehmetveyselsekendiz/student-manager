package tr.mvs.studentmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.mvs.studentmanager.model.Student;
import tr.mvs.studentmanager.repository.StudentRepository;
import tr.mvs.studentmanager.service.StudentService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> optionalStudent = this.studentRepository.findById(id);
        return optionalStudent.orElse(null);
    }

    @Override
    public List<Student> getStudents() {
        return this.studentRepository.findAll();
    }

    @Override
    public void deleteStudentById(Long id) {
        this.studentRepository.deleteById(id);
    }

    @Override
    public void deleteStudents() {
        this.studentRepository.deleteAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return this.studentRepository.save(student);
    }
}
