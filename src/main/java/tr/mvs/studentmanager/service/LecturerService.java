package tr.mvs.studentmanager.service;

import tr.mvs.studentmanager.model.Lecturer;

import java.util.List;

public interface LecturerService {

    Lecturer getLecturerById(Long id);

    List<Lecturer> getLecturers();

    void deleteLecturerById(Long id);

    void deleteLecturers();

    Lecturer saveLecturer(Lecturer lecturer);
}
