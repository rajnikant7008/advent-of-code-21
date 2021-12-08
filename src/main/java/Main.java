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

    }

    public static Path getFilePath(String filename) throws URISyntaxException {
        return Paths.get(Main.class.getClassLoader().getResource(filename).toURI());
    }

    public static List<String> getLines(Path path) throws IOException {
        return Files.lines(path).toList();
    }
}
