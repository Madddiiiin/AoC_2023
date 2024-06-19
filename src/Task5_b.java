import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Task5_b {

    public static void execute() {
        List<String> input = fileReader();
        long solution = flippedApproach(input);
        System.out.println(solution);
    }

    public static List<String> fileReader() {
        Path path = Paths.get("aoc_2023_day5_puzzle_input.txt");
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("An IO Exception occurred!");
            return new ArrayList<>();
        }
    }

    public static List<String> getSeeds (List<String> input) {
        String firstRow = input.getFirst();
        String seeds = firstRow.substring(firstRow.indexOf(':') + 2);
        return Arrays.stream(seeds.split(" ")).toList();
    }

    public static List<Long> stringToLong (List<String> list) {
        List<Long> output = new ArrayList<>();
        for (String s : list) {
            output.add(Long.valueOf(s));
        }
        return output;
    }

    /*
    mapType: 1 = seedToSoil, 2 = soilToFertilizer, 3 = fertilizerToWater, 4 = waterToLight, 5 = lightToTemperature, 6 = temperatureToHumidity, 7 = humidityToLocation
     */
    public static List<List<Long>> seperateMaps (List<String> input, int mapType) {
        List<List<String>> MapSegmentAsString = new ArrayList<>();
        int emptyLines = 0;
        for (int i = 0; i < input.size(); i++) {
            if (Objects.equals(input.get(i), "")) {
                emptyLines++;
                i += 2;
            }
            if (emptyLines == mapType) {
                MapSegmentAsString.add(Arrays.stream(input.get(i).split(" ")).toList());
            }
        }

        List<List<Long>> MapSegmentAsInt = new ArrayList<>();
        for (List<String> current : MapSegmentAsString) {
            MapSegmentAsInt.add(stringToLong(current));
        }
        return MapSegmentAsInt;
    }

    public static long mapper (List<List<Long>> map, long source) {
        long correspondence = source;
        for (List<Long> row : map) {
            if (source >= row.get(0) && source < row.get(0) + row.get(2)) {
                correspondence = row.get(1) + (source - row.get(0));
            }
        }
        return correspondence;
    }

    public static long flippedApproach (List<String> inputData) {
        List<Long> sources = stringToLong(getSeeds(inputData));
        long iterator = 0;
        while (true) {
            System.out.println(iterator);
            if (isSeed(locationToSeed(inputData, iterator), sources)) {
                return iterator;
            }
            iterator++;
        }
    }

    public static long locationToSeed (List<String> inputData, long location) {
        long currentSource = location;
        long seed;
        List<List<Long>> map = seperateMaps(inputData, 7);
        seed = mapper(map, currentSource);
        for (int i = 6; i > 0; i--) {
            currentSource = seed;
            map = seperateMaps(inputData, i);
            seed = mapper(map, currentSource);
        }
        return seed;
    }

    public static boolean isSeed (long candidate, List<Long> sources) {
        for (int i = 0; i < 20; i += 2) {
            if (candidate >= sources.get(i) && candidate < sources.get(i) + sources.get(i + 1)) {
                return true;
            }
        }
        return false;
    }

}