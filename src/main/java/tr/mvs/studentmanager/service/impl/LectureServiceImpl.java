package tr.mvs.studentmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.mvs.studentmanager.model.Lecture;
import tr.mvs.studentmanager.repository.LectureRepository;
import tr.mvs.studentmanager.service.LectureService;

import java.util.List;
import java.util.Optional;

@Service
public class LectureServiceImpl implements LectureService {

    @Autowired
    private LectureRepository lectureRepository;

    @Override
    public Lecture getLectureById(Long id) {
        Optional<Lecture> optionalLecture = this.lectureRepository.findById(id);
        return optionalLecture.orElse(null);
    }

    @Override
    public List<Lecture> getLectures() {
        return this.lectureRepository.findAll();
    }

    @Override
    public void deleteLectureById(Long id) {
        this.lectureRepository.deleteById(id);
    }

    @Override
    public void deleteLectures() {
        this.lectureRepository.deleteAll();
    }

    @Override
    public Lecture saveLecture(Lecture lecture) {
        return this.lectureRepository.save(lecture);
    }
}
