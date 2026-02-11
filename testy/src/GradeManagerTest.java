import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GradeManagerTest {

    private GradeManager gm;

    @BeforeEach
    void init(){
        gm = new GradeManager();
    }

    @Test
    void shouldCalculateAverageOf4And6As5() {
        gm.addGrade(4);
        gm.addGrade(6);

        double average = gm.getAverage();
        System.out.println(average);
        Assertions.assertEquals(5.0, average, "test");
    }

    @Test
    void shouldNotAddInvalidGrade() {
        gm.addGrade(5);
        gm.addGrade(7); // niepoprawna ocena

        double average = gm.getAverage();
        System.out.println(average);
        // Średnia powinna nadal wynosić 5.0,
        // ponieważ 7 nie została dodana
        Assertions.assertEquals(5.0, average);
    }

    @Test
    void shouldReturnZeroForEmptyList() {


        double average = gm.getAverage();
        System.out.println(average);
        Assertions.assertEquals(0.0, average);
    }
}