package tr.mvs.studentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.mvs.studentmanager.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
