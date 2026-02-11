import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GradeManager {
    private List<Integer> grades = new ArrayList<>();

    public void addGrade(int grade) {
        if (grade >= 1 && grade <= 6) grades.add(grade);
    }

    public double getAverage() {
        return grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public static void main(String[] args) {

    }
}
