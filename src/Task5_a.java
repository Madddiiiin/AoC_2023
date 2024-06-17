import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Task5_a {

    public static void execute() {
        List<String> input = fileReader();
        long minLocation = locationFinder(getAllMaps(input), input);
        System.out.println(minLocation);
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
        List<String> seedList = new ArrayList<>();
        String firstRow = input.getFirst();
        String seeds = firstRow.substring(firstRow.indexOf(':') + 2);
        return (Arrays.stream(seeds.split(" ")).toList());
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
    public static List<List<Long>> mapper (List<String> input, int mapType) {
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

    //public static long

    /*
    Map<source, destination>
     */
    public static Map<Long, Long> buildMap (List<List<Long>> input) {
        Map<Long, Long> map = new HashMap<>();
        for (List<Long> row : input) {
            long destinationRangeStart = row.getFirst();
            long sourceRangeStart = row.get(1);
            long rangeLength = row.getLast();
            for (int i = 0; i < rangeLength; i++) {
                map.put(sourceRangeStart + i, destinationRangeStart + i);
            }
        }
        return map;
    }

    public static List<Map<Long, Long>> getAllMaps (List<String> input) {
        List<Map<Long, Long>> maps = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            maps.add(buildMap(mapper(input, i)));
        }
        return maps;
    }

    public static long locationFinder (List<Map<Long, Long>> maps, List<String> inputData) {
        List<Long> seeds = stringToLong(getSeeds(inputData));
        List<Long> destinations = new ArrayList<>();
        for (Long seed : seeds) {
            Long currentValue = seed;
            for (Map<Long, Long> currentMap : maps) {
                if (currentMap.containsKey(currentValue)) {
                    currentValue = currentMap.get(currentValue);
                }
            }
            destinations.add(currentValue);
        }
        return destinations.stream().min(Long::compare).get();
    }

}