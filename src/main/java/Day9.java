import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

//--- Day 9: Smoke Basin --- <a href="https://adventofcode.com/2021/day/9">Day 9</a>
public class Day9 {

    record Indices(int x, int y) {
    }

    /**
     * These caves seem to be lava tubes. Parts are even still volcanically active; small hydrothermal vents release
     * smoke into the caves that slowly settles like rain.
     *
     * If you can model how the smoke flows through the caves, you might be able to avoid it and be that much safer.
     * The submarine generates a heightmap of the floor of the nearby caves for you (your puzzle input).
     *
     * Smoke flows to the lowest point of the area it's in. For example, consider the following heightmap:
     *
     * 2199943210
     * 3987894921
     * 9856789892
     * 8767896789
     * 9899965678
     *
     * Each number corresponds to the height of a particular location, where 9 is the highest and 0 is the lowest a
     * location can be.
     *
     * Your first goal is to find the low points - the locations that are lower than any of its adjacent locations.
     * Most locations have four adjacent locations (up, down, left, and right); locations on the edge or corner of
     * the map have three or two adjacent locations, respectively. (Diagonal locations do not count as adjacent.)
     *
     * In the above example, there are four low points, all highlighted: two are in the first row (a 1 and a 0), one
     * is in the third row (a 5), and one is in the bottom row (also a 5). All other locations on the heightmap have
     * some lower adjacent location, and so are not low points.
     *
     * The risk level of a low point is 1 plus its height. In the above example, the risk levels of the low points
     * are 2, 1, 6, and 6. The sum of the risk levels of all low points in the heightmap is therefore 15.
     */
    public static int taskOne(final List<String> input) {

        int m = input.size();
        int n = input.get(0).length();

        int[][] heightMap = formatInput(input, m, n);

        int riskLevel = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isLowPoint(heightMap, i, j))
                    riskLevel += heightMap[i][j] + 1;
            }
        }
        return riskLevel;
    }

    /**
     * Next, you need to find the largest basins so you know what areas are most important to avoid.
     *
     * A basin is all locations that eventually flow downward to a single low point. Therefore, every low point has a
     * basin, although some basins are very small. Locations of height 9 do not count as being in any basin, and all
     * other locations will always be part of exactly one basin.
     *
     * The size of a basin is the number of locations within the basin, including the low point. The example above
     * has four basins.
     *
     * The top-left basin, size 3
     *
     * The top-right basin, size 9
     *
     * The middle basin, size 14
     *
     * The bottom-right basin, size 9
     *
     * Find the three largest basins and multiply their sizes together. In the above example, this is 9 * 14 * 9 = 1134.
     */
    public static int taskTwo(final List<String> input) {
        int m = input.size();
        int n = input.get(0).length();

        int[][] heightMap = formatInput(input, m, n);

        List<Integer> basinSize = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isLowPoint(heightMap, i, j))
                    basinSize.add(basinSize(heightMap, i, j));
            }
        }
        return basinSize.stream().sorted(Collections.reverseOrder()).limit(3).reduce(1, (a, b) -> a * b);
    }

    private static int basinSize(int[][] heightMap, int x, int y) {
        Queue<Indices> indicesQueue = new ArrayDeque<>();
        boolean[][] visited = new boolean[heightMap.length][heightMap[0].length];
        for (boolean[] arr : visited) {
            Arrays.fill(arr, false);
        }
        Set<Indices> basin = new HashSet<>();
        indicesQueue.add(new Indices(x, y));
        while (!indicesQueue.isEmpty()) {
            Indices index = indicesQueue.remove();
            int i = index.x, j = index.y;
            visited[i][j] = true;
            if (i - 1 >= 0 && heightMap[i - 1][j] != 9 && !visited[i - 1][j])
                indicesQueue.add(new Indices(i - 1, j));
            if (i + 1 < heightMap.length && heightMap[i + 1][j] != 9 && !visited[i + 1][j])
                indicesQueue.add(new Indices(i + 1, j));
            if (j - 1 >= 0 && heightMap[i][j - 1] != 9 && !visited[i][j - 1])
                indicesQueue.add(new Indices(i, j - 1));
            if (j + 1 < heightMap[0].length && heightMap[i][j + 1] != 9 && !visited[i][j + 1])
                indicesQueue.add(new Indices(i, j + 1));

            basin.add(index);
        }
        return basin.size();
    }

    private static boolean isLowPoint(final int[][] heightMap, final int i, final int j) {
        int current = heightMap[i][j];
        if (i - 1 >= 0 && heightMap[i - 1][j] <= current)
            return false;
        if (i + 1 < heightMap.length && heightMap[i + 1][j] <= current)
            return false;
        if (j - 1 >= 0 && heightMap[i][j - 1] <= current)
            return false;
        if (j + 1 < heightMap[0].length && heightMap[i][j + 1] <= current)
            return false;
        return true;
    }

    private static int[][] formatInput(final List<String> input, final int m, final int n) {
        int[][] heightMap = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                heightMap[i][j] = Integer.parseInt(input.get(i).trim().split("")[j]);
            }
        }
        return heightMap;
    }
}
