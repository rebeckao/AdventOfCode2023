package day06;

import java.util.Arrays;
import java.util.List;

public class Day06 {
    static int multipliedWaysToBeatRecord(List<String> races) {
        int[] raceTimes = Arrays.stream(races.get(0).substring(5).trim().split("\\s+")).mapToInt(Integer::valueOf).toArray();
        int[] recordDistances = Arrays.stream(races.get(1).substring(9).trim().split("\\s+")).mapToInt(Integer::valueOf).toArray();
        int multipliedNumberOfWays = 1;
        for (int i = 0; i < raceTimes.length; i++) {
            multipliedNumberOfWays *= numberOfWaysToBeatRecord(raceTimes[i], recordDistances[i]);
        }
        return multipliedNumberOfWays;
    }

    private static int numberOfWaysToBeatRecord(int raceTime, int recordDistance) {
        int numberOfWays = 0;
        for (int buttonTime = 1; buttonTime < raceTime; buttonTime++) {
            if (buttonTime * (raceTime - buttonTime) > recordDistance) {
                numberOfWays++;
            }
        }
        return numberOfWays;
    }
}
