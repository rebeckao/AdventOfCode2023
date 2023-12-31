package day08;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day08 {

    private static final Pattern MAP_PATTERN = Pattern.compile("(.+) = \\((.+), (.+)\\)");

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

    static long ghostStepsToEnd(List<String> maps) {
        String directions = maps.get(0);
        Map<String, DesertNode> cyclesToNextEnd = calculateCyclesToEndNodes(maps, directions);
        // This only works because the data is the way it is. It's not a general solution.
        long leastCommonMultiple = cyclesToNextEnd.entrySet().stream()
                .filter(it -> endsWith(it.getKey(), 'A'))
                .map(it -> it.getValue().cyclesToEnd.orElseThrow())
                .map(BigInteger::valueOf)
                .reduce(Day08::leastCommonMultiple)
                .orElseThrow()
                .longValue();
        return leastCommonMultiple * directions.length();
    }

    private static BigInteger leastCommonMultiple(BigInteger a, BigInteger b) {
        BigInteger gcd = a.gcd(b);
        BigInteger absProduct = a.multiply(b).abs();
        return absProduct.divide(gcd);
    }

    private static Map<String, DesertNode> calculateCyclesToEndNodes(List<String> maps, String directions) {
        Map<String, DesertNode> fullCycleNodes = parseMapAndCalculateFullCycleNodes(maps, directions);
        Set<String> unfinishedNodes = fullCycleNodes.keySet().stream()
                .filter(it -> !it.equals(fullCycleNodes.get(it).target))
                .filter(it -> !endsWith(fullCycleNodes.get(it).target, 'Z'))
                .collect(Collectors.toSet());
        Set<String> deadEndNodes = fullCycleNodes.keySet().stream()
                .filter(it -> it.equals(fullCycleNodes.get(it).target))
                .collect(Collectors.toSet());
        while (!unfinishedNodes.isEmpty()) {
            Set<String> nextRoundUnfinished = new HashSet<>();
            for (String unfinishedNode : unfinishedNodes) {
                DesertNode thisNode = fullCycleNodes.get(unfinishedNode);
                DesertNode targetNode = fullCycleNodes.get(thisNode.target);
                Optional<Integer> cyclesToEnd = targetNode.cyclesToEnd;
                if (cyclesToEnd.isPresent()) {
                    fullCycleNodes.put(unfinishedNode, new DesertNode(targetNode.target, Optional.of(cyclesToEnd.get() + 1)));
                } else if (deadEndNodes.contains(thisNode.target)) {
                    deadEndNodes.add(unfinishedNode);
                } else {
                    nextRoundUnfinished.add(unfinishedNode);
                }
            }
            unfinishedNodes = nextRoundUnfinished;
        }
        return fullCycleNodes;
    }

    private static Map<String, DesertNode> parseMapAndCalculateFullCycleNodes(List<String> maps, String directions) {
        Map<String, String> leftMap = new HashMap<>();
        Map<String, String> rightMap = new HashMap<>();
        for (int i = 2; i < maps.size(); i++) {
            Matcher matcher = MAP_PATTERN.matcher(maps.get(i));
            if (matcher.find()) {
                String node = matcher.group(1);
                leftMap.put(node, matcher.group(2));
                rightMap.put(node, matcher.group(3));
            }
        }
        return fullCycleNodes(leftMap, directions, rightMap);
    }

    private static Map<String, DesertNode> fullCycleNodes(Map<String, String> leftMap, String directions, Map<String, String> rightMap) {
        List<String> startNodes = leftMap.keySet().stream().toList();
        Map<String, DesertNode> nodes = new HashMap<>();
        for (String startNode : startNodes) {
            String currentLocation = startNode;
            for (int step = 0; step < directions.length(); step++) {
                char currentDirection = directions.charAt(step);
                String newNode;
                if (currentDirection == 'L') {
                    newNode = leftMap.get(currentLocation);
                } else {
                    newNode = rightMap.get(currentLocation);
                }
                currentLocation = newNode;
            }
            nodes.put(startNode, new DesertNode(
                    currentLocation,
                    endsWith(currentLocation, 'Z') ? Optional.of(1) : Optional.empty()
            ));
        }
        return nodes;
    }

    private static boolean endsWith(String string, char character) {
        return string.charAt(string.length() - 1) == character;
    }

    private record DesertNode(String target, Optional<Integer> cyclesToEnd) {
    }
}
