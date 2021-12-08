import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day3Test {

    List<String> input;

    @BeforeEach
    void before(){
        String data = """
                00100
                11110
                10110
                10111
                10101
                01111
                00111
                11100
                10000
                11001
                00010
                01010
            """;
        input = new ArrayList<>(Arrays.stream(data.split("\n")).toList());
    }

    @Test
    void taskOne() {
        var actual = Day3.taskOne(input);
        assertEquals(198, actual);
    }

    @Test
    void taskTwo() {
        var actual = Day3.taskTwo(input);
        assertEquals(230, actual);
    }

}
