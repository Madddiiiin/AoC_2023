import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task2_b {

    public static void execute () {
        List<String> lines = fileReader();
        int sum = 0;
        for (int i = 0; i < lines.size(); i++) {
            String[] colors = getColors(lines.get(i));
            int power = minRedValue(colors) * minGreenValue(colors) * minBlueValue(colors);
            sum += power;
        }
        System.out.println(sum);
    }

    public static List<String> fileReader() {
        Path path = Paths.get("aoc_2024_day2_puzzle_input.txt");
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("An IO Exception occurred!");
            return new ArrayList<>();
        }
    }

    public static int getId (String game) {
        String id = game.substring(5, game.indexOf(':'));
        return Integer.valueOf(id);
    }

    public static String[] getColors (String game) {
        String colors = game.substring(game.indexOf(':') + 2);
        return colors.split(" ");
    }

    public static int minRedValue (String[] colors) {
        int min = 0;
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals("red") || colors[i].equals("red,") || colors[i].equals("red;")) {
                if (Integer.valueOf(colors[i - 1]) > min) {
                    min = Integer.valueOf(colors[i - 1]);
                }
            }
        }
        return min;
    }

    public static int minGreenValue (String[] colors) {
        int min = 0;
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals("green") || colors[i].equals("green,") || colors[i].equals("green;")) {
                if (Integer.valueOf(colors[i - 1]) > min) {
                    min = Integer.valueOf(colors[i - 1]);
                }
            }
        }
        return min;
    }

    public static int minBlueValue (String[] colors) {
        int min = 0;
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals("blue") || colors[i].equals("blue,") || colors[i].equals("blue;")) {
                if (Integer.valueOf(colors[i - 1]) > min) {
                    min = Integer.valueOf(colors[i - 1]);
                }
            }
        }
        return min;
    }

}