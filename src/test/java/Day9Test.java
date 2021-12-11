import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day9Test {

    String input = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
        """;

    List<String> data;

    @BeforeEach
    void before(){
        data = Arrays.stream(input.split("\n")).toList();
    }

    @Test
    public void taskOne(){
        int actual = Day9.taskOne(data);
        assertEquals(15, actual);
    }

    @Test
    public void taskTwo(){
        int actual = Day9.taskTwo(data);
        assertEquals(1134, actual);
    }
}
