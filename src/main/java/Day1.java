import java.util.List;

//--- Day 1: Sonar Sweep --- <a href="https://adventofcode.com/2021/day/1">Day 1</a>
public class Day1 {

    /**
     * As the submarine drops below the surface of the ocean, it automatically performs a sonar sweep of the nearby sea
     *      floor. On a small screen, the sonar sweep report (your puzzle input) appears: each line is a measurement
     *      of the sea floor depth as the sweep looks further and further away from the submarine.
     *
     *      For example, suppose you had the following report:
     *      199
     *      200
     *      208
     *      210
     *      200
     *      207
     *      240
     *      269
     *      260
     *      263
     *      This report indicates that, scanning outward from the submarine, the sonar sweep found depths of 199, 200,
     *      208, 210, and so on.
     *
     *      The first order of business is to figure out how quickly the depth increases, just so you know what you're
     *      dealing with.
     *
     *      To do this, count the number of times a depth measurement increases from the previous measurement. (There
     *      is no measurement before the first measurement.)
     *
     *      In this example, there are 7 measurements that are larger than the previous measurement.
     */
    public static int taskOne(List<String> input) {

        int increment = 0;
        if (input.size() < 2)
            return increment;

        int base = Integer.parseInt(input.get(0));

        for (int i = 1; i < input.size(); i++) {
            int current = Integer.parseInt(input.get(i));
            increment = current > base ? increment + 1 : increment;
            base = current;
        }

        return increment;
    }

    /**
     * Considering every single measurement isn't as useful as you expected: there's just too much noise in the data.
     *
     *         Instead, consider sums of a three-measurement sliding window. Again considering the above example:
     *
     *         199  A
     *         200  A B
     *         208  A B C
     *         210    B C D
     *         200  E   C D
     *         207  E F   D
     *         240  E F G
     *         269    F G H
     *         260      G H
     *         263        H
     *
     *         Start by comparing the first and second three-measurement windows. The measurements in the first
     *         window are
     *         marked A (199, 200, 208); their sum is 199 + 200 + 208 = 607. The second window is marked B (200, 208,
     *         210);
     *         its sum is 618. The sum of measurements in the second window is larger than the sum of the first, so this
     *         first comparison increased.
     *
     *         Your goal now is to count the number of times the sum of measurements in this sliding window increases
     *         from
     *         the previous sum. So, compare A with B, then compare B with C, then C with D, and so on. Stop when there
     *         aren't enough measurements left to create a new three-measurement sum.
     *
     *         In the above example, the sum of each three-measurement window is as follows:
     *
     *         A: 607 (N/A - no previous sum)
     *         B: 618 (increased)
     *         C: 618 (no change)
     *         D: 617 (decreased)
     *         E: 647 (increased)
     *         F: 716 (increased)
     *         G: 769 (increased)
     *         H: 792 (increased)
     *         In this example, there are 5 sums that are larger than the previous sum.
     *
     */
    public static int taskTwo(List<String> input) {
        int increment = 0;
        if (input.size() < 4)
            return increment;

        int b1 = Integer.parseInt(input.get(0));

        for (int i = 1; i < input.size() - 2; i++) {
            int c1 = Integer.parseInt(input.get(i));
            int c2 = Integer.parseInt(input.get(i + 1));
            int c3 = Integer.parseInt(input.get(i + 2));

            int base = b1 + c1 + c2;
            int current = c1 + c2 + c3;
            increment = current > base ? increment + 1 : increment;
            b1 = c1;
        }

        return increment;
    }
}
