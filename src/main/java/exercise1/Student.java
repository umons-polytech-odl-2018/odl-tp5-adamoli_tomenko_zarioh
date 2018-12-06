package exercise1;

import java.util.*;
import java.util.stream.Collectors;

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
        if(nom==null || number==null)
            throw new NullPointerException();
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
        if(course==null){
            throw new NullPointerException();
        }
        if(score<0 || score>20){
            throw new IllegalArgumentException();
        }
        listeScore.put(course, OptionalInt.of(score));
    }

    /**
     * Returns the score for the given course.
     *
     * @return the score if found, <code>OptionalInt#empty()</code> otherwise.
     */
    public OptionalInt getScore(String course) {
        return listeScore.getOrDefault(course, OptionalInt.empty());
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
        /*
        * listeScore.values().stream()
        * .mapToInt(Integer::intValue)
        * .average()
        * .orElse(0.0);*/
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
            if (listeScore.get(key).isPresent()) {
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
        return max;
    }

    /**
     * Returns the set of failed courses sorted by decreasing score.
     * A course is considered as passed if its score is higher than 12.
     */
    public Set<String> failedCourses() {
        //return null;
        return listeScore.entrySet().stream()
            .filter(x->x.getValue().getAsInt()<=12)
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());
    }

    /**
     * Returns <code>true</code> if the student has an average score greater than or equal to 12.0 and has less than 3 failed courses.
     */
    public boolean isSuccessful() {
        if(this.averageScore()<12){//si la moyenne est déjà plus petite que 12, alors il a raté
            return false;
        }
        else {//sinon, on regarde si il a moins de 3 échecs, si c'est le cas, alors il réussit!
            if(this.failedCourses().size() >= 3){
                return false;
            }else return true;
        }
    }

    /**
     * Returns the set of courses for which the student has received a score, sorted by course name.
     */
    public Set<String> attendedCourses() {
        return listeScore.keySet().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getName());
        sb.append(" (").append(getRegistrationNumber()).append(")");
        return sb.toString();
    }
}
