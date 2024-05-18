import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task1_b {

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

                if (characters[j] == 'o' && characters[j + 1] == 'n' && characters[j + 2] == 'e') {
                    current = "1";
                    break;
                } else if (characters[j] == 't' && characters[j + 1] == 'w' && characters[j + 2] == 'o') {
                    current = "2";
                    break;
                } else if (characters[j] == 't' && characters[j + 1] == 'h' && characters[j + 2] == 'r' && characters[j + 3] == 'e' && characters[j + 4] == 'e') {
                    current = "3";
                    break;
                } else if (characters[j] == 'f' && characters[j + 1] == 'o' && characters[j + 2] == 'u' && characters[j + 3] == 'r') {
                    current = "4";
                    break;
                } else if (characters[j] == 'f' && characters[j + 1] == 'i' && characters[j + 2] == 'v' && characters[j + 3] == 'e') {
                    current = "5";
                    break;
                } else if (characters[j] == 's' && characters[j + 1] == 'i' && characters[j + 2] == 'x') {
                    current = "6";
                    break;
                } else if (characters[j] == 's' && characters[j + 1] == 'e' && characters[j + 2] == 'v' && characters[j + 3] == 'e' && characters[j + 4] == 'n') {
                    current = "7";
                    break;
                } else if (characters[j] == 'e' && characters[j + 1] == 'i' && characters[j + 2] == 'g' && characters[j + 3] == 'h' && characters[j + 4] == 't') {
                    current = "8";
                    break;
                } else if (characters[j] == 'n' && characters[j + 1] == 'i' && characters[j + 2] == 'n' && characters[j + 3] == 'e') {
                    current = "9";
                    break;
                }
            }
            for (int j = characters.length - 1; j >= 0; j--) {
                if (characters[j] > 47 && characters[j] < 58) {
                    current = current + characters[j];
                    break;
                }

                if (characters[j] == 'e' && characters[j - 1] == 'n' && characters[j - 2] == 'o') {
                    current = current + "1";
                    break;
                } else if (characters[j] == 'o' && characters[j - 1] == 'w' && characters[j - 2] == 't') {
                    current = current + "2";
                    break;
                } else if (characters[j] == 'e' && characters[j - 1] == 'e' && characters[j - 2] == 'r' && characters[j - 3] == 'h' && characters[j - 4] == 't') {
                    current = current + "3";
                    break;
                } else if (characters[j] == 'r' && characters[j - 1] == 'u' && characters[j - 2] == 'o' && characters[j - 3] == 'f') {
                    current = current + "4";
                    break;
                } else if (characters[j] == 'e' && characters[j - 1] == 'v' && characters[j - 2] == 'i' && characters[j - 3] == 'f') {
                    current = current + "5";
                    break;
                } else if (characters[j] == 'x' && characters[j - 1] == 'i' && characters[j - 2] == 's') {
                    current = current + "6";
                    break;
                } else if (characters[j] == 'n' && characters[j - 1] == 'e' && characters[j - 2] == 'v' && characters[j - 3] == 'e' && characters[j - 4] == 's') {
                    current = current + "7";
                    break;
                } else if (characters[j] == 't' && characters[j - 1] == 'h' && characters[j - 2] == 'g' && characters[j - 3] == 'i' && characters[j - 4] == 'e') {
                    current = current + "8";
                    break;
                } else if (characters[j] == 'e' && characters[j - 1] == 'n' && characters[j - 2] == 'i' && characters[j - 3] == 'n') {
                    current = current + "9";
                    break;
                }
            }
            list.set(i, current);
        }
    }

    public static List<Integer> StringToInt (List<String> list) {
        List<Integer> output = new ArrayList<>();
        for (String s : list) {
            output.add(Integer.valueOf(s));
        }
        return output;
    }

    public static int Adder (List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

}
