package tr.mvs.studentmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.mvs.studentmanager.model.Lecture;
import tr.mvs.studentmanager.repository.LectureRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class LectureController {

    @Autowired
    private LectureRepository lectureRepository;

    @GetMapping(value = "/lectures")
    public ResponseEntity<List<Lecture>> getAllLectures() {
        List<Lecture> lectures = this.lectureRepository.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(lectures);
    }

    @GetMapping(value = "/lectures/{id}")
    public ResponseEntity<Lecture> getLectureById(
            @PathVariable(name = "id", required = true) Long id) {
        Optional<Lecture> optionalLecture = this.lectureRepository.findById(id);

        if (optionalLecture.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(optionalLecture.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping(value = "/lectures")
    public ResponseEntity<Lecture> deleteAllLectures() {
        this.lectureRepository.deleteAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping(value = "/lectures/{id}")
    public ResponseEntity<Lecture> deleteLectureById(
            @PathVariable(name = "id", required = true) Long id) {
        Optional<Lecture> optionalLecture = this.lectureRepository.findById(id);

        if (optionalLecture.isPresent()) {
            this.lectureRepository.deleteById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(optionalLecture.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PostMapping(value = "/lectures")
    public ResponseEntity<Lecture> createLecture(@RequestBody Lecture lecture) {
        Lecture savedLecture = this.lectureRepository.save(lecture);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedLecture);
    }
}
