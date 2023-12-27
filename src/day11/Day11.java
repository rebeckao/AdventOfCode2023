package day11;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Day11 {
    public static long sumOfGalaxyDistances(List<String> galaxyMap, int expansionRate) {
        List<Integer> expandedRows = findDoubleRows(galaxyMap);
        List<Integer> expandedColumns = findDoubleColumns(galaxyMap);
        List<Galaxy> originalGalaxyPositions = findGalaxyPositions(galaxyMap);
        List<Galaxy> expandedGalaxyPositions = findExpandedGalaxyPositions(originalGalaxyPositions, expandedRows, expandedColumns, expansionRate);
        return sumGalaxyDistances(expandedGalaxyPositions);
    }

    private static List<Integer> findDoubleRows(List<String> galaxyMap) {
        List<Integer> doubleRows = new ArrayList<>();
        for (int row = 0; row < galaxyMap.size(); row++) {
            if (galaxyMap.get(row).indexOf('#') == -1) {
                doubleRows.add(row);
            }
        }
        return doubleRows;
    }

    private static List<Integer> findDoubleColumns(List<String> galaxyMap) {
        List<Integer> doubleColumns = new ArrayList<>();
        for (int colum = 0; colum < galaxyMap.getFirst().length(); colum++) {
            boolean empty = true;
            for (String string : galaxyMap) {
                if (string.charAt(colum) == '#') {
                    empty = false;
                    break;
                }
            }
            if (empty) {
                doubleColumns.add(colum);
            }
        }
        return doubleColumns;
    }

    private static List<Galaxy> findGalaxyPositions(List<String> galaxyMap) {
        List<Galaxy> galaxies = new ArrayList<>();
        for (int row = 0; row < galaxyMap.size(); row++) {
            String mapRow = galaxyMap.get(row);
            for (int colum = 0; colum < mapRow.length(); colum++) {
                if (mapRow.charAt(colum) == '#') {
                    galaxies.add(new Galaxy(row, colum));
                }
            }
        }
        return galaxies;
    }

    private static List<Galaxy> findExpandedGalaxyPositions(List<Galaxy> galaxies, List<Integer> doubleRows, List<Integer> doubleColumns, int expansionRate) {
        return galaxies.stream()
                .map(originalPosition -> expandCoordinate(doubleRows, doubleColumns, originalPosition, expansionRate))
                .toList();
    }

    private static Galaxy expandCoordinate(List<Integer> doubleRows, List<Integer> doubleColumns, Galaxy originalPosition, int expansionRate) {
        long newRow = originalPosition.row;
        long newColumn = originalPosition.column;
        for (int row : doubleRows) {
            if (row < originalPosition.row) {
                newRow += (expansionRate - 1);
            } else {
                break;
            }
        }
        for (int colum : doubleColumns) {
            if (colum < originalPosition.column) {
                newColumn += (expansionRate - 1);
            } else {
                break;
            }
        }
        return new Galaxy(newRow, newColumn);
    }

    private static long sumGalaxyDistances(List<Galaxy> galaxies) {
        long distances = 0;
        for (int firstGalaxyIndex = 0; firstGalaxyIndex < galaxies.size() - 1; firstGalaxyIndex++) {
            for (int secondGalaxyIndex = firstGalaxyIndex + 1; secondGalaxyIndex < galaxies.size(); secondGalaxyIndex++) {
                distances += distanceBetween(galaxies.get(firstGalaxyIndex), galaxies.get(secondGalaxyIndex));
            }
        }
        return distances;
    }

    private static long distanceBetween(Galaxy galaxy1, Galaxy galaxy2) {
        return abs(galaxy2.row - galaxy1.row) + abs(galaxy2.column - galaxy1.column);
    }

    record Galaxy(long row, long column) {

    }
}
