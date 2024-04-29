import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task1_a {

    public static void execute () {
        List<String> lines = FileReader();
        CalibrationValueRecoveryAsString(lines);
        List<Integer> intList = StringToInt(lines);
        int result = Adder(intList);
        System.out.println(result);
    }

    public static List<String> FileReader() {
        Path path = Paths.get("aoc_2024_day1_puzzle_input.txt");
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("An IO Exception occurred!");
            return new ArrayList<>();
        }
    }

    public static void CalibrationValueRecoveryAsString (List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String current = list.get(i);
            char[] characters = current.toCharArray();
            for (int j = 0; j < characters.length; j++) {
                if (characters[j] > 47 && characters[j] < 58) {
                    current = "" + characters[j];
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

    public static List<Integer> StringToInt (List<String> list) {
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            output.add(Integer.valueOf(list.get(i)));
        }
        return output;
    }

    public static int Adder (List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

}
