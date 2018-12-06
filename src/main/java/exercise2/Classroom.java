package exercise2;

import exercise1.DuplicateStudentException;
import exercise1.Student;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Classroom {

    Collection<Student> students;

    /**
     * Adds a student to this classroom.
     *
     * @throws NullPointerException      if the student is null.
     * @throws DuplicateStudentException if the given student is already part of the classroom.
     */
    public void addStudent(Student student) {
        if (student == null)
            throw new NullPointerException();
        if (students.contains(student)) {
            throw new DuplicateStudentException();
        }
        students.add(student);
    }

    /**
     * Returns the average score of the classroom, all courses included.
     *
     * @return the average score of the students or 0 if there is none.
     */
    public double averageScore() {
        float score = 0;
        for (Student student : students) {
            score += student.averageScore();
        }
        return score / students.size();
    }

    /**
     * Returns the number of students that are part of this classroom.
     */
    public int countStudents() {
        return students.size();
    }

    /**
     * Returns the <code>n</code> students that scored best for the given course.
     * The returned students are sorted by decreasing score.
     *
     * @param n      the number of top students to return.
     * @param course the course to evaluate.
     * @return the top <code>n</code> students or an empty list if there is no student or none did score the course.
     * @throws NullPointerException     if the course is null.
     * @throws IllegalArgumentException if <code>n</code> is less than or equal to 0.
     */

    public List<Student> topScorers(String course, int n) {
        Comparator<Student> comparator = (o1, o2) -> {
            if (o1.getScore(course).getAsInt() > o2.getScore(course).getAsInt()) {
                return 1;
            } else
                return 0;
        };
        List<Student> listOfStudents = null;
        if (course == null) {
            throw new NullPointerException();
        }
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        for (Student student : students) {
            listOfStudents.add(student);
        }
        listOfStudents.sort(comparator);
        while (listOfStudents.size() == n) {
            listOfStudents.remove(listOfStudents.size() - 1);
        }
        return listOfStudents;
    }

    /**
     * Returns all the students that are successful.
     * The returned list is sorted by decreasing average score.
     *
     * @return a sorted list of students or an empty list.
     */
    public List<Student> successfulStudents() {
        List<Student> listOfStudents = null;
        for (Student student : students) {
            if (student.isSuccessful())
                listOfStudents.add(student);
        }
        listOfStudents.sort(Comparator.comparing(Student::averageScore));
        return listOfStudents;
    }
}
