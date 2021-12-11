import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

//--- Day 10: Syntax Scoring --- <a href="https://adventofcode.com/2021/day/10">Day 10</a>
public class Day10 {

    private static final Map<Character, Character> CLOSE_OPEN_MAP = Map.of(
        ')', '(',
        ']', '[',
        '}', '{',
        '>', '<'
    );

    private static final Map<Character, Character> OPEN_CLOSE_MAP =
        CLOSE_OPEN_MAP.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    private static final Map<Character, Integer> ILLEGAL_CHAR_POINTS = Map.of(
        ')', 3,
        ']', 57,
        '}', 1197,
        '>', 25137
    );

    private static final Map<Character, Integer> AUTO_COMPLETE_CHAR_POINTS = Map.of(
        ')', 1,
        ']', 2,
        '}', 3,
        '>', 4
    );

    /**
     *The navigation subsystem syntax is made of several lines containing chunks. There are one or more chunks on
     * each line, and chunks contain zero or more other chunks. Adjacent chunks are not separated by any delimiter;
     * if one chunk stops, the next chunk (if any) can immediately start. Every chunk must open and close with one of
     * four legal pairs of matching characters:
     *
     *     If a chunk opens with (, it must close with ).
     *     If a chunk opens with [, it must close with ].
     *     If a chunk opens with {, it must close with }.
     *     If a chunk opens with <, it must close with >.
     *
     * So, () is a legal chunk that contains no other chunks, as is []. More complex but valid chunks include ([]), {
     * ()()()}, <([{}])>, [<>({}){}[([])<>]], and even (((((((((()))))))))).
     *
     * Some lines are incomplete, but others are corrupted. Find and discard the corrupted lines first.
     *
     * A corrupted line is one where a chunk closes with the wrong character - that is, where the characters it opens
     * and closes with do not form one of the four legal pairs listed above.
     *
     * Examples of corrupted chunks include (], {()()()>, (((()))}, and <([]){()}[{}]). Such a chunk can appear
     * anywhere within a line, and its presence causes the whole line to be considered corrupted.
     *
     * For example, consider the following navigation subsystem:
     *
     * [({(<(())[]>[[{[]{<()<>>
     * [(()[<>])]({[<{<<[]>>(
     * {([(<{}[<>[]}>{[]{[(<()>
     * (((({<>}<{<{<>}{[]{[]{}
     * [[<[([]))<([[{}[[()]]]
     * [{[{({}]{}}([{[{{{}}([]
     * {<[[]]>}<{[{[{[]{()[[[]
     * [<(<(<(<{}))><([]([]()
     * <{([([[(<>()){}]>(<<{{
     * <{([{{}}[<[[[<>{}]]]>[]]
     *
     * Some of the lines aren't corrupted, just incomplete; you can ignore these lines for now. The remaining five
     * lines are corrupted:
     *
     *     {([(<{}[<>[]}>{[]{[(<()> - Expected ], but found } instead.
     *     [[<[([]))<([[{}[[()]]] - Expected ], but found ) instead.
     *     [{[{({}]{}}([{[{{{}}([] - Expected ), but found ] instead.
     *     [<(<(<(<{}))><([]([]() - Expected >, but found ) instead.
     *     <{([([[(<>()){}]>(<<{{ - Expected ], but found > instead.
     *
     * Stop at the first incorrect closing character on each corrupted line.
     *
     * Did you know that syntax checkers actually have contests to see who can get the high score for syntax errors
     * in a file? It's true! To calculate the syntax error score for a line, take the first illegal character on the
     * line and look it up in the following table:
     *
     *     ): 3 points.
     *     ]: 57 points.
     *     }: 1197 points.
     *     >: 25137 points.
     *
     * In the above example, an illegal ) was found twice (2*3 = 6 points), an illegal ] was found once (57 points),
     * an illegal } was found once (1197 points), and an illegal > was found once (25137 points). So, the total
     * syntax error score for this file is 6+57+1197+25137 = 26397 points!
     */
    public static int taskOne(final List<String> input) {
        List<Character> illegalChars = new ArrayList<>();

        for (String line : input) {
            findIllegalChar(line.trim()).ifPresent(illegalChars::add);
        }
        return illegalChars.stream().map(ILLEGAL_CHAR_POINTS::get).mapToInt(i -> i).sum();
    }

