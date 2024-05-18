import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task4_b {

    public static void execute () {
        List<List<String>> values = Task4_a.getValues(fileReader());
        List<List<String>> ownNumbers = Task4_a.getOwnNumbers(values);
        List<List<String>> winningNumbers = Task4_a.getWinningNumbers(values);
        List<List<Integer>> matchingNumbers = Task4_a.computeMatchingNumbers(winningNumbers, ownNumbers);
        List<Integer> copiesAmount = computeCopiesAmount(getMatchingNumbersAmount(matchingNumbers));
        System.out.println(adder(copiesAmount));
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

    public static List<Integer> getMatchingNumbersAmount (List<List<Integer>> matchingNumbers) {
        List<Integer> points = new ArrayList<>();
        for (List<Integer> row : matchingNumbers) {
            points.add(row.size());
        }
        return points;
    }

    public static List<Integer> computeCopiesAmount (List<Integer> matchingNumbersAmount) {
        List<Integer> copiesAmount = new ArrayList<>();
        for (int i = 0; i < matchingNumbersAmount.size(); i++) {
            copiesAmount.add(i, 1);
        }
        for (int i = 0; i < matchingNumbersAmount.size(); i++) {
            for (int j = 1; j <= matchingNumbersAmount.get(i) && i + j < matchingNumbersAmount.size(); j++) {
                copiesAmount.set(i + j, copiesAmount.get(i + j) + copiesAmount.get(i));
            }
        }
        return copiesAmount;
    }

    public static int adder (List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

}