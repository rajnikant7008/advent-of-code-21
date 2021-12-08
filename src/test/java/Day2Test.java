import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day2Test {
    List<String> input;

    @BeforeEach
    void before() {
        String data = """
               forward 5
               down 5
               forward 8
               up 3
               down 8
               forward 2
            """;

        input = new ArrayList<>(Arrays.stream(data.split("\n")).toList());
    }

    @Test
    void taskOne() {
        var actual = Day2.taskOne(input);
        assertEquals(150, actual);
    }

    @Test
    void taskTwo() {
        var actual = Day2.taskTwo(input);
        assertEquals(900, actual);
    }
}
