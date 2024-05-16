import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task3_a {

    public static void execute () {
        List<String> list = valueSelection(stringToChar(fileReader()));
        for (String current : list)  {
            System.out.println(current);
        }
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

    public static List<String> valueSelection (List<char[]> characters) {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < characters.size(); i++) {
            for (int j = 0; j < characters.get(i).length; j++) {
                if (characters.get(i)[j] > 47 && characters.get(i)[j] < 58) {
                    if (characters.get(i - 1)[j] == 50) {
                        values.add(String.valueOf(characters.get(i)[j]));
                    }
                }
            }
        }
        return values;
    }

}
