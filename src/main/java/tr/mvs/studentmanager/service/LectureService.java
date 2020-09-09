package tr.mvs.studentmanager.service;

import tr.mvs.studentmanager.model.Lecture;

import java.util.List;

public interface LectureService {

    Lecture getLectureById(Long id);

    List<Lecture> getLectures();

    void deleteLectureById(Long id);

    void deleteLectures();

    Lecture saveLecture(Lecture lecture);
}
