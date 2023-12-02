package Day02;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day02Test {

    @Test
    void sumOfIdsOfPossibleGames() {
        assertEquals(8, Day02.sumOfIdsOfPossibleGames(List.of(
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        )));
    }

    @Test
    void sumOfIdsOfPossibleGamesRealData() throws IOException {
        assertEquals(2239, Day02.sumOfIdsOfPossibleGames(Files.readAllLines(Path.of("src/resources/day02.txt"))));
    }
}