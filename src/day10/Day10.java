package day10;

import java.util.List;
import java.util.Set;

public class Day10 {
    public static int stepsToFarthestPipe(List<String> pipeMap) {
        int visitedPipes = 0;
        Position lastPosition = findStartPosition(pipeMap);
        Position currentPosition = findFirstStep(pipeMap, lastPosition);
        while (true) {
            visitedPipes++;
            char currentPipeShape = pipeMap.get(currentPosition.row).charAt(currentPosition.column);
            if (currentPipeShape == 'S') {
                return visitedPipes / 2;
            }
            Position newPosition = nextPosition(pipeMap, currentPipeShape, currentPosition, lastPosition);
            lastPosition = currentPosition;
            currentPosition = newPosition;
        }
    }

    private static Position findStartPosition(List<String> pipeMap) {
        for (int row = 0; row < pipeMap.size(); row++) {
            int startColumn = pipeMap.get(row).indexOf('S');
            if (startColumn != -1) {
                return new Position(row, startColumn);
            }
        }
        throw new IllegalStateException();
    }

    private static Position findFirstStep(List<String> pipeMap, Position startPosition) {
        if (startPosition.row > 0 && Set.of('|', 'F', '7').contains(pipeMap.get(startPosition.row - 1).charAt(startPosition.column))) {
            return new Position(startPosition.row - 1, startPosition.column);
        }
        if (startPosition.row < pipeMap.size() && Set.of('|', 'J', 'L').contains(pipeMap.get(startPosition.row + 1).charAt(startPosition.column))) {
            return new Position(startPosition.row + 1, startPosition.column);
        }
        if (startPosition.column > 0 && Set.of('-', 'F', 'L').contains(pipeMap.get(startPosition.row).charAt(startPosition.column - 1))) {
            return new Position(startPosition.row, startPosition.column - 1);
        }
        if (startPosition.column < pipeMap.get(0).length() && Set.of('-', 'J', '7').contains(pipeMap.get(startPosition.row).charAt(startPosition.column + 1))) {
            return new Position(startPosition.row, startPosition.column + 1);
        }
        throw new IllegalStateException();
    }

    private static Position nextPosition(List<String> pipeMap, char currentPipeShape, Position currentPosition, Position lastPosition) {
        Position north = new Position(currentPosition.row - 1, currentPosition.column);
        Position south = new Position(currentPosition.row + 1, currentPosition.column);
        Position west = new Position(currentPosition.row, currentPosition.column - 1);
        Position east = new Position(currentPosition.row, currentPosition.column + 1);
        switch (currentPipeShape) {
            case '|':
                return lastPosition.equals(north) ? south : north;
            case '-':
                return lastPosition.equals(west) ? east : west;
            case 'L':
                return lastPosition.equals(north) ? east : north;
            case 'J':
                return lastPosition.equals(north) ? west : north;
            case '7':
                return lastPosition.equals(south) ? west : south;
            case 'F':
                return lastPosition.equals(south) ? east : south;
        }
        throw new IllegalStateException();
    }

    private record Position(int row, int column) {
    }
}
