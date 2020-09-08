package tr.mvs.studentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.mvs.studentmanager.model.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
