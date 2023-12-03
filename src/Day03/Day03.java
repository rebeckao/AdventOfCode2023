package Day03;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Day03 {
    static int sumOfPartNumbers(List<String> engineMap) {
        List<String> partNumbers = new ArrayList<>();
        for (int mapRowIndex = 0; mapRowIndex < engineMap.size(); mapRowIndex++) {
            String line = engineMap.get(mapRowIndex);
            int mapRowIndexAsFinal = mapRowIndex;
            partNumbers.addAll(
                    Pattern.compile("(\\d+)")
                            .matcher(line)
                            .results()
                            .filter(it -> adjacentToSymbol(it.group(), it.start(), engineMap, mapRowIndexAsFinal))
                            .map(MatchResult::group)
                            .toList()
            );
        }
        return partNumbers.stream().mapToInt(Integer::valueOf).sum();
    }

    private static boolean adjacentToSymbol(String number, int startColumn, List<String> engineMap, int rowIndex) {
        int minRow = max(0, rowIndex - 1);
        int maxRowExclusive = min(engineMap.size(), rowIndex + 2);
        int minCol = max(0, startColumn - 1);
        int maxColExclusive = min(engineMap.get(rowIndex).length(), startColumn + number.length() + 1);
        for (int row = minRow; row < maxRowExclusive; row++) {
            for (int col = minCol; col < maxColExclusive; col++) {
                String spaceToCheck = String.valueOf(engineMap.get(row).charAt(col));
                if (!spaceToCheck.matches("[\\d.]")) {
                    return true;
                }
            }
        }
        return false;
    }
}
