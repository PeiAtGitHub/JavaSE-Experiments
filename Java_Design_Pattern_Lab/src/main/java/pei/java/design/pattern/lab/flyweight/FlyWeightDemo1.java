package pei.java.design.pattern.lab.flyweight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * the number of flyweight objects are supposed to be very few,
 * reused with different configurations
 * @author pei
 *
 */
public class FlyWeightDemo1 {
    
    public static void main(String args[]) {
        final int SIZE = 3;
        String names[] = { "Name1", "Name2", "Name3" };// 3 students
        int ids[] = { 1001, 1002, 1003 };//3 IDs
        int scores[] = { 70, 80, 90 };// 3 scores
        
        Student student = Student.builder().averageScore(80d).build();

        for (int i = 0; i < SIZE; i++) {
            student.setName(names[i]);
            student.setId(ids[i]);
            student.setScore(scores[i]);
            System.out.format("Name: %s; Standing: %s%n", student.getName(), 
                    Math.round(student.getStanding()));
        }
    }
}

@Getter @Setter 
@Builder @AllArgsConstructor
class Student {
    int id;
    String name;
    int score;

    double averageScore; //common data

    /**
     * the percentage by which the student's score differs from the average
     */
    public double getStanding() {
        return (((double) score) / averageScore - 1.0) * 100.0;
    }
}

