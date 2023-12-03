package common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Common {
    public static List<String> realData(int day) {
        try {
            return Files.readAllLines(Path.of(String.format("src/resources/day%02d.txt", day)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
