import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

        Path path1 = getFilePath("day1.txt");
        System.out.println(Day1.taskOne(getLines(path1)));
        System.out.println(Day1.taskTwo(getLines(path1)));

        Path path2 = getFilePath("day2.txt");
        System.out.println(Day2.taskOne(getLines(path2)));
        System.out.println(Day2.taskTwo(getLines(path2)));

        Path path3 = getFilePath("day3.txt");
        System.out.println(Day3.taskOne(getLines(path3)));
        System.out.println(Day3.taskTwo(getLines(path3)));

        Path path9 = getFilePath("day9.txt");
        System.out.println(Day9.taskOne(getLines(path9)));
        System.out.println(Day9.taskTwo(getLines(path9)));

        Path path10 = getFilePath("day10.txt");
        System.out.println(Day10.taskOne(getLines(path10)));
        System.out.println(Day10.taskTwo(getLines(path10)));

    }

    public static Path getFilePath(String filename) throws URISyntaxException {
        return Paths.get(Main.class.getClassLoader().getResource(filename).toURI());
    }

    public static List<String> getLines(Path path) throws IOException {
        return Files.lines(path).toList();
    }
}
