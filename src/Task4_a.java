import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Task4_a {

    public static void execute () {
        List<List<String>> values = getValues(fileReader());
        List<List<String>> ownNumbers = getOwnNumbers(values);
        List<List<String>> winningNumbers = getWinningNumbers(values);
        List<List<Integer>> matchingNumbers = computeMatchingNumbers(winningNumbers, ownNumbers);
        int points = adder(pointsCalculator(matchingNumbers));
        System.out.println(points);
    }

    public static List<String> fileReader() {
        Path path = Paths.get("aoc_2023_day4_puzzle_input.txt");
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("An IO Exception occurred!");
            return new ArrayList<>();
        }
    }

    public static List<List<String>> getValues (List<String> game) {
        List<List<String>> valueList = new ArrayList<>();
        for (String current : game) {
            String values = current.substring(current.indexOf(':') + 2);
            valueList.add(Arrays.stream(values.split(" ")).toList());
        }
        return valueList;
    }

    public static List<List<String>> getWinningNumbers (List<List<String>> values) {
        List<List<String>> winningNumbers = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            List<String> numbers = new ArrayList<>();
            winningNumbers.add(numbers);
            int index = 0;
            while (true) {
                if (Objects.equals(values.get(i).get(index), "|")) {
                    break;
                } else {
                    winningNumbers.get(i).add(values.get(i).get(index));
                    index++;
                }
            }
        }
        return winningNumbers;
    }

    public static List<List<String>> getOwnNumbers (List<List<String>> values) {
        List<List<String>> ownNumbers = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            List<String> numbers = new ArrayList<>();
            ownNumbers.add(numbers);
            int index = 0;
            while (true) {
                if (Objects.equals(values.get(i).get(values.get(i).size() - index - 1), "|")) {
                    break;
                } else {
                    ownNumbers.get(i).add(values.get(i).get(values.get(i).size() - index - 1));
                    index++;
                }
            }
        }
        return ownNumbers;
    }

    public static List<List<Integer>> computeMatchingNumbers (List<List<String>> winningNumbers, List<List<String>> ownNumbers) {
        List<List<Integer>> matchingNumbers = new ArrayList<>();
        for (int i = 0; i < winningNumbers.size(); i++) {
            List<Integer> numbers = new ArrayList<>();
            for (String current : ownNumbers.get(i)) {
                if (!(current == null) && !(current.isEmpty()) && winningNumbers.get(i).contains(current)) {
                    numbers.add(Integer.valueOf(current));
                }
            }
            matchingNumbers.add(numbers);
        }
        return matchingNumbers;
    }

    public static List<Integer> pointsCalculator (List<List<Integer>> matchingNumbers) {
        List<Integer> points = new ArrayList<>();
        for (List<Integer> row : matchingNumbers) {
            int rowPoints = 0;
            if (row.size() == 1) {
                rowPoints = 1;
            } else if (row.size() > 1) {
                rowPoints = (int) Math.pow(2, row.size() - 1);
            }
            points.add(rowPoints);
        }
        return points;
    }

    public static int adder (List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

}