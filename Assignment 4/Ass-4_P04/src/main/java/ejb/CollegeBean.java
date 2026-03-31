package ejb;

import entity.Student;
import entity.Subject;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CollegeBean {

    @PersistenceContext(unitName = "ass4p04PU")
    private EntityManager em;

    public void addStudent(String name) {
        if (name == null || name.isBlank()) {
            return;
        }
        Student s = new Student();
        s.setStudentName(name.trim());
        em.persist(s);
    }

    public void addSubject(String name) {
        if (name == null || name.isBlank()) {
            return;
        }
        Subject sub = new Subject();
        sub.setSubjectName(name.trim());
        em.persist(sub);
    }

    public boolean assignSubject(long studentId, long subjectId) {
        Student student = em.find(Student.class, studentId);
        Subject subject = em.find(Subject.class, subjectId);
        if (student == null || subject == null) {
            return false;
        }
        student.getSubjects().add(subject);
        subject.getStudents().add(student);
        return true;
    }

    public List<Student> allStudents() {
        return em.createQuery("SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.subjects", Student.class)
                .getResultList();
    }

    public List<Subject> allSubjects() {
        return em.createQuery("SELECT DISTINCT s FROM Subject s LEFT JOIN FETCH s.students", Subject.class)
                .getResultList();
    }
}

