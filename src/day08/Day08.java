package day08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day08 {

    private static final Pattern MAP_PATTERN = Pattern.compile("([A-Z]+) = \\(([A-Z]+), ([A-Z]+)\\)");

    static int stepsToEnd(List<String> maps) {
        String directions = maps.get(0);
        Map<String, String> leftMap = new HashMap<>();
        Map<String, String> rightMap = new HashMap<>();
        for (int i = 2; i < maps.size(); i++) {
            Matcher matcher = MAP_PATTERN.matcher(maps.get(i));
            if (matcher.find()) {
                leftMap.put(matcher.group(1), matcher.group(2));
                rightMap.put(matcher.group(1), matcher.group(3));
            }
        }
        String currentLocation = "AAA";
        int steps = 0;
        while (!currentLocation.equals("ZZZ")) {
            char currentDirection = directions.charAt(steps % directions.length());
            if (currentDirection == 'L') {
                currentLocation = leftMap.get(currentLocation);
            } else {
                currentLocation = rightMap.get(currentLocation);
            }
            steps++;
        }
        return steps;
    }
}
