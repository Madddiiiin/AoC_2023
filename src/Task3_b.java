import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task3_b {

    public static void execute() {
        List<char[]> characters = stringToChar(fileReader());
        List<int[]> stars = starFinder(characters);
        int result = 0;
        for (int[] current : stars) {
            List<Integer> numbers = twoNumbersInPeriphery(characters, current[0], current[1]);
            result += mult(numbers);
        }
        System.out.println(result);
    }

    public static List<String> fileReader() {
        Path path = Paths.get("aoc_2024_day3_puzzle_input.txt");
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("An IO Exception occurred!");
            return new ArrayList<>();
        }
    }

    public static List<char[]> stringToChar (List<String> input) {
        List<char[]> output = new ArrayList<>();
        for (String current : input) {
            char[] characters = current.toCharArray();
            output.add(characters);
        }
        return output;
    }

    public static boolean isDigit (char character) {
        return character > 47 && character < 58;
    }

    public static int numberDetector (List<char[]> characters, int row, int column) {
        String number = String.valueOf(characters.get(row)[column]);
        int columnSave = column;
        while (column > 0 && isDigit(characters.get(row)[column - 1])) {
            number = characters.get(row)[column - 1] + number;
            column--;
        }
        column = columnSave;
        while (column < characters.get(row).length - 1 && isDigit(characters.get(row)[column + 1])) {
            number = number + characters.get(row)[column + 1];
            column++;
        }
        return Integer.parseInt(number);
    }

    public static List<Integer> twoNumbersInPeriphery (List<char[]> characters, int row, int column) {
        List<Integer> numbers = new ArrayList<>();
        int numberCount = 0;

        if (row > 0 && isDigit(characters.get(row - 1)[column])) {
            numbers.add(numberDetector(characters, row - 1, column));
            numberCount++;
        } else {
            if (row > 0 && column > 0 && isDigit(characters.get(row - 1)[column - 1])) {
                numbers.add(numberDetector(characters, row - 1, column - 1));
                numberCount++;
            }
            if (row > 0 && column < characters.get(row - 1).length - 1 && isDigit(characters.get(row - 1)[column + 1])) {
                numbers.add(numberDetector(characters, row - 1, column + 1));
                numberCount++;
            }
        }

        if (column > 0 && isDigit(characters.get(row)[column - 1])) {
            numbers.add(numberDetector(characters, row, column - 1));
            numberCount++;
        }
        if (column < characters.get(row).length - 1 && isDigit(characters.get(row)[column + 1])) {
            numbers.add(numberDetector(characters, row, column + 1));
            numberCount++;
        }

        if (row < characters.size() - 1 && isDigit(characters.get(row + 1)[column])) {
            numbers.add(numberDetector(characters, row + 1, column));
            numberCount++;
        } else {
            if (row < characters.size() - 1 && column > 0 && isDigit(characters.get(row + 1)[column - 1])) {
                numbers.add(numberDetector(characters, row + 1, column - 1));
                numberCount++;
            }
            if (row < characters.size() - 1 && column < characters.get(row + 1).length - 1 && isDigit(characters.get(row + 1)[column + 1])) {
                numbers.add(numberDetector(characters, row + 1, column + 1));
                numberCount++;
            }
        }

        if (numberCount == 2) {
            return numbers;
        } else {
            List<Integer> empty = new ArrayList<>();
            return empty;
        }
    }

    public static List<int[]> starFinder (List<char[]> characters) {
        List<int[]> stars = new ArrayList<>();
        for (int i = 0; i < characters.size(); i++) {
            for (int j = 0; j < characters.get(i).length; j++) {
                if (characters.get(i)[j] == '*') {
                    stars.add(new int[]{i, j});
                }
            }
        }
        return stars;
    }

    public static int mult (List<Integer> numbers) {
        int result = 1;
        for (int current : numbers) {
            result *= current;
        }
        return result;
    }

}