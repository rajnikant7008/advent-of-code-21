import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day10Test {

    String input = """
        [({(<(())[]>[[{[]{<()<>>
        [(()[<>])]({[<{<<[]>>(
        {([(<{}[<>[]}>{[]{[(<()>
        (((({<>}<{<{<>}{[]{[]{}
        [[<[([]))<([[{}[[()]]]
        [{[{({}]{}}([{[{{{}}([]
        {<[[]]>}<{[{[{[]{()[[[]
        [<(<(<(<{}))><([]([]()
        <{([([[(<>()){}]>(<<{{
        <{([{{}}[<[[[<>{}]]]>[]]
        """;

    List<String> data;

    @BeforeEach
    void before() {
        data = Arrays.stream(input.split("\n")).toList();
    }

    @Test
    public void taskOne() {
        int actual = Day10.taskOne(data);
        assertEquals(26397, actual);
    }

    @Test
    public void taskTwo() {
        long actual = Day10.taskTwo(data);
        assertEquals(288957, actual);
    }
}

