package tr.mvs.studentmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.mvs.studentmanager.model.Lecturer;
import tr.mvs.studentmanager.repository.LecturerRepository;
import tr.mvs.studentmanager.service.LecturerService;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerServiceImpl implements LecturerService {

    @Autowired
    private LecturerRepository lecturerRepository;

    @Override
    public Lecturer getLecturerById(Long id) {
        Optional<Lecturer> optionalLecturer = this.lecturerRepository.findById(id);
        return optionalLecturer.orElse(null);
    }

    @Override
    public List<Lecturer> getLecturers() {
        return this.lecturerRepository.findAll();
    }

    @Override
    public void deleteLecturerById(Long id) {
        this.lecturerRepository.deleteById(id);
    }

    @Override
    public void deleteLecturers() {
        this.lecturerRepository.deleteAll();
    }

    @Override
    public Lecturer saveLecturer(Lecturer lecturer) {
        return this.lecturerRepository.save(lecturer);
    }
}
