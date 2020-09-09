package tr.mvs.studentmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.mvs.studentmanager.model.Lecture;
import tr.mvs.studentmanager.service.LectureService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LectureController {

    @Autowired
    private LectureService lectureService;

    @GetMapping(value = "/lectures")
    public ResponseEntity<List<Lecture>> getAllLectures() {
        List<Lecture> lectures = this.lectureService.getLectures();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(lectures);
    }

    @GetMapping(value = "/lectures/{id}")
    public ResponseEntity<Lecture> getLectureById(@PathVariable(name = "id") Long id) {
        Lecture lecture = this.lectureService.getLectureById(id);

        if (lecture != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(lecture);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping(value = "/lectures")
    public ResponseEntity<Lecture> deleteAllLectures() {
        this.lectureService.deleteLectures();

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping(value = "/lectures/{id}")
    public ResponseEntity<Lecture> deleteLectureById(@PathVariable(name = "id") Long id) {
        Lecture lecture = this.lectureService.getLectureById(id);

        if (lecture != null) {
            this.lectureService.deleteLectureById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(lecture);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PostMapping(value = "/lectures")
    public ResponseEntity<Lecture> createLecture(@RequestBody Lecture lecture) {
        Lecture savedLecture = this.lectureService.saveLecture(lecture);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedLecture);
    }

    @PutMapping(value = "/lectures/{id}")
    public ResponseEntity<Lecture> updateLecture(@PathVariable(name = "id") Long id,
                                                 @RequestBody Lecture lecture) {
        Lecture lectureOnDb = this.lectureService.getLectureById(id);

        if (lectureOnDb != null) {

            lectureOnDb.setName(lecture.getName());
            lectureOnDb.setCredit(lecture.getCredit());
            lectureOnDb.setLecturer(lecture.getLecturer());

            Lecture savedLecture = this.lectureService.saveLecture(lectureOnDb);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(savedLecture);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

    }

    @GetMapping(value = "/lectures/search")
    public ResponseEntity<List<Lecture>> searchLectures(@RequestParam(name = "name") String name) {
        List<Lecture> allLectures = this.lectureService.getLectures();

        List<Lecture> foundLectures = new ArrayList<>();
        if (name != null) {
            for (Lecture lecture : allLectures) {
                if (lecture.getName().toLowerCase().contains(name.toLowerCase())) {
                    foundLectures.add(lecture);
                }
            }
        } else {
            foundLectures = allLectures;
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(foundLectures);
    }
}
