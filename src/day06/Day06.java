package day06;

import java.util.Arrays;
import java.util.List;

public class Day06 {
    static long multipliedWaysToBeatRecord(List<String> races) {
        int[] raceTimes = Arrays.stream(races.get(0).substring(5).trim().split("\\s+")).mapToInt(Integer::valueOf).toArray();
        int[] recordDistances = Arrays.stream(races.get(1).substring(9).trim().split("\\s+")).mapToInt(Integer::valueOf).toArray();
        long multipliedNumberOfWays = 1;
        for (int i = 0; i < raceTimes.length; i++) {
            multipliedNumberOfWays *= numberOfWaysToBeatRecord(raceTimes[i], recordDistances[i]);
        }
        return multipliedNumberOfWays;
    }

    static long waysToBeatRecordForSingleRace(List<String> races) {
        long raceTime = Long.parseLong(races.get(0).substring(5).trim().replace(" ", ""));
        long recordDistance = Long.parseLong(races.get(1).substring(9).trim().replace(" ", ""));
        return numberOfWaysToBeatRecord(raceTime, recordDistance);
    }

    private static long numberOfWaysToBeatRecord(long raceTime, long recordDistance) {
        long numberOfWays = 0;
        for (long buttonTime = 1; buttonTime < raceTime; buttonTime++) {
            if (buttonTime * (raceTime - buttonTime) > recordDistance) {
                numberOfWays++;
            }
        }
        return numberOfWays;
    }
}
