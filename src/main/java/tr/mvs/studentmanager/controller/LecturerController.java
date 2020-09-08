package tr.mvs.studentmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.mvs.studentmanager.model.Lecturer;
import tr.mvs.studentmanager.repository.LecturerRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class LecturerController {

    @Autowired
    private LecturerRepository lecturerRepository;

    @GetMapping(value = "/lecturers")
    public ResponseEntity<List<Lecturer>> getAllLecturers() {
        List<Lecturer> lecturers = this.lecturerRepository.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(lecturers);
    }

    @GetMapping(value = "/lecturers/{id}")
    public ResponseEntity<Lecturer> getLecturerById(@PathVariable(name = "id", required = true) Long id) {
        Optional<Lecturer> optionalLecturer = this.lecturerRepository.findById(id);

        if (optionalLecturer.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(optionalLecturer.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping(value = "/lecturers")
    public ResponseEntity<Lecturer> deleteAllLecturers() {
        this.lecturerRepository.deleteAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping(value = "lecturers/{id}")
    public ResponseEntity<Lecturer> deleteLecturerById(@PathVariable(name = "id", required = true) Long id) {
        Optional<Lecturer> optionalLecturer = this.lecturerRepository.findById(id);

        if (optionalLecturer.isPresent()) {
            this.lecturerRepository.deleteById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(optionalLecturer.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PostMapping(value = "/lecturers")
    public ResponseEntity<Lecturer> createLecturer(@RequestBody Lecturer lecturer) {
        Lecturer savedLecturer = this.lecturerRepository.save(lecturer);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedLecturer);
    }
}
