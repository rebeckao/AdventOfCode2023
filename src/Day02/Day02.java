package Day02;

import java.util.*;

public class Day02 {
    static int sumOfIdsOfPossibleGames(List<String> games) {
        Map<String, Integer> maxQuantities = Map.of("red", 12, "green", 13, "blue", 14);
        return games.stream()
                .map(it -> parseGame(it))
                .filter(it -> isValidGame(it, maxQuantities))
                .mapToInt(it -> it.id())
                .sum();
    }

    private static boolean isValidGame(Game game, Map<String, Integer> maxQuantities) {
        for (Map<String, Integer> draw : game.draws()) {
            for (String cubeColour : maxQuantities.keySet()) {
                if (draw.containsKey(cubeColour) && draw.get(cubeColour) > maxQuantities.get(cubeColour)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Game parseGame(String gameString) {
        String[] gameStringParts = gameString.split(":");
        int gameId = Integer.parseInt(gameStringParts[0].substring(5));
        Set<Map<String, Integer>> draws = new HashSet<>();
        String[] drawParts = gameStringParts[1].split(";");
        for (String drawPart : drawParts) {
            Map<String, Integer> draw = new HashMap<>();
            draws.add(draw);
            String[] cubes = drawPart.split(",");
            for (String cube : cubes) {
                String[] cubeParts = cube.trim().split(" ");
                draw.put(cubeParts[1], Integer.parseInt(cubeParts[0]));
            }
        }
        return new Game(gameId, draws);
    }
}

record Game(int id, Set<Map<String, Integer>> draws) {
}
