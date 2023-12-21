package day10;

import java.util.ArrayList;
import java.util.HashSet;
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
            Position newPosition = nextPosition(currentPipeShape, currentPosition, lastPosition);
            lastPosition = currentPosition;
            currentPosition = newPosition;
        }
    }

    public static int tilesInLoop(List<String> pipeMap) {
        Position startPosition = findStartPosition(pipeMap);
        Set<Position> loopPositions = findLoopPositions(pipeMap, startPosition);
        pipeMap = removeStartMarker(pipeMap, startPosition);
        int enclosedTiles = 0;
        for (int row = 0; row < pipeMap.size(); row++) {
            Enclosed enclosed = Enclosed.NO;
            String currentRow = pipeMap.get(row);
            for (int col = 0; col < currentRow.length(); col++) {
                if (loopPositions.contains(new Position(row, col))) {
                    char currentShape = currentRow.charAt(col);
                    if (enclosed == Enclosed.YES) {
                        if (currentShape == '|') {
                            enclosed = Enclosed.NO;
                        } else if (currentShape == 'L') {
                            enclosed = Enclosed.BELOW;
                        } else if (currentShape == 'F') {
                            enclosed = Enclosed.ABOVE;
                        }
                    } else if (enclosed == Enclosed.NO) {
                        if (currentShape == '|') {
                            enclosed = Enclosed.YES;
                        } else if (currentShape == 'L') {
                            enclosed = Enclosed.ABOVE;
                        } else if (currentShape == 'F') {
                            enclosed = Enclosed.BELOW;
                        }
                    } else if (enclosed == Enclosed.ABOVE) {
                        if (currentShape == 'J') {
                            enclosed = Enclosed.NO;
                        } else if (currentShape == '7') {
                            enclosed = Enclosed.YES;
                        }
                    } else if (enclosed == Enclosed.BELOW) {
                        if (currentShape == 'J') {
                            enclosed = Enclosed.YES;
                        } else if (currentShape == '7') {
                            enclosed = Enclosed.NO;
                        }
                    }
                } else if (enclosed == Enclosed.YES) {
                    enclosedTiles++;
                }
            }
        }
        return enclosedTiles;
    }

    private static List<String> removeStartMarker(List<String> pipeMap, Position startPosition) {
        Character startShape = findStartShape(pipeMap, startPosition);
        String fixedRow = pipeMap.get(startPosition.row).replace('S', startShape);
        List<String> newMap = new ArrayList<>(pipeMap);
        newMap.set(startPosition.row, fixedRow);
        return newMap;
    }

    private static Character findStartShape(List<String> pipeMap, Position startPosition) {
        boolean pointsNorth = pointsNorth(pipeMap, startPosition);
        boolean pointsSouth = pointsSouth(pipeMap, startPosition);
        boolean pointsWest = pointsWest(pipeMap, startPosition);
        if (pointsNorth) {
            if (pointsSouth) {
                return '|';
            } else if (pointsWest) {
                return 'J';
            } else {
                return 'L';
            }
        } else if (pointsSouth) {
            if (pointsWest) {
                return '7';
            } else {
                return 'F';
            }
        } else {
            return '-';
        }
    }

    private static boolean pointsEast(List<String> pipeMap, Position startPosition) {
        return startPosition.column < pipeMap.get(0).length() && Set.of('-', 'J', '7').contains(pipeMap.get(startPosition.row).charAt(startPosition.column + 1));
    }

    private static boolean pointsWest(List<String> pipeMap, Position startPosition) {
        return startPosition.column > 0 && Set.of('-', 'F', 'L').contains(pipeMap.get(startPosition.row).charAt(startPosition.column - 1));
    }

    private static boolean pointsSouth(List<String> pipeMap, Position startPosition) {
        return startPosition.row < pipeMap.size() && Set.of('|', 'J', 'L').contains(pipeMap.get(startPosition.row + 1).charAt(startPosition.column));
    }

    private static boolean pointsNorth(List<String> pipeMap, Position startPosition) {
        return startPosition.row > 0 && Set.of('|', 'F', '7').contains(pipeMap.get(startPosition.row - 1).charAt(startPosition.column));
    }

    private enum Enclosed {
        NO,
        ABOVE,
        BELOW,
        YES
    }

    private static Set<Position> findLoopPositions(List<String> pipeMap, Position startPosition) {
        Position lastPosition = startPosition;
        Set<Position> loopPositions = new HashSet<>();
        loopPositions.add(lastPosition);
        Position currentPosition = findFirstStep(pipeMap, lastPosition);
        while (true) {
            loopPositions.add(currentPosition);
            char currentPipeShape = pipeMap.get(currentPosition.row).charAt(currentPosition.column);
            if (currentPipeShape == 'S') {
                break;
            }
            Position newPosition = nextPosition(currentPipeShape, currentPosition, lastPosition);
            lastPosition = currentPosition;
            currentPosition = newPosition;
        }
        return loopPositions;
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
        if (pointsNorth(pipeMap, startPosition)) {
            return new Position(startPosition.row - 1, startPosition.column);
        }
        if (pointsSouth(pipeMap, startPosition)) {
            return new Position(startPosition.row + 1, startPosition.column);
        }
        if (pointsWest(pipeMap, startPosition)) {
            return new Position(startPosition.row, startPosition.column - 1);
        }
        if (pointsEast(pipeMap, startPosition)) {
            return new Position(startPosition.row, startPosition.column + 1);
        }
        throw new IllegalStateException();
    }

    private static Position nextPosition(char currentPipeShape, Position currentPosition, Position lastPosition) {
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
