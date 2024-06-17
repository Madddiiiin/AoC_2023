import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task1_a {

    public static void execute () {
        List<String> lines = fileReader();
        calibrationValueRecoveryAsString(lines);
        List<Integer> intList = stringToInt(lines);
        int result = adder(intList);
        System.out.println(result);
    }

    public static List<String> fileReader() {
        Path path = Paths.get("aoc_2023_day1_puzzle_input.txt");
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("An IO Exception occurred!");
            return new ArrayList<>();
        }
    }

    public static void calibrationValueRecoveryAsString (List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String current = list.get(i);
            char[] characters = current.toCharArray();
            for (char character : characters) {
                if (character > 47 && character < 58) {
                    current = "" + character;
                    break;
                }
            }
            for (int j = characters.length - 1; j >= 0; j--) {
                if (characters[j] > 47 && characters[j] < 58) {
                    current = current + characters[j];
                    break;
                }
            }
            list.set(i, current);
        }
    }

    public static List<Integer> stringToInt (List<String> list) {
        List<Integer> output = new ArrayList<>();
        for (String s : list) {
            output.add(Integer.valueOf(s));
        }
        return output;
    }

    public static int adder (List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

}