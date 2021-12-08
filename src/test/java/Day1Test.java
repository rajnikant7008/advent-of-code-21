import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day1Test {

    List<String> input;

    @BeforeEach
    void before() {
        String data = """
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263
            """;

        input = new ArrayList<>(Arrays.stream(data.split("\n")).toList());
    }

    @Test
    void taskOne() {
        var actual = Day1.taskOne(input);
        assertEquals(7, actual);
    }

    @Test
    void taskTwo() {
        var actual = Day1.taskTwo(input);
        assertEquals(5, actual);
    }

}
