package tr.mvs.studentmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "lecture")
public class Lecture {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "credit", nullable = false)
    private Integer credit;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Lecturer lecturer;

    public Lecture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String lectureName) {
        this.name = lectureName;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", credit=" + credit +
                ", lecturer=" + lecturer +
                '}';
    }
}
