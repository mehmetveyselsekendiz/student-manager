package tr.mvs.studentmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.mvs.studentmanager.model.Lecturer;
import tr.mvs.studentmanager.service.LecturerService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @GetMapping(value = "/lecturers")
    public ResponseEntity<List<Lecturer>> getAllLecturers() {
        List<Lecturer> lecturers = this.lecturerService.getLecturers();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(lecturers);
    }

    @GetMapping(value = "/lecturers/{id}")
    public ResponseEntity<Lecturer> getLecturerById(@PathVariable(name = "id") Long id) {
        Lecturer lecturer = this.lecturerService.getLecturerById(id);

        if (lecturer != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(lecturer);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping(value = "/lecturers")
    public ResponseEntity<Lecturer> deleteAllLecturers() {
        this.lecturerService.deleteLecturers();

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping(value = "/lecturers/{id}")
    public ResponseEntity<Lecturer> deleteLecturerById(@PathVariable(name = "id") Long id) {
        Lecturer lecturer = this.lecturerService.getLecturerById(id);

        if (lecturer != null) {
            this.lecturerService.deleteLecturerById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(lecturer);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PostMapping(value = "/lecturers")
    public ResponseEntity<Lecturer> createLecturer(@RequestBody Lecturer lecturer) {
        Lecturer savedLecturer = this.lecturerService.saveLecturer(lecturer);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedLecturer);
    }

    @PutMapping(value = "/lecturers/{id}")
    public ResponseEntity<Lecturer> updateLecturer(@PathVariable(name = "id") Long id,
                                                   @RequestBody Lecturer lecturer) {
        Lecturer lecturerOnDb = this.lecturerService.getLecturerById(id);

        if (lecturerOnDb != null) {

            lecturerOnDb.setFirstName(lecturer.getFirstName());
            lecturerOnDb.setLastName(lecturer.getLastName());
            lecturerOnDb.setLectures(lecturer.getLectures());

            Lecturer savedLecturer = this.lecturerService.saveLecturer(lecturerOnDb);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(savedLecturer);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

    }

    @GetMapping(value = "/lecturers/search")
    public ResponseEntity<List<Lecturer>> searchLecturers(@RequestParam(name = "firstname", required = false) String firstName,
                                                          @RequestParam(name = "lastname", required = false) String lastName) {
        List<Lecturer> allLecturers = this.lecturerService.getLecturers();

        List<Lecturer> foundLecturers = new ArrayList<>();

        if (firstName != null && lastName != null) {
            for (Lecturer lecturer : allLecturers) {
                if (lecturer.getFirstName().toLowerCase().contains(firstName.toLowerCase())
                        && lecturer.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
                    foundLecturers.add(lecturer);
                }
            }
        } else if (firstName != null) {
            for (Lecturer lecturer : allLecturers) {
                if (lecturer.getFirstName().toLowerCase().contains(firstName.toLowerCase())) {
                    foundLecturers.add(lecturer);
                }
            }
        } else if (lastName != null) {
            for (Lecturer lecturer : allLecturers) {
                if (lecturer.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
                    foundLecturers.add(lecturer);
                }
            }
        } else {
            foundLecturers = allLecturers;
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(foundLecturers);
    }
}