    private static Optional<Character> findIllegalChar(String input) {

        Stack<Character> stack = new Stack<>();

        for (char ch : input.toCharArray()) {
            if (OPEN_CLOSE_MAP.containsKey(ch))
                stack.push(ch);
            else {
                if (stack.peek().equals(CLOSE_OPEN_MAP.get(ch))) {
                    stack.pop();
                } else {
                    return Optional.of(ch);
                }
            }
        }
        return Optional.empty();
    }

    /**
     *Now, discard the corrupted lines. The remaining lines are incomplete.
     *
     * Incomplete lines don't have any incorrect characters - instead, they're missing some closing characters at the
     * end of the line. To repair the navigation subsystem, you just need to figure out the sequence of closing
     * characters that complete all open chunks in the line.
     *
     * You can only use closing characters (), ], }, or >), and you must add them in the correct order so that only
     * legal pairs are formed and all chunks end up closed.
     *
     * In the example above, there are five incomplete lines:
     *
     *     [({(<(())[]>[[{[]{<()<>> - Complete by adding }}]])})].
     *     [(()[<>])]({[<{<<[]>>( - Complete by adding )}>]}).
     *     (((({<>}<{<{<>}{[]{[]{} - Complete by adding }}>}>)))).
     *     {<[[]]>}<{[{[{[]{()[[[] - Complete by adding ]]}}]}]}>.
     *     <{([{{}}[<[[[<>{}]]]>[]] - Complete by adding ])}>.
     *
     * Did you know that autocomplete tools also have contests? It's true! The score is determined by considering the
     * completion string character-by-character. Start with a total score of 0. Then, for each character, multiply
     * the total score by 5 and then increase the total score by the point value given for the character in the
     * following table:
     *
     *     ): 1 point.
     *     ]: 2 points.
     *     }: 3 points.
     *     >: 4 points.
     *
     * So, the last completion string above - ])}> - would be scored as follows:
     *
     *     Start with a total score of 0.
     *     Multiply the total score by 5 to get 0, then add the value of ] (2) to get a new total score of 2.
     *     Multiply the total score by 5 to get 10, then add the value of ) (1) to get a new total score of 11.
     *     Multiply the total score by 5 to get 55, then add the value of } (3) to get a new total score of 58.
     *     Multiply the total score by 5 to get 290, then add the value of > (4) to get a new total score of 294.
     *
     * The five lines' completion strings have total scores as follows:
     *
     *     }}]])})] - 288957 total points.
     *     )}>]}) - 5566 total points.
     *     }}>}>)))) - 1480781 total points.
     *     ]]}}]}]}> - 995444 total points.
     *     ])}> - 294 total points.
     *
     * Autocomplete tools are an odd bunch: the winner is found by sorting all of the scores and then taking the
     * middle score. (There will always be an odd number of scores to consider.) In this example, the middle score is
     * 288957 because there are the same number of scores smaller and larger than it.
     */
    public static long taskTwo(final List<String> input) {

        List<Long> scores = new ArrayList<>();
        input.forEach(line -> autoComplete(line.trim()).ifPresent(
            l -> scores.add(
                l.stream()
                    .map(AUTO_COMPLETE_CHAR_POINTS::get)
                    .mapToLong(Long::valueOf)
                    .reduce(0, (a, b) -> a * 5 + b))
        ));
        Collections.sort(scores);
        return scores.get(scores.size() / 2);
    }

    private static Optional<List<Character>> autoComplete(String input) {
        Stack<Character> stack = new Stack<>();

        for (char ch : input.toCharArray()) {
            if (OPEN_CLOSE_MAP.containsKey(ch))
                stack.push(ch);
            else {
                if (stack.peek().equals(CLOSE_OPEN_MAP.get(ch))) {
                    stack.pop();
                } else {
                    return Optional.empty();
                }
            }
        }
        List<Character> chars = new LinkedList<>();
        while (!stack.isEmpty()) {
            chars.add(OPEN_CLOSE_MAP.get(stack.pop()));
        }

        return Optional.of(chars);
    }
}
