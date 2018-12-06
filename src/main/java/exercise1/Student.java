package exercise1;

import java.util.*;

/**
 * Represents a student.
 * A Student is identified by its registration number.
 * A student gets scored in different courses.
 * These scores are expressed as integers on a scale from 0 to 20.
 */
public class Student {
    /**
     * Creates a new Student.
     *
     * @throws NullPointerException if one of the parameter is null.
     */

    private String name;
    private String registrationNumber;
    private Map<String, OptionalInt> listeScore = new HashMap<>();

    public Student(String nom, String number) {
        name = nom;
        registrationNumber = number;
    }

    /**
     * Sets the score of this student for the given course.
     * If the score is set twice for the same course, the new score replaces the previous one.
     *
     * @throws NullPointerException     if the course name is null.
     * @throws IllegalArgumentException if the score is less than 0 or greater than 20.
     */
    public void setScore(String course, int score) {
        listeScore.put(course, OptionalInt.of(score));
    }

    /**
     * Returns the score for the given course.
     *
     * @return the score if found, <code>OptionalInt#empty()</code> otherwise.
     */
    public OptionalInt getScore(String course) {
        return listeScore.get(course);
    }

    /**
     * Returns the average score.
     *
     * @return the average score or 0 if there is none.
     */
    public double averageScore() {
        int taille = listeScore.size();
        double moyenne = 0;

        for (String i : listeScore.keySet()) {
            try {
                moyenne += listeScore.get(i).getAsInt();
            } catch (NoSuchElementException e) {
                taille--;
            }
        }

        if (taille == 0) {
            return 0;
        } else {
            return moyenne / taille;
        }
    }

    /**
     * Returns the course with the highest score.
     *
     * @return the best scored course or <code>Optional#empty()</code> if there is none.
     */
    public Optional<String> bestCourse() {
        Integer max = 0;
        String bestCourse = null;
        for (String key : listeScore.keySet()) {
            if (listeScore.get(key).isPresent() == true) {
                if (listeScore.get(key).getAsInt() > max) {
                    max = listeScore.get(key).getAsInt();
                    bestCourse = key;
                }
            }
        }
        //return Optional.ofNullable(bestCourse);//rendra null si y'a rien, et l'élément si il existe
        return bestCourse != null ? Optional.of(bestCourse) : Optional.ofNullable(bestCourse);
        //return nullableScore !=null ? OptionalInt.of(nullablescore) : OptionalInt.empty();
        //en dessous : des notes du prof
        //return nullableScore !=null ? OptionalInt.of(nullablescore) : OptionalInt.empty();
        /*return listeScore.entrySet().stream()
           .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
           .map(Map.Entry::getKey)
           .findFirst();*/
    }

    /**
     * Returns the highest score.
     *
     * @return the highest score or 0 if there is none.
     */
    public int bestScore() {
        Integer max = 0;
        for (String key : listeScore.keySet()) {
            if (listeScore.get(key).isPresent() == true) {
                if (listeScore.get(key).getAsInt() > max) {
                    max = listeScore.get(key).getAsInt();
                }
            }
        }
        return max;//rendra null si y'a rien, et l'élément si il existe
    }

    /**
     * Returns the set of failed courses sorted by decreasing score.
     * A course is considered as passed if its score is higher than 12.
     */
    public Set<String> failedCourses() {
        return null;
    }

    /**
     * Returns <code>true</code> if the student has an average score greater than or equal to 12.0 and has less than 3 failed courses.
     */
    public boolean isSuccessful() {
        return false;
    }

    /**
     * Returns the set of courses for which the student has received a score, sorted by course name.
     */
    public Set<String> attendedCourses() {
        return null;
    }

    public String getName() {
        return null;
    }

    public String getRegistrationNumber() {
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getName());
        sb.append(" (").append(getRegistrationNumber()).append(")");
        return sb.toString();
    }
}
